package pnu.hakathon.anyone.repository

import android.util.Log
import androidx.lifecycle.LiveData
import pnu.hakathon.anyone.localdb.MapStoreListDao
import pnu.hakathon.anyone.localdb.MapStoreModel
import pnu.hakathon.anyone.localdb.ReqMapStore
import pnu.hakathon.anyone.network.RetrofitService
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
        return mapStoreListDao.getMapStoreList(categoryID, lat, lng)
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
                        for (i in 0 until arr.size()) {
                            mapStoreListDao.insert(MapStoreModel().jsonToObj(arr[i].asJsonObject))
                        }
                    }
                }
            } catch (e: ConnectException) {
                Log.e("getStoreListFromServer", "Cannot connect to server")
            }
        }
    }
}