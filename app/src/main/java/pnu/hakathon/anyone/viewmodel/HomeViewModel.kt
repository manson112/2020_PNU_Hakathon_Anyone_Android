package pnu.hakathon.anyone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import pnu.hakathon.anyone.model.HomeFragmentItemThree
import pnu.hakathon.anyone.model.HomeFragmentItemTwo
import pnu.hakathon.anyone.model.HomeHashItem

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    var quite = MutableLiveData<List<HomeHashItem>>()
    var noisy = MutableLiveData<List<HomeHashItem>>()
    var kind = MutableLiveData<List<HomeHashItem>>()
    var clean = MutableLiveData<List<HomeHashItem>>()

    var recommend1 = MutableLiveData<List<HomeFragmentItemTwo>>()
    var recommend2 = MutableLiveData<List<HomeFragmentItemThree>>()

    fun setDummyData() {
        val list = ArrayList<HomeHashItem>()
        list.add(
            HomeHashItem(
                0,
                "SATISFACTORY",
                "home_image_sample_1"
            )
        )
        list.add(
            HomeHashItem(
                1,
                "카페 꿈을 살다",
                "home_image_sample_2"
            )
        )
        list.add(
            HomeHashItem(
                2,
                "카페 안집",
                "home_image_sample_3"
            )
        )
        list.add(
            HomeHashItem(
                3,
                "머스마롱",
                "home_image_sample_4"
            )
        )

        quite.value = list
        noisy.value = list
        kind.value = list
        clean.value = list
    }

    fun setDummyData2() {
        val rec1 = ArrayList<HomeFragmentItemTwo>()
        rec1.add(HomeFragmentItemTwo(0, "이솝페이블", 7, 10, "home_image_sample_5"))
        rec1.add(HomeFragmentItemTwo(1, "카페 호밀", 7, 10, "home_image_sample_6"))

        this.recommend1.value = rec1

        val rec2 = ArrayList<HomeFragmentItemThree>()
        rec2.add(HomeFragmentItemThree(0, "비오는 날, 달짝지근하게", "home_image_sample_7", "부산광역시 장전동"))
        rec2.add(HomeFragmentItemThree(1, "무료한 일상의 탈출", "home_image_sample_8", "부산광역시 구서동"))
        rec2.add(HomeFragmentItemThree(2, "선선한 저녁과 센치한 카페", "home_image_sample_9", "부산광역시 장전동"))

        this.recommend2.value = rec2

    }
}