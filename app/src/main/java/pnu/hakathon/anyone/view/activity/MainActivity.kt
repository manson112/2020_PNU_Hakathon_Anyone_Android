package pnu.hakathon.anyone.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.viewmodel.HomeViewModel


class MainActivity : AppCompatActivity() {
    val homeViewModel: HomeViewModel by stateViewModel()
    lateinit var categoryID: String
    lateinit var categoryName: String
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.main_fragment)
        val appBar = app_bar as Toolbar

        appBar.overflowIcon = null
        setSupportActionBar(appBar)
        setupActionBarWithNavController(navController)
        categoryID = intent.getStringExtra("categoryID")!!
        categoryName = intent.getStringExtra("categoryName")!!
        homeViewModel.categoryID = categoryID
        homeViewModel.getNewList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_tab_menu, menu)
        main_bottomBar.setupWithNavController(menu!!, navController)
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        Log.d("ACAC", "CLICKED")
        navController.navigateUp()
        return true
    }

    fun toSearchActivity() {
        startActivity(Intent(this, SearchActivity::class.java))
    }


}