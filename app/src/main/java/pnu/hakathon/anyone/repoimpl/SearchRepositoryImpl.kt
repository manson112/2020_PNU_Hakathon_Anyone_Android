package pnu.hakathon.anyone.repoimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.network.RetrofitService
import pnu.hakathon.anyone.repository.SearchRepository

class SearchRepositoryImpl(private val webService: RetrofitService): SearchRepository {
    override suspend fun getSearchResults(
        categoryID: String,
        query: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) = flow {
        val map = hashMapOf<String, String>()
        map["categoryID"] = categoryID
        map["searchQuery"] = query
        val response = webService.requestSearch(map)
        if (response.code != 200) {
            onError()
            emit(emptyList<StoreModel>())
        } else {
            response.responseData?.let { arr ->
                val list = mutableListOf<StoreModel>()
                arr.forEach {
                    list.add(StoreModel().jsonToObj(it.asJsonObject))
                }
                emit(list)
                onSuccess()
            } ?: run {
                emit(emptyList<StoreModel>())
                onSuccess()
            }
        }
    }.flowOn(Dispatchers.IO)
}