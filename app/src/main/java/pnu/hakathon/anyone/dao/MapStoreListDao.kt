package pnu.hakathon.anyone.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pnu.hakathon.anyone.entity.MapStoreModel

@Dao
interface MapStoreListDao {
    //    @Query("SELECT * FROM map_stores WHERE category_id = :categoryID and latitude between :lat-0.0005 and :lat+0.0005 and longitude between :lng-0.005 and :lng+0.005")
    @Query("SELECT * FROM map_stores WHERE category_id = :categoryID and latitude between :lat-0.0005 and :lat+0.0005 and longitude between :lng-0.005 and :lng+0.005")
    fun getMapStoreList(categoryID: String, lat: Double, lng: Double): LiveData<List<MapStoreModel>>

    @Query("SELECT * FROM map_stores WHERE category_id = :categoryID")
    fun getMapStore(categoryID: String): LiveData<List<MapStoreModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(msm: MapStoreModel)

    @Query("DELETE FROM map_stores")
    fun deleteAll()
}
