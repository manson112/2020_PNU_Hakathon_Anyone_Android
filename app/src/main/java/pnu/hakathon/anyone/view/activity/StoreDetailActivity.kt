package pnu.hakathon.anyone.view.activity

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_store_detail.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.view.adapter.TabAdapter
import pnu.hakathon.anyone.view.fragment.StoreDetailMenuFragment
import pnu.hakathon.anyone.view.fragment.StoreDetailReviewFragment
import pnu.hakathon.anyone.viewmodel.StoreDetailViewModel

class StoreDetailActivity : AppCompatActivity() {
    lateinit var storeDetailViewModel: StoreDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_store_detail)
        storeDetailViewModel = ViewModelProvider(this).get(StoreDetailViewModel::class.java)

        storeDetailViewModel.storeID = "1"
        storeDetailViewModel.requestStoreInfo()

        storeDetailViewModel.store.observe(this, Observer { storeDetail ->
            // NEED VIEW BINDING
            Log.d("StoreDetailActivity", storeDetail.toString())
        })

        val tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(StoreDetailMenuFragment.newInstance(), "Menu")
        tabAdapter.addFragment(StoreDetailReviewFragment.newInstance(), "Review")

        detail_viewpager.adapter = tabAdapter
        detail_tab.setupWithViewPager(detail_viewpager)

        // 메뉴(메뉴명, 가격)
        // 리뷰(청결도, WIFI, 친절도, 소음)(DB)
        // 카페 정보(이름(DB), 좌석 수(DB), 현재 인원 수, 사진, 전화번호(DB))
    }
}