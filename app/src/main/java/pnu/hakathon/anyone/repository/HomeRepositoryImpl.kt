package pnu.hakathon.anyone.repository

import android.util.Log
import androidx.lifecycle.LiveData
import pnu.hakathon.anyone.localdb.NearStore
import pnu.hakathon.anyone.localdb.NearStoreDao
import pnu.hakathon.anyone.localdb.ReqNearStore
import pnu.hakathon.anyone.network.RetrofitService
import java.net.ConnectException
import java.util.concurrent.Executor

class HomeRepositoryImpl(
    private val webService: RetrofitService,
    private val executor: Executor,
    private val nearStoreDao: NearStoreDao
) : HomeRepository {
    override fun getStoresNearBy(
        categoryID: String,
        lat: Double,
        lng: Double
    ): LiveData<List<NearStore>> {
        getNearStoresFromServer(categoryID, lat, lng)
        return nearStoreDao.getStoresNearBy(categoryID, lat, lng)
    }

    fun getNearStoresFromServer(categoryID: String, lat: Double, lng: Double) {
        executor.execute {
            try {
                val response = webService.requestStoreNearMe(
                    ReqNearStore(
                        categoryID,
                        lat.toString(),
                        lng.toString()
                    ).toMap()
                ).execute()
                if (response.isSuccessful) {
                    response.body()?.responseData?.let { arr ->
                        for (i in 0 until arr.size()) {
                            nearStoreDao.insert(NearStore().jsonToObj(arr[i].asJsonObject))
                        }
                    }
                }
            } catch (e: ConnectException) {
                Log.e("getNearStoresFromServer", "Cannot connect to server")
            }
        }
    }
}