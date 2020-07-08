package pnu.hakathon.anyone.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pnu.hakathon.anyone.R

class StoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        // 메뉴(메뉴명, 가격)
        // 리뷰(청결도, WIFI, 친절도, 소음)(DB)
        // 카페 정보(이름(DB), 좌석 수(DB), 현재 인원 수, 사진, 전화번호(DB))
    }
}