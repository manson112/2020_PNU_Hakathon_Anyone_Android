package pnu.hakathon.anyone.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.ui.adapter.HomeFragmentListAdapter
import pnu.hakathon.anyone.viewmodel.MainViewModel

class HomeFragment : Fragment(){
    private val mainViewModel by sharedViewModel<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val adapter = HomeFragmentListAdapter(container?.context!!, mainViewModel.categoryID, mainViewModel.categoryName, this)
        v.home_recommend_recyclerview.adapter = adapter
        mainViewModel.stores.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.setList(it as MutableList<StoreModel>)
//                v.home_empty_text.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            }
        })
        mainViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    v.home_list_shimmer.startShimmer()
                } else {
                    v.home_list_shimmer.stopShimmer()
                }
                v.home_list_shimmer.visibility = if (it) View.VISIBLE else View.GONE
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