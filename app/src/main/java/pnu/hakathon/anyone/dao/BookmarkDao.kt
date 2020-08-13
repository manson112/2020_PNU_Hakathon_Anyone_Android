package pnu.hakathon.anyone.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pnu.hakathon.anyone.entity.Bookmark

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmark ORDER BY id DESC LIMIT 30")
    fun getBookmarks(): LiveData<List<Bookmark>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(bm: Bookmark)

    @Query("DELETE FROM bookmark")
    fun deleteAll()

    @Query("DELETE FROM bookmark WHERE id=:id")
    fun delete(id: Int)
}