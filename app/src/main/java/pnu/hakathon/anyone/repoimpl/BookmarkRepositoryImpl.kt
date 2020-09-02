package pnu.hakathon.anyone.repoimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.model.ReqBookmark
import pnu.hakathon.anyone.network.RetrofitService
import pnu.hakathon.anyone.repository.BookmarkRepository

class BookmarkRepositoryImpl(
    private val webService: RetrofitService
) : BookmarkRepository {
    override suspend fun getBookmarksFromServer(userID: String) = flow {
        val response = webService.requestBookmark(ReqBookmark(userID).toMap())
        response.responseData?.let { arr ->
            val list = ArrayList<StoreModel>()
            arr.forEach {
                val item = StoreModel().jsonToObj(it.asJsonObject)
                item.bookmarked = true
                list.add(item)
            }
            emit(list)
        } ?: run {
            emit(ArrayList<StoreModel>())
        }
    }.flowOn(Dispatchers.IO)

}