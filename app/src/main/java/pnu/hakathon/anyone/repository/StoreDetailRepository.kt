package pnu.hakathon.anyone.repository

interface StoreDetailRepository {
    suspend fun setBookmark(userID: String, storeID: String, checked: Boolean, onSuccess: () -> Unit, onError: () -> Unit)
    suspend fun getStoreData(storeID: String, onSuccess: (current: Int) -> Unit, onError: () -> Unit)
}