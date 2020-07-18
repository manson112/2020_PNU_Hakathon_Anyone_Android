package pnu.hakathon.anyone.controller

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_category2.*
import pnu.hakathon.anyone.R

class CategoryActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_category2)
        category2_btn_cafe.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        category2_btn_restaurant.setOnClickListener {
            val intent = Intent(this, SignInByPhoneActivity::class.java)
            startActivity(intent)
        }
        category2_btn_pub.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
        category2_btn_fastfood.setOnClickListener {
            val intent = Intent(this, StoreDetailActivity::class.java)
            startActivity(intent)
        }
    }
}