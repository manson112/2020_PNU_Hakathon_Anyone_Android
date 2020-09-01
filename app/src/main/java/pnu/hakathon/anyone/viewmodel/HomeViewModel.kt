package pnu.hakathon.anyone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.model.HomeHashItem
import pnu.hakathon.anyone.repository.HomeRepository

class HomeViewModel(savedStateHandle: SavedStateHandle, private val repo: HomeRepository) :
    ViewModel() {
    var quite = MutableLiveData<List<HomeHashItem>>()
    var noisy = MutableLiveData<List<HomeHashItem>>()
    var kind  = MutableLiveData<List<HomeHashItem>>()
    var clean = MutableLiveData<List<HomeHashItem>>()
    var categoryID: String = "1"

    var lat: Double = savedStateHandle["lat"] ?: 35.23177955501981
    var lng: Double = savedStateHandle["lng"] ?: 129.08447619178358

    var recommend :LiveData<List<StoreModel>> = MutableLiveData()

    init {

    }

    fun setLatLng(lat: Double, lng: Double) {
        this.lat = lat
        this.lng = lng
        getNewList()
    }
    fun getNewList() {
//        recommend = repo.getStoresNearBy(categoryID, lat, lng).asLiveData()
    }
}