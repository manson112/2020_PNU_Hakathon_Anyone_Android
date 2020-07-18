package pnu.hakathon.anyone.controller

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_search.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.search.SearchHistoryListAdapter
import pnu.hakathon.anyone.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_search)
        val adapter =
            SearchHistoryListAdapter(this)
        search_recyclerView.adapter = adapter

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchViewModel.searchHistories.observe(this, Observer { hists ->
            hists?.let { adapter.setHistories(it) }
        })
    }
}