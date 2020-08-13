package pnu.hakathon.anyone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pnu.hakathon.anyone.entity.Bookmark
import pnu.hakathon.anyone.entity.SearchHistory
import pnu.hakathon.anyone.localdb.AppDatabase
import pnu.hakathon.anyone.model.User

class LoginViewModel(application: Application) : AndroidViewModel(application) {
//    private val bmRepository: BookmarkRepository
//    private val shRepository: SearchHistoryRepository


    init {
        val bookmarkDao = AppDatabase.getDatabase(application, viewModelScope).bookmarkDao()
        val searchHistoryDao =
            AppDatabase.getDatabase(application, viewModelScope).searchHistoryDao()
//        bmRepository = BookmarkRepository(bookmarkDao)
//        shRepository = SearchHistoryRepository(searchHistoryDao)
    }

    fun requestBookmark() {
        User.requestBookmark("1", {
            // Success
            it?.responseData?.let { arr ->
                for (i in 0 until arr.size()) {
                    saveBookmark(Bookmark().jsonToObj(arr[i].asJsonObject))
                }
            }
        }, {
            // Fail
        })
    }

    fun requestSearchHistory() {
        val shs = emptyList<SearchHistory>()
        User.requestSearchHistory("1", {
            // Success
            it?.responseData?.let { arr ->
                for (i in 0 until arr.size()) {
                    saveSearchHistory(SearchHistory().jsonToObj(arr[i].asJsonObject))
                }
            }
        }, {
            // Fail
        })
    }

    private fun saveBookmark(bm: Bookmark) = viewModelScope.launch(Dispatchers.IO) {
//        bmRepository.insert(bm)
    }

    private fun saveSearchHistory(sh: SearchHistory) = viewModelScope.launch(Dispatchers.IO) {
//        shRepository.insert(sh)
    }
}