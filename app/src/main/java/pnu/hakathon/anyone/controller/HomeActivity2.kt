package pnu.hakathon.anyone.controller

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_home2.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.home.HomeHashTabFragmentAdapter
import pnu.hakathon.anyone.viewmodel.HomeViewModel

class HomeActivity2 : AppCompatActivity() {
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_home2)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val tabAdapter = HomeHashTabFragmentAdapter(supportFragmentManager)
        tabAdapter.addFragment(HomeHashFragment1.newInstance(), "#조용한")
        tabAdapter.addFragment(HomeHashFragment2.newInstance(), "#활기찬")
        tabAdapter.addFragment(HomeHashFragment3.newInstance(), "#친절한")
        tabAdapter.addFragment(HomeHashFragment4.newInstance(), "#깨끗한")

        home2_viewpager.adapter = tabAdapter
        home2_tablayout.setupWithViewPager(home2_viewpager)
    }
}