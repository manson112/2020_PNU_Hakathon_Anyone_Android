package pnu.hakathon.anyone.viewmodel

import androidx.lifecycle.*
import pnu.hakathon.anyone.entity.StoreModel
import pnu.hakathon.anyone.repository.BookmarkRepository

class BookmarkViewModel(private val repo: BookmarkRepository) : ViewModel() {
    private val bookmarks: LiveData<List<StoreModel>> = liveData {
        emitSource(repo.getBookmarksFromServer("1").asLiveData())
    }
    val cafe: LiveData<List<StoreModel>>
    val rest: LiveData<List<StoreModel>>

    init {
        cafe = bookmarks.map { bm ->
            bm.filter { it.categoryID == "1" }
        }
        rest = bookmarks.map { bm ->
            bm.filter { it.categoryID == "2" }
        }
    }

}