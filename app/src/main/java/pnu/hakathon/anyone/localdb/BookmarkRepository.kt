package pnu.hakathon.anyone.localdb

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class BookmarkRepository(private val bookmarkDao: BookmarkDao)  {
    val allBookmarks: LiveData<List<Bookmark>> = bookmarkDao.getBookmarks()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(bookmark: Bookmark) {
        bookmarkDao.insert(bookmark)
    }
}