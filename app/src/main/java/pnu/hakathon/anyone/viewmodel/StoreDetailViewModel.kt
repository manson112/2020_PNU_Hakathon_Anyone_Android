package pnu.hakathon.anyone.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.repository.StoreDetailRepository
import timber.log.Timber
import kotlin.math.roundToInt

class StoreDetailViewModel(private val repo: StoreDetailRepository) : ViewModel() {
    var bookmarked = MutableLiveData(false)
    var store: MutableLiveData<StoreModel> = MutableLiveData(StoreModel())
    var text = MutableLiveData<String>("")

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

    fun getCurrentSeat() {
        CoroutineScope(viewModelScope.coroutineContext + Dispatchers.IO).launch {
            repo.getStoreData(store.value?.id!!.toString(), {
                store.value?.current = it
                setText()
            }, {
            })
        }
    }

    fun setText() {
        store.value?.let {
            text.postValue("현재 ${((it.current.toDouble() / it.total)*100).roundToInt()}%예요.\n완충까지 ${it.total - it.current}자리 남았어요!")
        }
    }

    @MainThread
    fun setStore(st: StoreModel) {
        store.value = st
        setText()
    }
}