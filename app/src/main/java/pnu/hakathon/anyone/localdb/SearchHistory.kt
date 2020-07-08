package pnu.hakathon.anyone.localdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchHistory(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name="searchQuery") val searchQuery: String
)