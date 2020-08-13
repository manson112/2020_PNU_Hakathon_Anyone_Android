package pnu.hakathon.anyone.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import pnu.hakathon.anyone.dao.RecentlyViewedItemDao
import pnu.hakathon.anyone.entity.RecentlyViewedItem

class RecentlyViewedItemRepository(private val recentlyViewedItemDao: RecentlyViewedItemDao) {
    val recentlyViewedItems: LiveData<List<RecentlyViewedItem>> = recentlyViewedItemDao.getRecentlyViewedItems()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(rv: RecentlyViewedItem) {
        recentlyViewedItemDao.insert(rv)
    }
}