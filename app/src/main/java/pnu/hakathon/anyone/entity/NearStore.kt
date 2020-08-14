package pnu.hakathon.anyone.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject
import pnu.hakathon.anyone.network.Request

@Entity(tableName = "near_stores")
data class NearStore(
    @PrimaryKey var id: Int = 0,
    @ColumnInfo(name = "category_id") var categoryID: String = "",
    @ColumnInfo(name = "image_url") var imageURL: String = "",
    @ColumnInfo(name = "store_name") var storeName: String = "",
    @ColumnInfo(name = "current_seat") var current: Int = 0,
    @ColumnInfo(name = "total_seat") var total: Int = 0,
    @ColumnInfo(name = "latitude") var lat: Double = 0.0,
    @ColumnInfo(name = "longitude") var lng: Double = 0.0
) {
    fun jsonToObj(json: JsonObject): NearStore {
        json.get("id")?.let {
            this.id = it.asInt
        }
        json.get("category_id")?.let {
            this.categoryID = it.asString
        }
        json.get("imageURL")?.let {
            this.imageURL = it.asString
        }
        json.get("store_name")?.let {
            this.storeName = it.asString
        }
        json.get("total_seat")?.let {
            this.total = it.asInt
        }
        json.get("current_seat")?.let {
            this.current = it.asInt
        }
        json.get("lat")?.let {
            if (it.toString() != "") {
                this.lat = it.asDouble
            }
        }
        json.get("lng")?.let {
            if (it.toString() != "") {
                this.lng = it.asDouble
            }
        }
        return this
    }
}

data class ReqNearStore(
    var data: Data = Data()
) : Request {
    constructor(categoryID: String, lat: String, lng: String) : this(
        Data(
            categoryID,
            lat,
            lng
        )
    )

    data class Data(
        var categoryID: String = "",
        var lat: String = "",
        var lng: String = ""
    )

    override fun toMap(): HashMap<String, String> {
        val map = hashMapOf<String, String>()
        map["categoryID"] = data.categoryID
        map["lat"] = data.lat
        map["lng"] = data.lng
        return map
    }

}
