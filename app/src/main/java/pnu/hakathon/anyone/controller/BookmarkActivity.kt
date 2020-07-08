package pnu.hakathon.anyone.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_bookmark.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.BookmarkListAdapter
import pnu.hakathon.anyone.viewmodel.BookmarkViewModel

class BookmarkActivity : AppCompatActivity() {
    private lateinit var bookmarkViewModel: BookmarkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        val adapter = BookmarkListAdapter(this)
        bookmark_recyclerView.adapter = adapter

        bookmarkViewModel = ViewModelProvider(this).get(BookmarkViewModel::class.java)
        bookmarkViewModel.bookmarks.observe(this, Observer { bookmarks ->
            bookmarks?.let { adapter.setBookmarks(it) }
        })
    }
}