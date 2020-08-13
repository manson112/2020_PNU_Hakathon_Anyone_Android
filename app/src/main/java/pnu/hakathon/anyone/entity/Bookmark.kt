package pnu.hakathon.anyone.entity

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject

@Entity(tableName = "bookmark")
class Bookmark(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "category_id") var categoryID: String = "",
    @ColumnInfo(name = "category") var categoryName: String = "",
    @ColumnInfo(name = "store_name") var storeName: String = "",
    @ColumnInfo(name = "address") var address: String = "",
    @ColumnInfo(name = "imageURL") var imageURL: String = "",
    @ColumnInfo(name = "created_at") var createdAt: String = ""
) {

    fun jsonToObj(json: JsonObject): Bookmark {
        json.get("id")?.let {
            this.id = it.asInt
        }
        json.get("category_id")?.let {
            this.categoryID = it.asString
        }
        json.get("category")?.let {
            this.categoryName = it.asString
        }
        json.get("store_name")?.let {
            this.storeName = it.asString
        }
        json.get("address")?.let {
            this.address = it.asString
        }
        json.get("image")?.let {
            this.imageURL = it.asString
            Log.d("BOOKMARK", it.asString)
        }
        json.get("created_at")?.let {
            this.createdAt = it.asString
        }
        return this
    }
}