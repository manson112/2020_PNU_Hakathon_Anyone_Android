package pnu.hakathon.anyone.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_home2_hash.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.home.HomeFragmentListOneAdapter
import pnu.hakathon.anyone.adapter.home.HomeFragmentListThreeAdapter
import pnu.hakathon.anyone.adapter.home.HomeFragmentListTwoAdapter

class HomeHashFragment1 : Fragment() {
    lateinit var context: MainActivity

    @Nullable
    @Override
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home2_hash, container, false)
        context = activity as MainActivity

        val adapter1 = HomeFragmentListOneAdapter(context)
        val adapter2 = HomeFragmentListTwoAdapter(context)
        val adapter3 = HomeFragmentListThreeAdapter(context)
        v.home2_fragment_recyclerview1.adapter = adapter1
        v.home2_fragment_recommend_recyclerview1.adapter = adapter2
        v.home2_fragment_recommend_recyclerview2.adapter = adapter3

        context.homeViewModel.list1.list1.observe(context, Observer {
            adapter1.setList(it)
        })
        context.homeViewModel.list1.list2.observe(context, Observer {
            adapter2.setList(it)
        })
        context.homeViewModel.list1.list3.observe(context, Observer {
            adapter3.setList(it)
        })

        context.homeViewModel.setA()
        return v
    }

    companion object {
        fun newInstance(): HomeHashFragment1 {
            return HomeHashFragment1()
        }
    }
}