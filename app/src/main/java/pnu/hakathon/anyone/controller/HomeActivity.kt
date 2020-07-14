package pnu.hakathon.anyone.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_home.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.home.HomeAlbumListAdapter
import pnu.hakathon.anyone.adapter.home.HomeHashTabFragmentAdapter
import pnu.hakathon.anyone.adapter.home.HomeRecommendedListAdapter
import pnu.hakathon.anyone.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val adapter = HomeRecommendedListAdapter(this)
        home_recyclerview_recommend.adapter = adapter

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.recommended.observe(this, Observer {
            adapter.setRecommended(it)
        })

        val albumAdapter = HomeAlbumListAdapter(this)
        home_recyclerview_album.adapter = albumAdapter

        homeViewModel.album.observe(this, Observer {
            albumAdapter.setAlbum(it)
        })

        val tabAdapter = HomeHashTabFragmentAdapter(supportFragmentManager)
        tabAdapter.addFragment(HomeHashFragment1.newInstance(), "#조용한")
        tabAdapter.addFragment(HomeHashFragment2.newInstance(), "#활기찬")
        tabAdapter.addFragment(HomeHashFragment3.newInstance(), "#친절한")
        tabAdapter.addFragment(HomeHashFragment4.newInstance(), "#깨끗한")
        home_hashtag_viewpager.adapter = tabAdapter
        home_hashtag_tablayout.setupWithViewPager(home_hashtag_viewpager)

        homeViewModel.setDummyData()
    }
}