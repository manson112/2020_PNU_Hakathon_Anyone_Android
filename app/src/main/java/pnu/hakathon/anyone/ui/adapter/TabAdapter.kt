package pnu.hakathon.anyone.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import pnu.hakathon.anyone.ui.fragment.HomeHashFragment1
import pnu.hakathon.anyone.ui.fragment.HomeHashFragment2
import pnu.hakathon.anyone.ui.fragment.HomeHashFragment3
import pnu.hakathon.anyone.ui.fragment.HomeHashFragment4

class TabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeHashFragment1()
            1 -> HomeHashFragment2()
            2 -> HomeHashFragment3()
            else -> HomeHashFragment4()
        }
    }
}