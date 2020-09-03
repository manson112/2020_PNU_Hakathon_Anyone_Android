package pnu.hakathon.anyone.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_category.*
import pnu.hakathon.anyone.R

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_category)

        category_img_cafe.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
                .putExtra("categoryID", "1")
                .putExtra("categoryName", "카페")
            startActivity(intent)
        }
        category_img_restaurant.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
                .putExtra("categoryID", "2")
                .putExtra("categoryName", "음식점")
            startActivity(intent)
        }

    }
}