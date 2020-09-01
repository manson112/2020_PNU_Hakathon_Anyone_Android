package pnu.hakathon.anyone.repository

import kotlinx.coroutines.flow.Flow
import pnu.hakathon.anyone.entity.StoreModel

interface HomeRepository {
    //    fun getHashItems(categoryID: String, obj: String): LiveData<List<HomeHashItem>>
    suspend fun getStoresNearBy(categoryID: String, lat: Double, lng: Double): Flow<List<StoreModel>>
}