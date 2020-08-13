package pnu.hakathon.anyone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import net.daum.mf.map.api.MapPoint
import pnu.hakathon.anyone.model.SearchModel
import pnu.hakathon.anyone.repository.MapRepository

class MapViewModel(savedStateHandle: SavedStateHandle, val repo: MapRepository) : ViewModel() {
    val categoryID: String = savedStateHandle["categoryID"] ?: "1"
    var lat: MutableLiveData<Double>? =
        MutableLiveData(savedStateHandle["lat"] ?: 35.23177955501981)
    var lng: MutableLiveData<Double>? =
        MutableLiveData(savedStateHandle["lng"] ?: 129.08447619178358)
    var list = repo.getStoreList(categoryID, lat?.value!!, lng?.value!!)
    var currentAddress: MutableLiveData<String> = MutableLiveData("")

    var isFindingLocation = MutableLiveData<Boolean>(true)
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

    fun setDummyData() {
        val list = ArrayList<SearchModel>()

        list.add(
            SearchModel(
                0,
                "search_image_sample1",
                "부산 커피",
                "#조용한 #쾌적한",
                "부산 금정구 장전온천천로 93",
                30,
                2
            )
        )
        list.add(
            SearchModel(
                1,
                "search_image_sample2",
                "카페 해뮴",
                "#조용한 #쾌적한",
                "부산 금정구 장전온천천로 93",
                40,
                2
            )
        )
        list.add(
            SearchModel(
                2,
                "search_image_sample3",
                "카페 유달리",
                "#조용한 #쾌적한",
                "부산 금정구 장전온천천로 93",
                20,
                2
            )
        )
        list.add(
            SearchModel(
                3,
                "search_image_sample4",
                "카페 비윤",
                "#조용한 #쾌적한",
                "부산 금정구 장전온천천로 93",
                10,
                2
            )
        )
        list.add(
            SearchModel(
                4,
                "search_image_sample5",
                "누자베스 414",
                "#조용한 #쾌적한",
                "부산 금정구 장전온천천로 93",
                35,
                2
            )
        )
        list.add(
            SearchModel(
                5,
                "search_image_sample6",
                "흐리멍텅 카페",
                "#조용한 #쾌적한",
                "부산 금정구 장전온천천로 93",
                22,
                2
            )
        )

//        this.list.value = list
    }

}