package pnu.hakathon.anyone.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pnu.hakathon.anyone.entity.StoreModel

@Dao
interface StoreListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sm: StoreModel)

    @Query("SELECT * FROM stores WHERE category_id = :categoryID and (:sin_lat * sin_lat + :cos_lat * cos_lat * (cos_lng * :cos_lng + sin_lng * :sin_lng)) < :distance ORDER BY distance ASC LIMIT 30")
    suspend fun getStoresNear(categoryID: String, cos_lat: String, sin_lat: String, cos_lng: String, sin_lng: String, distance: String): List<StoreModel>
}