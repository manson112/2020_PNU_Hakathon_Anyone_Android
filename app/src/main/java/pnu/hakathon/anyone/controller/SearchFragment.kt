package pnu.hakathon.anyone.controller

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_search.view.*
import pnu.hakathon.anyone.R
import pnu.hakathon.anyone.adapter.search.SearchHistoryListAdapter


class SearchFragment : Fragment() {
    lateinit var context: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_search, container, false)
        context = activity as MainActivity
        val adapter =
            SearchHistoryListAdapter(context)
        v.search_history_recyclerview.adapter = adapter


//        context.searchViewModel.searchHistories.observe(context, Observer { hists ->
//            hists?.let { adapter.setHistories(it) }
//        })
//        val searchAdapter = SearchListAdapter(context)
//        v.search_recyclerview.adapter = searchAdapter
//        context.searchViewModel.searchResults.observe(context, Observer {
//            it?.let {
//                searchAdapter.setList(it)
//            }
//        })
//        v.search_searchbar.setOnFocusChangeListener { vv, hasFocus ->
//            if (hasFocus) {
//                v.search_history_container.visibility = View.VISIBLE
//            } else {
//                v.search_history_container.visibility = View.GONE
//                hideKeyboard()
//            }
//        }
//
//        v.search_searchbar.setOnEditorActionListener { vv, actionId, event ->
//            val searchQuery = v.search_searchbar.text.toString().trim()
//            if (searchQuery == "") {
//                return@setOnEditorActionListener false
//            }
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault())
//                val currentDate = sdf.format(Date())
//                context.searchViewModel.insert(SearchHistory(0, searchQuery, currentDate))
//                v.search_searchbar.clearFocus()
//                context.searchViewModel.setDummy()
//                return@setOnEditorActionListener true
//            }
//            false
//        }
//        v.setOnClickListener {
//            hideKeyboard()
//        }

        return v
    }

    fun executeSearch(view: View, searchQuery: String) {

    }

    private fun hideKeyboard() {
        val imm: InputMethodManager = view!!.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}