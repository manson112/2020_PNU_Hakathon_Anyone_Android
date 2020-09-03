package pnu.hakathon.anyone.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.repository.SearchRepository

class SearchViewModel(private val repo: SearchRepository) : ViewModel() {
    var searchQuery: MutableLiveData<String> = MutableLiveData()
    var searchResults: LiveData<List<StoreModel>>
    var isLoading = MutableLiveData(false)
    var categoryID: String = "1"

    init {
        searchResults = searchQuery.switchMap {
            liveData<List<StoreModel>>(viewModelScope.coroutineContext + Dispatchers.IO) {
                isLoading.postValue(true)
                emitSource(repo.getSearchResults(categoryID, it, {
                    isLoading.postValue(true)
                }, {
                    isLoading.postValue(false)
                }).asLiveData())
            }
        }
    }

    @MainThread
    fun setQuery(query: String) {
        searchQuery.value = query
    }
}