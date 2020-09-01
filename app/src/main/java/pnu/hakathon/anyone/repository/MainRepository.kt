package pnu.hakathon.anyone.repository

import kotlinx.coroutines.flow.Flow
import pnu.hakathon.anyone.entity.StoreModel


interface MainRepository {
//    suspend fun getStoreListFromServer(categoryID: String, lat: Double, lng: Double, onSuccess: () -> Unit, onError: (String) -> Unit)
    suspend fun getStores(categoryID: String, lat: Double, lng: Double, onSuccess: () -> Unit, onError: (String) -> Unit): Flow<List<StoreModel>>
}