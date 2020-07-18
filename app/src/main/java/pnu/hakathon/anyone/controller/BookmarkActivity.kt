package pnu.hakathon.anyone.controller

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_bookmark.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.bookmark.BookmarkListAdapter
import pnu.hakathon.anyone.viewmodel.BookmarkViewModel

class BookmarkActivity : AppCompatActivity() {
    private lateinit var bookmarkViewModel: BookmarkViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_bookmark)

        shimmerFrameLayout.startShimmer()

        val adapter = BookmarkListAdapter(this)
        bookmark_recyclerView.adapter = adapter

        bookmarkViewModel = ViewModelProvider(this).get(BookmarkViewModel::class.java)
        bookmarkViewModel.bookmarks.observe(this, Observer { bookmarks ->
            bookmarks?.let {
                shimmerFrameLayout.stopShimmer()
                shimmerFrameLayout.visibility = View.GONE
                bookmark_recyclerView.visibility = View.VISIBLE
                adapter.setBookmarks(it)
            }
        })
    }
}