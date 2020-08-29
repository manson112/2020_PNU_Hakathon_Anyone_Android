package pnu.hakathon.anyone.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import pnu.hakathon.anyone.entity.NearStore
import pnu.hakathon.anyone.model.HomeHashItem
import pnu.hakathon.anyone.model.StoreModel
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

    var recommend :LiveData<List<NearStore>> = MutableLiveData()

    fun setLatLng(lat: Double, lng: Double) {
        this.lat = lat
        this.lng = lng
        getNewList()
    }
    fun getNewList() {
        recommend = repo.getStoresNearBy(categoryID, lat, lng)
    }

    fun requestHome(categoryID: String) {
        StoreModel.requestHome(categoryID, {
            // Success
            Log.i("TAG", it?.responseData.toString())

            it?.responseData?.let { arr ->
                val q = ArrayList<HomeHashItem>()
                val n = ArrayList<HomeHashItem>()
                val k = ArrayList<HomeHashItem>()
                val c = ArrayList<HomeHashItem>()

                for (i in 0 until arr.size()) {
                    val obj = arr[i].asJsonObject
                    val item = HomeHashItem()
                    obj.get("id")?.let { id ->
                        item.id = id.asInt
                    }
                    obj.get("image")?.let { image ->
                        item.imageURL = image.asString
                    }
                    obj.get("name")?.let { name ->
                        item.name = name.asString
                        Log.d("AAAAAA", name.asString)
                    }
                    obj.get("noise")?.let { noise ->
                        Log.d("AAAAAA", noise.asString)
                        if (noise.asFloat <= 3) {
                            Log.d("AAAAAA", "QUITE")
                            q.add(item)
                        } else {
                            Log.d("AAAAAA", "NOISY")
                            n.add(item)
                        }
                    }
                    obj.get("cleanliness")?.let { clean ->
                        Log.d("AAAAAA", clean.asString)
                        if (clean.asFloat > 3) {
                            Log.d("AAAAAA", "CLEAN")
                            c.add(item)
                        }
                    }
                    obj.get("kindness")?.let { kind ->
                        Log.d("AAAAAA", kind.asString)
                        if (kind.asFloat > 3) {
                            Log.d("AAAAAA", "KIND")
                            k.add(item)
                        }
                    }
                }
                this.noisy.value = n
                this.clean.value = c
                this.kind.value = k
                this.quite.value = q
            }
        }, {
            // Failure
        })
    }

}