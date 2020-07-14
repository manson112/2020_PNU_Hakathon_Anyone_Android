package pnu.hakathon.anyone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import pnu.hakathon.anyone.model.AlbumItem
import pnu.hakathon.anyone.model.HashStore
import pnu.hakathon.anyone.model.RecommendedStore

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val recommended: MutableLiveData<List<RecommendedStore>> = MutableLiveData()

    //hash
    val quite: MutableLiveData<List<HashStore>> = MutableLiveData()
    val noisy: MutableLiveData<List<HashStore>> = MutableLiveData()
    val kind: MutableLiveData<List<HashStore>> = MutableLiveData()
    val clean: MutableLiveData<List<HashStore>> = MutableLiveData()

    //album
    val album: MutableLiveData<List<AlbumItem>> = MutableLiveData()

    fun setDummyData() {
        val list = ArrayList<RecommendedStore>()
        list.add(
            RecommendedStore(
                0,
                "SATISFACTORY",
                23,
                100,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/440px-Image_created_with_a_mobile_phone.png"
            )
        )
        list.add(
            RecommendedStore(
                1,
                "카페 꿈을 살다",
                7,
                100,
                "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-260nw-1048185397.jpg"
            )
        )
        list.add(
            RecommendedStore(
                2,
                "카페 안집",
                12,
                100,
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"
            )
        )
        list.add(
            RecommendedStore(
                3,
                "머스마롱",
                2,
                100,
                "https://cdn.eso.org/images/thumb300y/eso1907a.jpg"
            )
        )
        recommended.value = list

        val list2 = ArrayList<AlbumItem>()
        list2.add(
            AlbumItem(
                "맵고 짜거 단거",
                "맵고 짜고 단거 먹고 싶을 때 가는 카페",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQfLmgD1rGIId0zdgdYiCpSSOeerwE8guFpDg&usqp=CAU"
            )
        )
        list2.add(
            AlbumItem(
                "우울할 때",
                "우울한 날, 이런 카페는 어때요?",
                "https://tour.gwangju.go.kr/uploads/board/2018/01/201801120536524190.jpg"
            )
        )
        list2.add(
            AlbumItem(
                "따뜻한 카페",
                "따뜻함을 느끼고 싶을 때",
                "https://media-cdn.tripadvisor.com/media/photo-s/11/fa/54/9c/cbd.jpg"
            )
        )
        list2.add(
            AlbumItem(
                "흐에!",
                "단거 먹고 힘내자! 흐에!",
                "https://fun.cheonan.go.kr/wp-content/uploads/2020/02/20200110_181159.jpg"
            )
        )
        album.value = list2
    }

    fun setA() {
        val list2 = ArrayList<HashStore>()
        list2.add(
            HashStore(
                0,
                "모구모구 베이커리",
                "https://s.ftcdn.net/v2013/pics/all/curated/RKyaEDwp8J7JKeZWQPuOVWvkUjGQfpCx_cover_580.jpg?r=1a0fc22192d0c808b8bb2b9bcfbf4a45b1793687"
            )
        )
        list2.add(
            HashStore(
                1,
                "카페 호밀",
                "https://image.shutterstock.com/image-photo/colorful-flower-on-dark-tropical-260nw-721703848.jpg"
            )
        )
        list2.add(
            HashStore(
                2,
                "카페 드 팽",
                "https://www.gettyimages.com/gi-resources/images/500px/983794168.jpg"
            )
        )
        list2.add(
            HashStore(
                3,
                "노스 커피 4호점",
                "https://www.lokeshdhakar.com/projects/lightbox2/images/image-3.jpg"
            )
        )
        quite.value = list2
    }

    fun setB() {
        val list3 = ArrayList<HashStore>()
        list3.add(
            HashStore(
                0,
                "브라운 커피",
                "https://filedn.com/ltOdFv1aqz1YIFhf4gTY8D7/ingus-info/BLOGS/Photography-stocks3/stock-photography-slider.jpg"
            )
        )
        list3.add(
            HashStore(
                1,
                "할리스 부산대점",
                "https://lh3.googleusercontent.com/proxy/UsYVH8IeiH0z7rA4hpxHq2Mn7kfUKmBZClixjpXE7rewNpMR4Z6fBtmyok2oLgvuRKwGiWp-lWkN2T12MElZXawNPWgVEwjc7lzsttP2IoTgddsm_GP4ZPrMbmI43z7hTvO_tgYmVKLbXi4E8VtFgFeDucZ3l44nZPeP_RCJhw"
            )
        )
        list3.add(
            HashStore(
                2,
                "투썸플레이스 부산대점",
                "https://lh3.googleusercontent.com/proxy/hhundKZ1WE5mnSMc1ypbsyHaTy58Xqu_Dq14vamLfDg06DUnQkfnlvSgObJpMR38fMiZSquPPvdvsvelWkx0N8U_hs7o3xpLb3ti39jY11QAwg"
            )
        )
        list3.add(
            HashStore(
                3,
                "뚜레쥬르",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTRkpcx3W2mDII0ID29ysXydNPwS8B7t-YQEw&usqp=CAU"
            )
        )
        noisy.value = list3
    }

    fun setC() {
        val list4 = ArrayList<HashStore>()
        list4.add(
            HashStore(
                0,
                "카페 드 팽",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRS3RvKBBZVhGHmmxJVy962wHIsCnDXrgjJ4Q&usqp=CAU"
            )
        )
        list4.add(
            HashStore(
                1,
                "카페 드 팽 2호점",
                "https://media-cdn.tripadvisor.com/media/photo-s/15/61/40/5d/notre-salle-de-restaurant.jpg"
            )
        )
        list4.add(
            HashStore(
                2,
                "브라운 커피",
                "https://mp-seoul-image-production-s3.mangoplate.com/47970/59123_14282035568104311143110?fit=around|738:738&crop=738:738;*,*&output-format=jpg&output-quality=80"
            )
        )
        kind.value = list4
    }

    fun setD() {
        val list5 = ArrayList<HashStore>()
        list5.add(HashStore(0, "카페 드 팽 2호점", "https://i.vimeocdn.com/video/665629871_940x540.jpg"))
        list5.add(
            HashStore(
                1,
                "파리바게트",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRZ7l4dJFhRZkMyL9FSb6UaINphnLQDUxTElA&usqp=CAU"
            )
        )
        list5.add(
            HashStore(
                2,
                "브라운 커피",
                "https://tour.gwangju.go.kr/uploads/board/2018/01/201801120539343910.jpg"
            )
        )
        list5.add(
            HashStore(
                3,
                "노스 커피 1호점",
                "https://i.pinimg.com/originals/dc/56/ae/dc56ae956d0b52a34d3a7456f13c2642.jpg"
            )
        )
        clean.value = list5
    }
}