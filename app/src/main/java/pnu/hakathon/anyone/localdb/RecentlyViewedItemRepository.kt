package pnu.hakathon.anyone.localdb

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class RecentlyViewedItemRepository(private val recentlyViewedItemDao: RecentlyViewedItemDao) {
    val recentlyViewedItems: LiveData<List<RecentlyViewedItem>> = recentlyViewedItemDao.getRecentlyViewedItems()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(rv: RecentlyViewedItem) {
        recentlyViewedItemDao.insert(rv)
    }
}