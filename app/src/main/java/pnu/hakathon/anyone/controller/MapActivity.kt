package pnu.hakathon.anyone.controller

import android.Manifest
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import net.daum.mf.map.api.MapView
import pnu.hakathon.anyone.R

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_map)

        checkPermission()
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

    val permissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            val mapView = MapView(this@MapActivity)

            val mapViewContainer = findViewById<ViewGroup>(R.id.map_view)
            mapViewContainer.addView(mapView)
        }

        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            finish()
        }
    }
}