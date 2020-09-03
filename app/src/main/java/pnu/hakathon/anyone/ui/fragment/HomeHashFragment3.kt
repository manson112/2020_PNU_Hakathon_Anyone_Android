package pnu.hakathon.anyone.ui.fragment

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
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.ui.adapter.HomeFragmentHashListAdapter
import pnu.hakathon.anyone.viewmodel.MainViewModel

class HomeHashFragment3 : Fragment() {
    private val mainViewModel by sharedViewModel<MainViewModel>()

    @Nullable
    @Override
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home2_hash, container, false)

        val adapter =
            HomeFragmentHashListAdapter(
                requireContext()
            )
        v.home2_fragment_recyclerview.adapter = adapter
        val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.home_hash_divider)?.let {
            itemDecorator.setDrawable(
                it
            )
        }
        v.home2_fragment_recyclerview.addItemDecoration(itemDecorator)
        mainViewModel.store_noisy.observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
            v.home_hash_empty_text.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        })

        return v
    }

    companion object {
        fun newInstance(): HomeHashFragment3 {
            return HomeHashFragment3()
        }
    }
}