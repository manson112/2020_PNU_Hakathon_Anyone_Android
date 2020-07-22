package pnu.hakathon.anyone.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_home2_hash.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.home.HomeFragmentListAdapter


class HomeHashFragment2 : Fragment() {
    lateinit var context: MainActivity

    @Nullable
    @Override
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home2_hash, container, false)
        context = activity as MainActivity
        val adapter = HomeFragmentListAdapter(context)
        v.home2_fragment_recyclerview.adapter = adapter
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        ContextCompat.getDrawable(context, R.drawable.home_hash_divider)?.let {
            itemDecorator.setDrawable(
                it
            )
        }
        v.home2_fragment_recyclerview.addItemDecoration(itemDecorator)
        context.homeViewModel.quite.observe(context, Observer {
            it?.let { adapter.setList(it) }
        })
        return v
    }

    companion object {
        fun newInstance(): HomeHashFragment2 {
            return HomeHashFragment2()
        }
    }
}