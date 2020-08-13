package pnu.hakathon.anyone.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject

@Entity(tableName = "search_history")
data class SearchHistory(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "searchQuery") var searchQuery: String = "",
    @ColumnInfo(name = "created_at") var createdAt: String = ""
) {
    fun jsonToObj(json: JsonObject): SearchHistory {
        json.get("id")?.let {
            this.id = it.asInt
        }
        json.get("search_query")?.let {
            this.searchQuery = it.asString
        }
        json.get("created_at")?.let {
            this.createdAt = it.asString
        }
        return this
    }
}