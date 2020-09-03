package pnu.hakathon.anyone.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import net.daum.mf.map.api.MapPoint
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.repository.MapRepository
import timber.log.Timber

class MapViewModel(var savedStateHandle: SavedStateHandle, val repo: MapRepository) : ViewModel() {
    var categoryID: String = "1"
    var locLiveData = MutableLiveData(Loc())
    var list: LiveData<List<StoreModel>>
    var isLoading = MutableLiveData(true)

    init {
        Timber.d("${savedStateHandle.get<Double>("lat")}, ${savedStateHandle.get<Double>("lng")}")
        list = locLiveData.switchMap {
            isLoading.postValue(true)
            Timber.d("LIST INIT")
            liveData<List<StoreModel>>(viewModelScope.coroutineContext + Dispatchers.IO) {
                emitSource(repo.getStoreList(categoryID, it.lat, it.lng, {isLoading.postValue(false)}, {isLoading.postValue(false)}).asLiveData())
            }
        }
    }

    fun setLatLng(loc: Loc) {
        locLiveData.value = loc
    }

    fun getCenterMapPoint(): MapPoint{
        return MapPoint.mapPointWithGeoCoord(locLiveData.value!!.lat, locLiveData.value!!.lng)
    }


}