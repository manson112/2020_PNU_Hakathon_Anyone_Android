package pnu.hakathon.anyone.repository

import androidx.lifecycle.LiveData
import pnu.hakathon.anyone.localdb.Bookmark

interface BookmarkRepository {
    fun getBookmarks(userID: String): LiveData<List<Bookmark>>
}