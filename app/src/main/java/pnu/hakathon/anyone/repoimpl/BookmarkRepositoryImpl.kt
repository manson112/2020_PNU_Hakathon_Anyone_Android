package pnu.hakathon.anyone.repoimpl

import pnu.hakathon.anyone.dao.BookmarkDao
import pnu.hakathon.anyone.network.RetrofitService
import pnu.hakathon.anyone.repository.BookmarkRepository
import java.util.concurrent.Executor

class BookmarkRepositoryImpl(
    private val webService: RetrofitService,
    private val executor: Executor,
    private val bookmarkDao: BookmarkDao
) : BookmarkRepository {
//    override suspend fun getBookmarks(userID: String): LiveData<List<Bookmark>> {
//        getBookmarksFromServer(userID)
//        return bookmarkDao.getBookmarks()
//    }

//    private suspend fun getBookmarksFromServer(userID: String) {
//            val response = webService.requestBookmark(ReqBookmark(userID).toMap()).execute()
//            if (response.isSuccessful) {
//                response.body()?.responseData?.let { arr ->
//                    for (i in 0 until arr.size()) {
//                        Log.d("BOOKMARK FROM SERVER", arr[i].toString())
//                        bookmarkDao.insert(
//                            Bookmark().jsonToObj(arr[i].asJsonObject)
//                        )
//                    }
//                } ?: run {
//                    bookmarkDao.deleteAll()
//                }
//            }
//        }
//
//    }
}