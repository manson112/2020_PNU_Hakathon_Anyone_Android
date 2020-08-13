package pnu.hakathon.anyone.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recently_viewed")
data class RecentlyViewedItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name="name") val name: String
)