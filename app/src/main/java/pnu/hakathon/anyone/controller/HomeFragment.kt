package pnu.hakathon.anyone.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_home.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.TabAdapter

class HomeFragment : Fragment() {
    lateinit var context: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        context = activity as MainActivity

        Glide.with(context)
            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png")
            .into(v.home2_profile_image)

        val tabAdapter = TabAdapter(context.supportFragmentManager)
        tabAdapter.addFragment(HomeHashFragment1.newInstance(), "#조용한")
        tabAdapter.addFragment(HomeHashFragment2.newInstance(), "#활기찬")
        tabAdapter.addFragment(HomeHashFragment3.newInstance(), "#친절한")
        tabAdapter.addFragment(HomeHashFragment4.newInstance(), "#깨끗한")

        v.home2_viewpager.adapter = tabAdapter
        v.home2_tablayout.setupWithViewPager(v.home2_viewpager)
        return v
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}