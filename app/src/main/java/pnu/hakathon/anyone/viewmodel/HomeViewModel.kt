package pnu.hakathon.anyone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pnu.hakathon.anyone.model.HomeFragmentItem
import pnu.hakathon.anyone.model.HomeFragmentItemOne
import pnu.hakathon.anyone.model.HomeFragmentItemThree
import pnu.hakathon.anyone.model.HomeFragmentItemTwo

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    var list1 = HomeFragmentItem()
    var list2 = HomeFragmentItem()
    var list3 = HomeFragmentItem()
    var list4 = HomeFragmentItem()

    fun setA() {
        val list = ArrayList<HomeFragmentItemOne>()
        list.add(
            HomeFragmentItemOne(
                0,
                "SATISFACTORY",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/440px-Image_created_with_a_mobile_phone.png"
            )
        )
        list.add(
            HomeFragmentItemOne(
                1,
                "카페 꿈을 살다",
                "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-260nw-1048185397.jpg"
            )
        )
        list.add(
            HomeFragmentItemOne(
                2,
                "카페 안집",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"
            )
        )
        list.add(
            HomeFragmentItemOne(
                3,
                "머스마롱",
                "https://cdn.eso.org/images/thumb300y/eso1907a.jpg"
            )
        )
        list1.list1.value = list
        val list2 = ArrayList<HomeFragmentItemTwo>()
        list2.add(
            HomeFragmentItemTwo(
                0,
                "모구모구 베이커리",
                10,
                20,
                "https://s.ftcdn.net/v2013/pics/all/curated/RKyaEDwp8J7JKeZWQPuOVWvkUjGQfpCx_cover_580.jpg?r=1a0fc22192d0c808b8bb2b9bcfbf4a45b1793687"
            )
        )
        list2.add(
            HomeFragmentItemTwo(
                1,
                "카페 호밀",
                10,
                20,
                "https://image.shutterstock.com/image-photo/colorful-flower-on-dark-tropical-260nw-721703848.jpg"
            )
        )
        list2.add(
            HomeFragmentItemTwo(
                2,
                "카페 드 팽",
                10,
                20,
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"
            )
        )
        list2.add(
            HomeFragmentItemTwo(
                3,
                "노스 커피 4호점",
                10,
                20,
                "https://www.lokeshdhakar.com/projects/lightbox2/images/image-3.jpg"
            )
        )
        this.list1.list2.value = list2

        val list3 = ArrayList<HomeFragmentItemThree>()
        list3.add(
            HomeFragmentItemThree(
                0,
                "비오는 날, 달짝지근하게",
                "https://images.unsplash.com/photo-1497515114629-f71d768fd07c?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                "부산광역시 장전동"
            )
        )
        list3.add(
            HomeFragmentItemThree(
                1,
                "무료한 일상의 탈출",
                "data:image/jpeg;base64,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",
                "부산광역시 구서동"
            )
        )
        list3.add(
            HomeFragmentItemThree(
                2,
                "선선한 저녁과 센치한 카페",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Homemade_Dalgona_Coffee.jpg/1200px-Homemade_Dalgona_Coffee.jpg",
                "부산광역시 장전동"
            )
        )
        this.list1.list3.value = list3
    }

    fun setB() {
        val list = ArrayList<HomeFragmentItemOne>()
        list.add(
            HomeFragmentItemOne(
                0,
                "SATISFACTORY",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/440px-Image_created_with_a_mobile_phone.png"
            )
        )
        list.add(
            HomeFragmentItemOne(
                1,
                "카페 꿈을 살다",
                "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-260nw-1048185397.jpg"
            )
        )
        list.add(
            HomeFragmentItemOne(
                2,
                "카페 안집",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"
            )
        )
        list.add(
            HomeFragmentItemOne(
                3,
                "머스마롱",
                "https://cdn.eso.org/images/thumb300y/eso1907a.jpg"
            )
        )
        val list2 = ArrayList<HomeFragmentItemTwo>()
        list2.add(
            HomeFragmentItemTwo(
                0,
                "모구모구 베이커리",
                10,
                20,
                "https://s.ftcdn.net/v2013/pics/all/curated/RKyaEDwp8J7JKeZWQPuOVWvkUjGQfpCx_cover_580.jpg?r=1a0fc22192d0c808b8bb2b9bcfbf4a45b1793687"
            )
        )
        list2.add(
            HomeFragmentItemTwo(
                1,
                "카페 호밀",
                10,
                20,
                "https://image.shutterstock.com/image-photo/colorful-flower-on-dark-tropical-260nw-721703848.jpg"
            )
        )
        list2.add(
            HomeFragmentItemTwo(
                2,
                "카페 드 팽",
                10,
                20,
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"
            )
        )
        list2.add(
            HomeFragmentItemTwo(
                3,
                "노스 커피 4호점",
                10,
                20,
                "https://www.lokeshdhakar.com/projects/lightbox2/images/image-3.jpg"
            )
        )

        val list3 = ArrayList<HomeFragmentItemThree>()
        list3.add(
            HomeFragmentItemThree(
                0,
                "비오는 날, 달짝지근하게",
                "https://images.unsplash.com/photo-1497515114629-f71d768fd07c?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                "부산광역시 장전동"
            )
        )
        list3.add(
            HomeFragmentItemThree(
                1,
                "무료한 일상의 탈출",
                "data:image/jpeg;base64,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",
                "부산광역시 구서동"
            )
        )
        list3.add(
            HomeFragmentItemThree(
                2,
                "선선한 저녁과 센치한 카페",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Homemade_Dalgona_Coffee.jpg/1200px-Homemade_Dalgona_Coffee.jpg",
                "부산광역시 장전동"
            )
        )
        this.list2.list1.value = list
        this.list2.list2.value = list2
        this.list2.list3.value = list3
    }

    fun setC() {
        val list = ArrayList<HomeFragmentItemOne>()
        list.add(
            HomeFragmentItemOne(
                0,
                "SATISFACTORY",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/440px-Image_created_with_a_mobile_phone.png"
            )
        )
        list.add(
            HomeFragmentItemOne(
                1,
                "카페 꿈을 살다",
                "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-260nw-1048185397.jpg"
            )
        )
        list.add(
            HomeFragmentItemOne(
                2,
                "카페 안집",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"
            )
        )
        list.add(
            HomeFragmentItemOne(
                3,
                "머스마롱",
                "https://cdn.eso.org/images/thumb300y/eso1907a.jpg"
            )
        )
        val list2 = ArrayList<HomeFragmentItemTwo>()
        list2.add(
            HomeFragmentItemTwo(
                0,
                "모구모구 베이커리",
                10,
                20,
                "https://s.ftcdn.net/v2013/pics/all/curated/RKyaEDwp8J7JKeZWQPuOVWvkUjGQfpCx_cover_580.jpg?r=1a0fc22192d0c808b8bb2b9bcfbf4a45b1793687"
            )
        )
        list2.add(
            HomeFragmentItemTwo(
                1,
                "카페 호밀",
                10,
                20,
                "https://image.shutterstock.com/image-photo/colorful-flower-on-dark-tropical-260nw-721703848.jpg"
            )
        )
        list2.add(
            HomeFragmentItemTwo(
                2,
                "카페 드 팽",
                10,
                20,
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"
            )
        )
        list2.add(
            HomeFragmentItemTwo(
                3,
                "노스 커피 4호점",
                10,
                20,
                "https://www.lokeshdhakar.com/projects/lightbox2/images/image-3.jpg"
            )
        )

        val list3 = ArrayList<HomeFragmentItemThree>()
        list3.add(
            HomeFragmentItemThree(
                0,
                "비오는 날, 달짝지근하게",
                "https://images.unsplash.com/photo-1497515114629-f71d768fd07c?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                "부산광역시 장전동"
            )
        )
        list3.add(
            HomeFragmentItemThree(
                1,
                "무료한 일상의 탈출",
                "data:image/jpeg;base64,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",
                "부산광역시 구서동"
            )
        )
        list3.add(
            HomeFragmentItemThree(
                2,
                "선선한 저녁과 센치한 카페",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Homemade_Dalgona_Coffee.jpg/1200px-Homemade_Dalgona_Coffee.jpg",
                "부산광역시 장전동"
            )
        )
        this.list3.list1.value = list
        this.list3.list2.value = list2
        this.list3.list3.value = list3
    }

    fun setD() {
        val list = ArrayList<HomeFragmentItemOne>()
        list.add(
            HomeFragmentItemOne(
                0,
                "SATISFACTORY",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/440px-Image_created_with_a_mobile_phone.png"
            )
        )
        list.add(
            HomeFragmentItemOne(
                1,
                "카페 꿈을 살다",
                "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-260nw-1048185397.jpg"
            )
        )
        list.add(
            HomeFragmentItemOne(
                2,
                "카페 안집",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"
            )
        )
        list.add(
            HomeFragmentItemOne(
                3,
                "머스마롱",
                "https://cdn.eso.org/images/thumb300y/eso1907a.jpg"
            )
        )
        val list2 = ArrayList<HomeFragmentItemTwo>()
        list2.add(
            HomeFragmentItemTwo(
                0,
                "모구모구 베이커리",
                10,
                20,
                "https://s.ftcdn.net/v2013/pics/all/curated/RKyaEDwp8J7JKeZWQPuOVWvkUjGQfpCx_cover_580.jpg?r=1a0fc22192d0c808b8bb2b9bcfbf4a45b1793687"
            )
        )
        list2.add(
            HomeFragmentItemTwo(
                1,
                "카페 호밀",
                10,
                20,
                "https://image.shutterstock.com/image-photo/colorful-flower-on-dark-tropical-260nw-721703848.jpg"
            )
        )
        list2.add(
            HomeFragmentItemTwo(
                2,
                "카페 드 팽",
                10,
                20,
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"
            )
        )
        list2.add(
            HomeFragmentItemTwo(
                3,
                "노스 커피 4호점",
                10,
                20,
                "https://www.lokeshdhakar.com/projects/lightbox2/images/image-3.jpg"
            )
        )

        val list3 = ArrayList<HomeFragmentItemThree>()
        list3.add(
            HomeFragmentItemThree(
                0,
                "비오는 날, 달짝지근하게",
                "https://images.unsplash.com/photo-1497515114629-f71d768fd07c?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                "부산광역시 장전동"
            )
        )
        list3.add(
            HomeFragmentItemThree(
                1,
                "무료한 일상의 탈출",
                "data:image/jpeg;base64,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",
                "부산광역시 구서동"
            )
        )
        list3.add(
            HomeFragmentItemThree(
                2,
                "선선한 저녁과 센치한 카페",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Homemade_Dalgona_Coffee.jpg/1200px-Homemade_Dalgona_Coffee.jpg",
                "부산광역시 장전동"
            )
        )
        this.list4.list1.value = list
        this.list4.list2.value = list2
        this.list4.list3.value = list3
    }
}