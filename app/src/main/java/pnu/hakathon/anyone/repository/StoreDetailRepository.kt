package pnu.hakathon.anyone.repository

interface StoreDetailRepository {
    suspend fun setBookmark(userID: String, storeID: String, checked: Boolean, onSuccess: () -> Unit, onError: () -> Unit)
}