package pnu.hakathon.anyone.repository

import kotlinx.coroutines.flow.Flow
import pnu.hakathon.anyone.entity.StoreModel

interface BookmarkRepository {
    suspend fun getBookmarksFromServer(userID: String): Flow<List<StoreModel>>
}