package pnu.hakathon.anyone.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationEndContainer
import kotlinx.android.synthetic.main.activity_store_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.viewmodel.StoreDetailViewModel

class StoreDetailActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    val storeDetailViewModel by viewModel<StoreDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationEndContainer(intent.getParcelableExtra("com.skydoves.transformationlayout"))
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_store_detail)

        refresh_container.setOnRefreshListener(this)

        val store: StoreModel = requireNotNull(intent.getParcelableExtra(STORE))
        storeDetailViewModel.setStore(store)
        storeDetailViewModel.bookmarked.value = store.bookmarked

        storeDetailViewModel.store.observe(this, Observer {
            Glide.with(this).load(it.imageURL).placeholder(R.drawable.image_empty).into(detail_image)
            detail_name.text            = it.storeName
            detail_address.text         = it.address
            detail_progress_1.labelText = "${it.noise}"
            detail_progress_1.progress  = it.noise.toFloat() * 20
            detail_progress_2.progress  = it.cleanliness.toFloat() * 20
            detail_progress_3.progress  = it.kindness.toFloat() * 20
            detail_progress_4.progress  = it.wifi.toFloat() * 20
            detail_progress_2.labelText = "${it.cleanliness}"
            detail_progress_3.labelText = "${it.kindness}"
            detail_progress_4.labelText = "${it.wifi}"
            detail_bookmark.isChecked   = it.bookmarked
        })

        storeDetailViewModel.text.observe(this, Observer {
            detail_text_fill.text = it
            refresh_container.isRefreshing = false
        })

        detail_bookmark.setOnCheckedChangeListener { buttonView, isChecked ->
            storeDetailViewModel.setBookmark(isChecked)
        }

        storeDetailViewModel.bookmarked.observe(this, Observer {
            detail_bookmark.isChecked = it
        })

        detail_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onRefresh() {
        storeDetailViewModel.getCurrentSeat()
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