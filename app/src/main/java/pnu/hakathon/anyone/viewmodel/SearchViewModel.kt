package pnu.hakathon.anyone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pnu.hakathon.anyone.localdb.AppDatabase
import pnu.hakathon.anyone.localdb.SearchHistory
import pnu.hakathon.anyone.localdb.SearchHistoryRepository

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SearchHistoryRepository
    val searchHistories: LiveData<List<SearchHistory>>

    init {
        val searchHistoryDao = AppDatabase.getDatabase(application, viewModelScope).searchHistoryDao()
        repository = SearchHistoryRepository(searchHistoryDao)
        searchHistories = repository.allSearchHistories
    }

    fun insert(searchHistory: SearchHistory) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(searchHistory)
    }
}