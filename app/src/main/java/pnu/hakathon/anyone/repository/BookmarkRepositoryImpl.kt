package pnu.hakathon.anyone.repository

import android.util.Log
import androidx.lifecycle.LiveData
import pnu.hakathon.anyone.localdb.Bookmark
import pnu.hakathon.anyone.localdb.BookmarkDao
import pnu.hakathon.anyone.model.ReqBookmark
import pnu.hakathon.anyone.network.RetrofitService
import java.net.ConnectException
import java.util.concurrent.Executor

class BookmarkRepositoryImpl(
    private val webService: RetrofitService,
    private val executor: Executor,
    private val bookmarkDao: BookmarkDao
) : BookmarkRepository {
    override fun getBookmarks(userID: String): LiveData<List<Bookmark>> {
        getBookmarksFromServer(userID)
        return bookmarkDao.getBookmarks()
    }

    private fun getBookmarksFromServer(userID: String) {

        executor.execute {
            try {
                val response = webService.requestBookmark(ReqBookmark(userID).toMap()).execute()
                if (response.isSuccessful) {
                    response.body()?.responseData?.let { arr ->
                        for (i in 0 until arr.size()) {
                            bookmarkDao.insert(Bookmark().jsonToObj(arr[i].asJsonObject))
                        }
                    }
                }
            } catch (e: ConnectException) {
                Log.e("getBookmarksFromServer", "Cannot connect to server")
            }
        }

    }
}