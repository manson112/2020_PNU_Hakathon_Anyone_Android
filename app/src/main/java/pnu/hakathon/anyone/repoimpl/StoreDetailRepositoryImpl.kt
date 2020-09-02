package pnu.hakathon.anyone.repoimpl

import pnu.hakathon.anyone.model.ReqSetBookmark
import pnu.hakathon.anyone.network.RetrofitService
import pnu.hakathon.anyone.repository.StoreDetailRepository
import timber.log.Timber

class StoreDetailRepositoryImpl(private val webService: RetrofitService): StoreDetailRepository {
    override suspend fun setBookmark(userID: String, storeID: String, checked: Boolean, onSuccess: () -> Unit, onError: () -> Unit) {
        val result = webService.requestSetBookmark(ReqSetBookmark(userID, storeID, checked).toMap())
        result.responseData?.let {
            if (result.code == 200) { onSuccess() }
            else {
                Timber.d("Fail on Server")
                onError()
            }
        } ?: run {
            Timber.d("Fail to set Bookmark")
            onError()
        }
    }
}