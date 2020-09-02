package pnu.hakathon.anyone.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.view.activity.SearchActivity
import pnu.hakathon.anyone.view.adapter.TabAdapter
import pnu.hakathon.anyone.view.adapter.home.HomeFragmentListAdapter
import pnu.hakathon.anyone.viewmodel.MainViewModel

class HomeFragment : Fragment(){
    private val mainViewModel by sharedViewModel<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        Glide.with(container?.context!!)
            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png")
            .into(v.home_profile_image)

        v.home_text_category_1.text = mainViewModel.categoryName
        v.home_text_category_2.text = mainViewModel.categoryName
        v.home_text_category_3.text = mainViewModel.categoryName
        v.home_searchbar.hint = "${mainViewModel.categoryName} 이름을 검색해보세요"

        val tabAdapter = TabAdapter(this)
        v.home_hash_pager.adapter = tabAdapter
        TabLayoutMediator(v.home_tablayout, v.home_hash_pager) { tab, position ->
            when(position) {
                0 -> tab.text = "조용한"
                1 -> tab.text = "쾌적한"
                2 -> tab.text = "활기찬"
                3 -> tab.text = "친절한"
            }
        }.attach()

        v.home_searchbar.setOnClickListener {
            startActivity(Intent(requireActivity(), SearchActivity::class.java))
        }

        val adapter = HomeFragmentListAdapter(container.context)
        v.home_recommend_recyclerview.adapter = adapter
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        ContextCompat.getDrawable(container.context, R.drawable.home_hash_divider)?.let {
            itemDecorator.setDrawable(
                it
            )
        }
        v.home_recommend_recyclerview.addItemDecoration(itemDecorator)
        mainViewModel.stores.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.setList(it)
                var seats = 0
                it.forEach { item -> seats += (item.total - item.current) }
                v.home_text_num_of_seat.text = seats.toString()
                v.home_empty_text.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            }
        })

        return v
    }
    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}