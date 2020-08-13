package pnu.hakathon.anyone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pnu.hakathon.anyone.entity.Bookmark
import pnu.hakathon.anyone.repository.BookmarkRepository

class BookmarkViewModel(private val repo: BookmarkRepository) : ViewModel() {
    val bookmarks: LiveData<List<Bookmark>> = repo.getBookmarks("1")
}