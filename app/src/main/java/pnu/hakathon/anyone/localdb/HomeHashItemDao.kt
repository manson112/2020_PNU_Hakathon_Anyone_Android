package pnu.hakathon.anyone.localdb

import androidx.room.Dao

@Dao
interface HomeHashItemDao {
    //    @Query("SELECT * FROM hash_item WHERE category_id=:categoryID and noise < 3")
    fun getQuietHashItem(categoryID: String)


}