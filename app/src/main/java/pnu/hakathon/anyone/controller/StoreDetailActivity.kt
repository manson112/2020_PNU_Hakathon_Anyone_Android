package pnu.hakathon.anyone.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pnu.hakathon.anyone.R

class StoreDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)
        // 메뉴(메뉴명, 가격)
        // 리뷰(청결도, WIFI, 친절도, 소음)(DB)
        // 카페 정보(이름(DB), 좌석 수(DB), 현재 인원 수, 사진, 전화번호(DB))
    }
}