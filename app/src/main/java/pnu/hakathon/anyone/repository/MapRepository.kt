package pnu.hakathon.anyone.repository

import androidx.lifecycle.LiveData
import pnu.hakathon.anyone.entity.MapStoreModel

interface MapRepository {
    fun getStoreList(categoryID: String, lat: Double, lng: Double): LiveData<List<MapStoreModel>>
    fun getStoreListFromServer(categoryID: String, lat: Double, lng: Double)
}