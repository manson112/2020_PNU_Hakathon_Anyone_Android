package pnu.hakathon.anyone.controller

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_search.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.search.SearchHistoryListAdapter
import pnu.hakathon.anyone.adapter.search.SearchListAdapter
import pnu.hakathon.anyone.localdb.SearchHistory
import pnu.hakathon.anyone.viewmodel.SearchViewModel
import java.text.SimpleDateFormat
import java.util.*

class SearchActivity : AppCompatActivity() {
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_search)
        val adapter = SearchHistoryListAdapter(this)
        search_history_recyclerview.adapter = adapter

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchViewModel.searchHistories.observe(this, Observer { hists ->
            hists?.let { adapter.setHistories(it) }
        })

        val searchAdapter = SearchListAdapter(this)
        search_recyclerview.adapter = searchAdapter

        searchViewModel.searchResults.observe(this, Observer {
            it?.let {
                searchAdapter.setList(it)
            }
        })

        search_searchbar.setOnFocusChangeListener { vv, hasFocus ->
            if (hasFocus) {
                search_history_container.visibility = View.VISIBLE
            } else {
                search_history_container.visibility = View.GONE
                this.hideKeyboard()
            }
        }

        search_searchbar.setOnEditorActionListener { vv, actionId, event ->
            val searchQuery = search_searchbar.text.toString().trim()
            if (searchQuery == "") {
                return@setOnEditorActionListener false
            }
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault())
                val currentDate = sdf.format(Date())
                searchViewModel.insert(SearchHistory(0, searchQuery, currentDate))
                search_searchbar.clearFocus()
                hideKeyboard()
                searchViewModel.setDummy()
                return@setOnEditorActionListener true
            }
            false
        }

        backBtn.setOnClickListener {
            finish()
        }
        search_activity.setOnClickListener {
            hideKeyboard()
        }
    }


    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}