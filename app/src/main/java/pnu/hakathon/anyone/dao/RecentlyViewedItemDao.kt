package pnu.hakathon.anyone.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pnu.hakathon.anyone.entity.RecentlyViewedItem

@Dao
interface RecentlyViewedItemDao {
    @Query("SELECT * FROM recently_viewed ORDER BY id DESC LIMIT 6")
    fun getRecentlyViewedItems(): LiveData<List<RecentlyViewedItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(rv: RecentlyViewedItem)

    @Query("DELETE FROM recently_viewed")
    fun deleteAll()

    @Query("DELETE FROM recently_viewed WHERE id=:id")
    fun delete(id: Int)
}