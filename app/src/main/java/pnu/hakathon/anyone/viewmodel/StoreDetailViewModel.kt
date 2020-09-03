package pnu.hakathon.anyone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.repository.StoreDetailRepository
import timber.log.Timber

class StoreDetailViewModel(private val repo: StoreDetailRepository) : ViewModel() {
    var bookmarked = MutableLiveData(false)
    var store: MutableLiveData<StoreModel> = MutableLiveData(StoreModel())

    fun setBookmark(isChecked: Boolean) {
        CoroutineScope(viewModelScope.coroutineContext + Dispatchers.IO). launch {
            repo.setBookmark("1", store.value!!.id.toString(), isChecked, {
                CoroutineScope(Dispatchers.Main).launch {
                    bookmarked.value = isChecked
                }
            }, {
                Timber.d("Bookmark putting error")
            })
        }
    }
    fun setStore(st: StoreModel) {
        store.value = st
    }
}