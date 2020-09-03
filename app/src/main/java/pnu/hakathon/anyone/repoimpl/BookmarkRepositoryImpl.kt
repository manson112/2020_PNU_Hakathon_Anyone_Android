package pnu.hakathon.anyone.repoimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.network.ReqBookmark
import pnu.hakathon.anyone.network.RetrofitService
import pnu.hakathon.anyone.repository.BookmarkRepository

class BookmarkRepositoryImpl(
    private val webService: RetrofitService
) : BookmarkRepository {
    override suspend fun getBookmarksFromServer(userID: String, onSuccess:() -> Unit, onError:() -> Unit) = flow {
        val response = webService.requestBookmark(ReqBookmark(userID).toMap())
        if (response.code != 200) {
            emit(ArrayList<StoreModel>())
            onError()
        } else {
            response.responseData?.let { arr ->
                val list = ArrayList<StoreModel>()
                arr.forEach {
                    val item = StoreModel().jsonToObj(it.asJsonObject)
                    item.bookmarked = true
                    list.add(item)
                }
                emit(list)
                onSuccess()
            } ?: run {
                emit(ArrayList<StoreModel>())
                onSuccess()
            }
        }
    }.flowOn(Dispatchers.IO)

}