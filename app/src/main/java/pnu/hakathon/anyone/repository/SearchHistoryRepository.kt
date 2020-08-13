package pnu.hakathon.anyone.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import pnu.hakathon.anyone.dao.SearchHistoryDao
import pnu.hakathon.anyone.entity.SearchHistory

class SearchHistoryRepository(private val searchHistoryDao: SearchHistoryDao) {
    val allSearchHistories: LiveData<List<SearchHistory>> = searchHistoryDao.getSearchHistories()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(searchHistory: SearchHistory) {
        searchHistoryDao.insert(searchHistory)
    }

}