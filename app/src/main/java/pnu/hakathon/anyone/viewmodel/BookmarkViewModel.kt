package pnu.hakathon.anyone.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pnu.hakathon.anyone.localdb.*

class BookmarkViewModel(application: Application): AndroidViewModel(application) {
    private val repository: BookmarkRepository
    val bookmarks: LiveData<List<Bookmark>>

    init {
        val bookmarkDao = AppDatabase.getDatabase(application, viewModelScope).bookmarkDao()
        repository = BookmarkRepository(bookmarkDao)
        bookmarks = repository.allBookmarks
    }

    fun insert(bookmark: Bookmark) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(bookmark)
    }
}