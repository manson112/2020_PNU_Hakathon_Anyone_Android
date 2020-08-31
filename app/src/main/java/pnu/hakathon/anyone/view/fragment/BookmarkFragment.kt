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
import kotlinx.android.synthetic.main.fragment_bookmark.view.*
import org.koin.android.ext.android.inject
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.view.activity.MainActivity
import pnu.hakathon.anyone.view.activity.StoreDetailActivity
import pnu.hakathon.anyone.view.adapter.bookmark.BookmarkListAdapter
import pnu.hakathon.anyone.viewmodel.BookmarkViewModel

class BookmarkFragment : Fragment() {
    lateinit var context: MainActivity
    val bookmarkViewModel: BookmarkViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_bookmark, container, false)
        context = activity as MainActivity
        val adapter = BookmarkListAdapter(context, bookmarkViewModel)
        v.bookmark_recyclerView_cafe.adapter = adapter

        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(context, R.drawable.recycler_divider_vertical)?.let {
            itemDecorator.setDrawable(
                it
            )
        }
        v.bookmark_recyclerView_cafe.addItemDecoration(itemDecorator)

        bookmarkViewModel.bookmarks.observe(context, Observer {
            it?.let { adapter.setBookmarks(it) }
        })
        bookmarkViewModel.selected.observe(context, Observer {
            val intent = Intent(context, StoreDetailActivity::class.java)
            context.startActivity(intent)
        })

        return v
    }

    companion object {
        fun newInstance() = BookmarkFragment()
    }
}