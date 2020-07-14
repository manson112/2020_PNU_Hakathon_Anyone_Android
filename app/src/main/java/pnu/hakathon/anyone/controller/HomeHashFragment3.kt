package pnu.hakathon.anyone.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_home_hash.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.home.HomeHashListAdapter

class HomeHashFragment3 : Fragment() {
    @Nullable
    @Override
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home_hash, container, false)
        val context = activity as HomeActivity
        val adapter = HomeHashListAdapter(context)
        v.home_hash_fragment_recyclerview.adapter = adapter
        context.homeViewModel.kind.value?.let {
            adapter.setStores(it)
        }
        context.homeViewModel.kind.observe(context, Observer {
            adapter.setStores(it)
        })
        context.homeViewModel.setC()

        return v
    }

    companion object {
        fun newInstance(): HomeHashFragment3 {
            return HomeHashFragment3()
        }
    }
}