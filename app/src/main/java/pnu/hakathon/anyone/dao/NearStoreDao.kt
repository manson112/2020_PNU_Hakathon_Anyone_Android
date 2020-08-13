package pnu.hakathon.anyone.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pnu.hakathon.anyone.entity.NearStore


@Dao
interface NearStoreDao {
    @Query("SELECT * FROM near_stores WHERE category_id = :categoryID and latitude between :lat-0.0005 and :lat+0.0005 and longitude between :lng-0.000005 and :lng+0.000005")
    fun getStoresNearBy(categoryID: String, lat: Double, lng: Double): LiveData<List<NearStore>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ns: NearStore)
}