package pnu.hakathon.anyone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import net.daum.mf.map.api.MapPoint
import pnu.hakathon.anyone.entity.MapStoreModel
import pnu.hakathon.anyone.repository.MapRepository

class MapViewModel(var savedStateHandle: SavedStateHandle, val repo: MapRepository) : ViewModel() {
    var categoryID: String = "1"
    var lat: MutableLiveData<Double>? =
        MutableLiveData(savedStateHandle["lat"] ?: 35.23177955501981)
    var lng: MutableLiveData<Double>? =
        MutableLiveData(savedStateHandle["lng"] ?: 129.08447619178358)
    var list: LiveData<List<MapStoreModel>> = MutableLiveData()
    var currentAddress: MutableLiveData<String> = MutableLiveData("")
    var isFindingLocation = MutableLiveData<Boolean>(true)
    var needToUpdate = true

    fun startLoading() {
        isFindingLocation.value = true
    }

    fun stopLoading() {
        isFindingLocation.value = false
    }

    fun getLat(): Double {
        return lat?.value!!
    }

    fun getLng(): Double {
        return lng?.value!!
    }

    fun getCenterMapPoint(): MapPoint {
        return MapPoint.mapPointWithGeoCoord(getLat(), getLng())
    }

    fun getNewList() {
        list = repo.getStoreList(categoryID, lat?.value!!, lng?.value!!)
        noNeedToUpdate()
    }

    fun noNeedToUpdate() {
        needToUpdate = false
    }

    fun needUpdate() {
        needToUpdate = true
    }


}