package pnu.hakathon.anyone.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_store_detail_menu.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.storedetail.MenuListAdapter


class StoreDetailMenuFragment : Fragment() {
    lateinit var layoutManager: GridLayoutManager
    lateinit var context: StoreDetailActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_store_detail_menu, container, false)
        context = activity as StoreDetailActivity
        val adapter = MenuListAdapter(context)

        layoutManager = GridLayoutManager(context, 6)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val gridPosition = position % 3
                when (gridPosition) {
                    0, 1 -> return 3
                    2 -> return 6
                }
                return 0
            }
        }
        v.detail_menu_recyclerview.layoutManager = layoutManager
        v.detail_menu_recyclerview.adapter = adapter

        context.storeDetailViewModel.menu.observe(context, Observer {
            Log.d("DEEE", "HERE")
            adapter.setMenu(it)
        })

        context.storeDetailViewModel.setDummyMenu()
        return v
    }

    companion object {
        fun newInstance() = StoreDetailMenuFragment()
    }
}