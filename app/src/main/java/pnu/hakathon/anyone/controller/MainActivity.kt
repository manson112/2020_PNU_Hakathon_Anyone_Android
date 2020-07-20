package pnu.hakathon.anyone.controller

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_tab.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.TabAdapter
import pnu.hakathon.anyone.viewmodel.HomeViewModel
import pnu.hakathon.anyone.viewmodel.SearchViewModel


class MainActivity : AppCompatActivity() {
    lateinit var homeViewModel: HomeViewModel
    lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        val tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(MapFragment.newInstance(), "지도")
        tabAdapter.addFragment(HomeFragment.newInstance(), "홈")
        tabAdapter.addFragment(SearchFragment.newInstance(), "탐색")
        tabAdapter.addFragment(ProfileFragment.newInstance(), "프로필")

        main_viewpager.offscreenPageLimit = 4
        main_viewpager.adapter = tabAdapter
        main_tablayout.setupWithViewPager(main_viewpager)
        setupTabIcons()
        moveTab(1)
    }

    private fun setupTabIcons() {
        val tab1 = layoutInflater.inflate(R.layout.main_tab, null)
        tab1.main_tab_image.setImageResource(R.drawable.ic_location)
        tab1.main_tab_text.text = "지도"
        val tab2 = layoutInflater.inflate(R.layout.main_tab, null)
        tab2.main_tab_image.setImageResource(R.drawable.ic_home)
        tab2.main_tab_text.text = "홈"
        val tab3 = layoutInflater.inflate(R.layout.main_tab, null)
        tab3.main_tab_image.setImageResource(R.drawable.ic_search)
        tab3.main_tab_text.text = "탐색"
        val tab4 = layoutInflater.inflate(R.layout.main_tab, null)
        tab4.main_tab_image.setImageResource(R.drawable.ic_profile)
        tab4.main_tab_text.text = "프로필"

        main_tablayout.getTabAt(0)?.customView = tab1
        main_tablayout.getTabAt(1)?.customView = tab2
        main_tablayout.getTabAt(2)?.customView = tab3
        main_tablayout.getTabAt(3)?.customView = tab4
    }

    fun moveTab(position: Int) {
        main_viewpager.currentItem = position
    }

}