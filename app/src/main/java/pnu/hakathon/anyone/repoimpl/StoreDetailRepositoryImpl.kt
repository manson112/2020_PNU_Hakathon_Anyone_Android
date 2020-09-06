package pnu.hakathon.anyone.repoimpl

import pnu.hakathon.anyone.network.ReqSetBookmark
import pnu.hakathon.anyone.network.RetrofitService
import pnu.hakathon.anyone.repository.StoreDetailRepository
import timber.log.Timber

class StoreDetailRepositoryImpl(private val webService: RetrofitService): StoreDetailRepository {
    override suspend fun setBookmark(userID: String, storeID: String, checked: Boolean, onSuccess: () -> Unit, onError: () -> Unit) {
        val result = webService.requestSetBookmark(ReqSetBookmark(userID, storeID, checked).toMap())
        if (result.code == 200) { onSuccess() }
        else {
            Timber.d("Fail to set Bookmark")
            onError()
        }
    }

    override suspend fun getStoreData(
        storeID: String,
        onSuccess: (current: Int) -> Unit,
        onError: () -> Unit
    ) {
        val result = webService.requestGetCurrentSeat(hashMapOf<String, String>("storeID" to storeID))
        if (result.code != 200) {
            onError()
        } else {
            result.responseData?.let { arr ->
                Timber.d("$arr")
                var current = 0
                arr.forEach { current = it.asInt }
                onSuccess(current)
            }
        }

    }
}