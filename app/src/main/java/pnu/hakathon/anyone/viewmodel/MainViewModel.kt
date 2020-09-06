package pnu.hakathon.anyone.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.repository.MainRepository

class MainViewModel(private val repo: MainRepository): ViewModel() {
    private var _address = MutableLiveData<String>()
    private var _curLoc: MutableLiveData<Loc> = MutableLiveData(Loc())
    private var _categoryID: String = ""
    private var _categoryName: String = ""
    private var _locationUpdated: MutableLiveData<Boolean> = MutableLiveData(false)

    val isLoading = MutableLiveData(true)

    val address: LiveData<String>
        get() =_address
    val curLoc: LiveData<Loc>
        get() = _curLoc
    val categoryID: String
        get() = _categoryID
    val categoryName: String
        get() = _categoryName
    val locationUpdated: LiveData<Boolean>
        get() = _locationUpdated

    var stores: LiveData<List<StoreModel>>
    var store_quiet: LiveData<List<StoreModel>>
    var store_clean: LiveData<List<StoreModel>>
    var store_noisy: LiveData<List<StoreModel>>
    var store_kind: LiveData<List<StoreModel>>

    init {
        stores = address.switchMap {
            isLoading.postValue(true)
            liveData<List<StoreModel>>(viewModelScope.coroutineContext + Dispatchers.IO) {
                emitSource(repo.getStores(categoryID, curLoc.value!!.lat, curLoc.value!!.lng, {isLoading.postValue(false)}, {isLoading.postValue(false)}).asLiveData())
//                emitSource(repo.getStores(categoryID, 35.23177955501981, 129.08447619178358, {
//                    isLoading.postValue(false)
//                }, {
//                    isLoading.postValue(false)
//                }).asLiveData())
            }
        }
        store_quiet = stores.map { s -> s.filter { i -> i.noise < 3 } }
        store_clean = stores.map { s -> s.filter { i -> i.cleanliness >= 3 } }
        store_noisy = stores.map { s -> s.filter { i -> i.noise >= 3 } }
        store_kind = stores.map { s -> s.filter { i -> i.kindness >= 3 } }

    }

    fun setCategory(categoryID: String, categoryName: String) {
        _categoryID = categoryID
        _categoryName = categoryName
    }

    fun setLatLng(lat: Double, lng: Double) {
        _curLoc.value = Loc(lat, lng)
        _locationUpdated.value = true
    }

    fun setAddress(address: String?) {
        address?.let {
            this._address.value = address
        }
    }
}