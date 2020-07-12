package pnu.hakathon.anyone.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import pnu.hakathon.anyone.model.StoreDetail
import pnu.hakathon.anyone.model.StoreModel

class StoreDetailViewModel(application: Application) : AndroidViewModel(application) {
    val store = MutableLiveData<StoreDetail>()
    var storeID: String? = null

    fun requestStoreInfo() {
        storeID?.let { stID ->
            StoreModel.requestStoreInfo(stID, {
                it?.responseData?.let { arr ->
                    Log.d("REQREQ", arr[0].toString())
                    store.value = StoreDetail().jsonToObj(arr[0].asJsonObject)
                }
            }, {

            })
        }
    }


}