package pnu.hakathon.anyone.repository

import kotlinx.coroutines.flow.Flow
import pnu.hakathon.anyone.entity.StoreModel

interface MapRepository {
    suspend fun getStoreList(categoryID: String, lat: Double, lng: Double): Flow<List<StoreModel>>
}