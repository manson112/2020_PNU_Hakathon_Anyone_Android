package pnu.hakathon.anyone.repoimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pnu.hakathon.anyone.dao.StoreListDao
import pnu.hakathon.anyone.entity.ReqMapStore
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.network.RetrofitService
import pnu.hakathon.anyone.repository.MapRepository
import timber.log.Timber

class MapRepositoryImpl(
    private val webService: RetrofitService,
    private val storeListDao: StoreListDao
) : MapRepository {
    override suspend fun getStoreList(
        categoryID: String,
        lat: Double,
        lng: Double,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) = flow {
        val response = webService.requestStoreMapNearMe(ReqMapStore(categoryID, lat.toString(), lng.toString()).toMap())
        Timber.d(response.toString())
        if (response.code != 200) {
            emit(ArrayList<StoreModel>())
            onError()
        } else {
            response.responseData?.let { arr ->
                val list = ArrayList<StoreModel>()
                arr.forEach {
                    list.add(StoreModel().jsonToObj(it.asJsonObject))
//                storeListDao.insert(StoreModel().jsonToObj(it.asJsonObject))
                }
//            val cos_lat = cos(Math.toRadians(lat)).toString()
//            val sin_lat = sin(Math.toRadians(lat)).toString()
//            val cos_lng = cos(Math.toRadians(lng)).toString()
//            val sin_lng = sin(Math.toRadians(lng)).toString()
//            val distance = cos(0.5 / 6317).toString()
//            val list = storeListDao.getStoresNear(
//                categoryID,
//                cos_lat,
//                sin_lat,
//                cos_lng,
//                sin_lng,
//                distance
//            )
                emit(list)
                onSuccess()
            } ?: run {
                emit(ArrayList<StoreModel>())
                onSuccess()
            }
        }
    }.flowOn(Dispatchers.IO)

}