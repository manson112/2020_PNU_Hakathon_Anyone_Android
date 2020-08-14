package pnu.hakathon.anyone.repoimpl

import android.util.Log
import androidx.lifecycle.LiveData
import pnu.hakathon.anyone.dao.MapStoreListDao
import pnu.hakathon.anyone.entity.MapStoreModel
import pnu.hakathon.anyone.entity.ReqMapStore
import pnu.hakathon.anyone.network.RetrofitService
import pnu.hakathon.anyone.repository.MapRepository
import java.net.ConnectException
import java.util.concurrent.Executor

class MapRepositoryImpl(
    private val webService: RetrofitService,
    private val executor: Executor,
    private val mapStoreListDao: MapStoreListDao
) : MapRepository {
    override fun getStoreList(
        categoryID: String,
        lat: Double,
        lng: Double
    ): LiveData<List<MapStoreModel>> {
        getStoreListFromServer(categoryID, lat, lng)
//        return mapStoreListDao.getMapStoreList(categoryID, lat, lng)
        return mapStoreListDao.getMapStore(categoryID)
    }

    override fun getStoreListFromServer(categoryID: String, lat: Double, lng: Double) {
        executor.execute {
            try {
                val response = webService.requestStoreMapNearMe(
                    ReqMapStore(
                        categoryID,
                        lat.toString(),
                        lng.toString()
                    ).toMap()
                ).execute()
                if (response.isSuccessful) {
                    response.body()?.responseData?.let { arr ->
                        mapStoreListDao.deleteAll()
                        for (i in 0 until arr.size()) {
                            mapStoreListDao.insert(
                                MapStoreModel().jsonToObj(arr[i].asJsonObject)
                            )
                        }
                    } ?: run {
                        mapStoreListDao.deleteAll()
                    }
                }
            } catch (e: ConnectException) {
                Log.e("getStoreListFromServer", "Cannot connect to server")
            }
        }
    }
}