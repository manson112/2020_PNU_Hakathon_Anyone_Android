package pnu.hakathon.anyone.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationEndContainer
import kotlinx.android.synthetic.main.activity_store_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.viewmodel.StoreDetailViewModel
import kotlin.math.roundToInt

class StoreDetailActivity : AppCompatActivity() {
    val storeDetailViewModel by viewModel<StoreDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationEndContainer(intent.getParcelableExtra("com.skydoves.transformationlayout"))
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_store_detail)
        val store: StoreModel = requireNotNull(intent.getParcelableExtra(STORE))
        storeDetailViewModel.store = store

        Glide.with(this).load(storeDetailViewModel.store.imageURL).placeholder(R.drawable.image_empty).into(detail_image)
        detail_name.text            = storeDetailViewModel.store.storeName
        detail_address.text         = storeDetailViewModel.store.address
        detail_progress_1.labelText = "${storeDetailViewModel.store.noise}"
        detail_progress_1.progress  = storeDetailViewModel.store.noise.toFloat() * 20
        detail_progress_2.labelText = "${storeDetailViewModel.store.cleanliness}"
        detail_progress_2.progress  = storeDetailViewModel.store.cleanliness.toFloat() * 20
        detail_progress_3.labelText = "${storeDetailViewModel.store.kindness}"
        detail_progress_3.progress  = storeDetailViewModel.store.kindness.toFloat() * 20
        detail_progress_4.labelText = "${storeDetailViewModel.store.wifi}"
        detail_progress_4.progress  = storeDetailViewModel.store.wifi.toFloat() * 20
        detail_text_fill.text = "현재 ${((storeDetailViewModel.store.current.toDouble() / storeDetailViewModel.store.total)*100).roundToInt()}%예요.\n완충까지 ${storeDetailViewModel.store.total - storeDetailViewModel.store.current}자리 남았어요!"
        detail_bookmark.isChecked = storeDetailViewModel.store.bookmarked

        detail_bookmark.setOnCheckedChangeListener { buttonView, isChecked ->
            storeDetailViewModel.setBookmark(isChecked)
        }

        storeDetailViewModel.bookmarked.observe(this, Observer {
            detail_bookmark.isChecked = it
        })

        detail_back.setOnClickListener {
            onBackPressed()
        }

        // 메뉴(메뉴명, 가격)
        // 리뷰(청결도, WIFI, 친절도, 소음)(DB)
        // 카페 정보(이름(DB), 좌석 수(DB), 현재 인원 수, 사진, 전화번호(DB))
    }

    companion object {
        const val STORE = "STORE"
        fun startActivity(transformationLayout: TransformationLayout, store: StoreModel) {
            val context = transformationLayout.context
            if (context is Activity) {
                val intent = Intent(context, StoreDetailActivity::class.java)
                intent.putExtra(STORE, store)
                TransformationCompat.startActivity(transformationLayout, intent)
            }
        }
    }
}