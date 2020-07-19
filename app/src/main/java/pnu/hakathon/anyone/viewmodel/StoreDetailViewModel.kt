package pnu.hakathon.anyone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import pnu.hakathon.anyone.model.MenuModel
import pnu.hakathon.anyone.model.StoreDetail
import pnu.hakathon.anyone.model.StoreModel

class StoreDetailViewModel(application: Application) : AndroidViewModel(application) {
    val store = MutableLiveData<StoreDetail>()
    val menu = MutableLiveData<List<MenuModel>>()
    var storeID: String? = null

    fun requestStoreInfo() {
        storeID?.let { stID ->
            StoreModel.requestStoreInfo(stID, {
                it?.responseData?.let { arr ->
                    store.value = StoreDetail().jsonToObj(arr[0].asJsonObject)
                }
            }, {

            })
        }
    }

    fun setDummyMenu() {
        val list = ArrayList<MenuModel>()

        list.add(
            MenuModel(
                "한라봉 차",
                "3000",
                "https://www.mapleandmango.com/wp-content/uploads/2019/10/Single-Cup-Of-Orange-Spice-Mulled-Tea.jpg"
            )
        )
        list.add(
            MenuModel(
                "아이스 티",
                "3400",
                "https://lilluna.com/wp-content/uploads/2018/12/homemade-bread-resize-6-500x500.jpg"
            )
        )
        list.add(
            MenuModel(
                "아메리카노",
                "2000",
                "data:image/jpeg;base64,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"
            )
        )
        list.add(
            MenuModel(
                "과일차",
                "3800",
                "https://lilluna.com/wp-content/uploads/2018/12/homemade-bread-resize-6-500x500.jpg"
            )
        )
        list.add(
            MenuModel(
                "마카롱",
                "2500",
                "https://lilluna.com/wp-content/uploads/2018/12/homemade-bread-resize-6-500x500.jpg"
            )
        )
        list.add(
            MenuModel(
                "크로와상",
                "2000",
                "https://lilluna.com/wp-content/uploads/2018/12/homemade-bread-resize-6-500x500.jpg"
            )
        )
        list.add(
            MenuModel(
                "프라푸치노",
                "5800",
                "https://upload.wikimedia.org/wikipedia/commons/4/45/A_small_cup_of_coffee.JPG"
            )
        )
        list.add(
            MenuModel(
                "카페라떼",
                "3100",
                "https://lilluna.com/wp-content/uploads/2018/12/homemade-bread-resize-6-500x500.jpg"
            )
        )
        list.add(
            MenuModel(
                "생과일주스",
                "3300",
                "https://lilluna.com/wp-content/uploads/2018/12/homemade-bread-resize-6-500x500.jpg"
            )
        )
        list.add(
            MenuModel(
                "에스프레소",
                "2500",
                "https://lilluna.com/wp-content/uploads/2018/12/homemade-bread-resize-6-500x500.jpg"
            )
        )
        list.add(
            MenuModel(
                "페퍼민트",
                "3000",
                "https://lilluna.com/wp-content/uploads/2018/12/homemade-bread-resize-6-500x500.jpg"
            )
        )

        menu.value = list
    }


}