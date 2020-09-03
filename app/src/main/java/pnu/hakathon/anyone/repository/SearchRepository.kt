package pnu.hakathon.anyone.repository

import kotlinx.coroutines.flow.Flow
import pnu.hakathon.anyone.entity.StoreModel

interface SearchRepository {
    suspend fun getSearchResults(categoryID: String, query: String, onSuccess: () -> Unit, onError: () -> Unit): Flow<List<StoreModel>>
}