package pnu.hakathon.anyone.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_bookmark.view.*
import org.koin.android.ext.android.inject
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.view.adapter.bookmark.BookmarkListAdapter
import pnu.hakathon.anyone.viewmodel.BookmarkViewModel

class BookmarkFragment : Fragment() {
    val bookmarkViewModel: BookmarkViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_bookmark, container, false)
        val adapter_cafe = BookmarkListAdapter(requireContext())
        val adapter_restaurant = BookmarkListAdapter(requireContext())
        v.bookmark_recyclerView_cafe.adapter = adapter_cafe
        v.bookmark_recyclerView_restaurant.adapter = adapter_restaurant

        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.recycler_divider_vertical)?.let { itemDecorator.setDrawable(it) }
        v.bookmark_recyclerView_cafe.addItemDecoration(itemDecorator)
        v.bookmark_recyclerView_restaurant.addItemDecoration(itemDecorator)

        bookmarkViewModel.cafe.observe(viewLifecycleOwner, Observer {
            it?.let { adapter_cafe.setBookmarks(it) }
        })
        bookmarkViewModel.rest.observe(viewLifecycleOwner, Observer {
            it?.let { adapter_restaurant.setBookmarks(it) }
        })
//        bookmarkViewModel.selected.observe(context, Observer {
//            val intent = Intent(context, StoreDetailActivity::class.java)
//            context.startActivity(intent)
//        })

        return v
    }

    companion object {
        fun newInstance() = BookmarkFragment()
    }
}