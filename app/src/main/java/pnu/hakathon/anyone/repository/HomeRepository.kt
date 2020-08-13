package pnu.hakathon.anyone.repository

import androidx.lifecycle.LiveData
import pnu.hakathon.anyone.localdb.NearStore

interface HomeRepository {
    //    fun getHashItems(categoryID: String, obj: String): LiveData<List<HomeHashItem>>
    fun getStoresNearBy(categoryID: String, lat: Double, lng: Double): LiveData<List<NearStore>>
}