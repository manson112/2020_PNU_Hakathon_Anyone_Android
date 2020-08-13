package pnu.hakathon.anyone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pnu.hakathon.anyone.entity.SearchHistory
import pnu.hakathon.anyone.localdb.AppDatabase
import pnu.hakathon.anyone.model.SearchModel
import pnu.hakathon.anyone.repository.SearchHistoryRepository

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SearchHistoryRepository
    val searchHistories: LiveData<List<SearchHistory>>
    var searchResults: MutableLiveData<List<SearchModel>> = MutableLiveData()

    init {
        val searchHistoryDao =
            AppDatabase.getDatabase(application, viewModelScope).searchHistoryDao()
        repository = SearchHistoryRepository(
            searchHistoryDao
        )
        searchHistories = repository.allSearchHistories
    }

    fun insert(searchHistory: SearchHistory) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(searchHistory)
    }

    fun setDummy() {
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

        searchResults.value = list
    }
}