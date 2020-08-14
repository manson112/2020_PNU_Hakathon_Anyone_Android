package pnu.hakathon.anyone.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_tab.view.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.view.adapter.TabAdapter
import pnu.hakathon.anyone.view.fragment.BookmarkFragment
import pnu.hakathon.anyone.view.fragment.HomeFragment
import pnu.hakathon.anyone.view.fragment.MapFragment
import pnu.hakathon.anyone.view.fragment.ProfileFragment
import pnu.hakathon.anyone.viewmodel.HomeViewModel


class MainActivity : AppCompatActivity() {
    val homeViewModel: HomeViewModel by stateViewModel()
    lateinit var categoryID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main)

        categoryID = intent.getStringExtra("categoryID")!!

        val tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(MapFragment.newInstance(), "지도")
        tabAdapter.addFragment(HomeFragment.newInstance(), "홈")
        tabAdapter.addFragment(BookmarkFragment.newInstance(), "북마크")
        tabAdapter.addFragment(ProfileFragment.newInstance(), "프로필")

        main_viewpager.offscreenPageLimit = 4
        main_viewpager.adapter = tabAdapter
        main_tablayout.setupWithViewPager(main_viewpager)

        main_viewpager.isPagingEnabled = false

//        homeViewModel.requestHome(categoryID)
        homeViewModel.categoryID = categoryID
        homeViewModel.getNewList()

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
        tab3.main_tab_image.setImageResource(R.drawable.ic_bookmark)
        tab3.main_tab_text.text = "북마크"
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

    fun toSearchActivity() {
        startActivity(Intent(this, SearchActivity::class.java))
    }


}