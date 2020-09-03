package pnu.hakathon.anyone.ui.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.ui.adapter.SearchListAdapter
import pnu.hakathon.anyone.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {
    private val searchViewModel by viewModel<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_search)

        val categoryID = requireNotNull(intent.getStringExtra("categoryID"))
        searchViewModel.categoryID = categoryID


        val searchAdapter =
            SearchListAdapter(this)
        search_recyclerview.adapter = searchAdapter
        searchViewModel.searchResults.observe(this, Observer {
            it?.let {
                searchAdapter.setList(it)
            }
        })

        search_box.setOnQueryTextListener(queryTextListener)

        backBtn.setOnClickListener {
            onBackPressed()
        }
        search_activity.setOnClickListener {
            hideKeyboard()
        }
    }

    val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                searchViewModel.setQuery(it)
            }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
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