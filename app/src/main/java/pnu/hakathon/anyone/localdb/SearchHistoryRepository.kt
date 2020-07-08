package pnu.hakathon.anyone.localdb

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class SearchHistoryRepository(private val searchHistoryDao: SearchHistoryDao) {
    val allSearchHistories: LiveData<List<SearchHistory>> = searchHistoryDao.getSearchHistories()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(searchHistory: SearchHistory) {
        searchHistoryDao.insert(searchHistory)
    }

}