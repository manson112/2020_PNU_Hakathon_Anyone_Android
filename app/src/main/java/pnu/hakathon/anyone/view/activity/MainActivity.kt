package pnu.hakathon.anyone.view.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.Menu
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.Util
import pnu.hakathon.anyone.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    val mainViewModel by viewModel<MainViewModel>()

    var locationManager: LocationManager? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main)

        checkPermission()

        mainViewModel.setCategory(intent.getStringExtra("categoryID")!!, intent.getStringExtra("categoryName")!!)
        mainViewModel.address.observe(this, Observer {
            app_bar_title.text = it
        })

        // INIT NAVCONTROLLER
        navController = findNavController(R.id.main_fragment)

        // APP BAR SETTING
        val appBar = app_bar as Toolbar
        appBar.overflowIcon = null
        setSupportActionBar(appBar)
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setDisplayShowHomeEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_tab_menu, menu)
        main_bottomBar.setupWithNavController(menu!!, navController)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return true
    }

    fun toSearchActivity() {
        startActivity(Intent(this, SearchActivity::class.java))
    }

    private fun checkPermission() {
        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .check()
    }

    private val permissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            getLocation()
        }
        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            finish()
        }
    }

    fun getLocation() {
        try {
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                100, 1f, mLocationListener
            )
            locationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                100, 1f, mLocationListener
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    val mLocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            mainViewModel.setLatLng(location?.latitude!!, location.longitude)
            mainViewModel.setAddress(Util.getCompleteAddressString(this@MainActivity, location.latitude, location.longitude))
        }
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        override fun onProviderEnabled(provider: String?) {}
        override fun onProviderDisabled(provider: String?) {}
    }

}