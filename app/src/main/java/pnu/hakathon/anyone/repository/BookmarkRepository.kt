package pnu.hakathon.anyone.repository

import androidx.lifecycle.LiveData
import pnu.hakathon.anyone.entity.Bookmark

interface BookmarkRepository {
    fun getBookmarks(userID: String): LiveData<List<Bookmark>>
}