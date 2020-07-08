package pnu.hakathon.anyone.localdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchHistoryDao {
    @Query("SELECT * FROM search_history ORDER BY id DESC LIMIT 5")
    fun getSearchHistories(): LiveData<List<SearchHistory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(sh: SearchHistory)

    @Query("DELETE FROM search_history")
    fun deleteAll()

    @Query("DELETE FROM search_history WHERE id=:id")
    fun delete(id: Int)
}