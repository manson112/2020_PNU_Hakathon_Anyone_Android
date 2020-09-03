package pnu.hakathon.anyone.entity

import android.os.Parcelable
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject
import kotlinx.android.parcel.Parcelize
import pnu.hakathon.anyone.network.Request
import kotlin.math.cos
import kotlin.math.sin

@Entity(tableName = "stores")
@Parcelize
data class StoreModel (
    @PrimaryKey var id: Int = 0,
    @ColumnInfo(name = "category_id") var categoryID: String = "",
    @ColumnInfo(name = "image_url") var imageURL: String = "",
    @ColumnInfo(name = "store_name") var storeName: String = "",
    @ColumnInfo(name = "hashtags") var hashTag: String = "",
    @ColumnInfo(name = "address") var address: String = "",
    @ColumnInfo(name = "total_seat") var total: Int = 0,
    @ColumnInfo(name = "current_seat") var current: Int = 0,
    @ColumnInfo(name = "latitude") var lat: Double = 0.0,
    @ColumnInfo(name = "longitude") var lng: Double = 0.0,
    @ColumnInfo(name = "cos_lat") var cos_lat: Double = 0.0,
    @ColumnInfo(name = "sin_lat") var sin_lat: Double = 0.0,
    @ColumnInfo(name = "cos_lng") var cos_lng: Double = 0.0,
    @ColumnInfo(name = "sin_lng") var sin_lng: Double = 0.0,
    @ColumnInfo(name = "distance") var distance: Int = 0,
    @ColumnInfo(name = "noise") var noise: Double = 0.0,
    @ColumnInfo(name = "cleanliness") var cleanliness: Double = 0.0,
    @ColumnInfo(name = "kindness") var kindness: Double = 0.0,
    @ColumnInfo(name = "wifi") var wifi: Double = 0.0,
    @ColumnInfo(name = "bookmarked") var bookmarked: Boolean = false
) : Parcelable {
    fun jsonToObj(json: JsonObject): StoreModel {
        json.get("id")?.let {
            this.id = it.asInt
        }
        json.get("category_id")?.let {
            this.categoryID = it.asString
        }
        json.get("image")?.let {
            this.imageURL = it.asString
        }
        json.get("name")?.let {
            this.storeName = it.asString
        }
        json.get("hashtags")?.let {
            this.hashTag = it.asString
        }
        json.get("address")?.let {
            this.address = it.asString
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
                this.cos_lat = cos(Math.toRadians(this.lat))
                this.sin_lat = sin(Math.toRadians(this.lat))
            }
        }
        json.get("lng")?.let {
            if (it.toString() != "") {
                this.lng = it.asDouble
                this.cos_lng = cos(Math.toRadians(this.lng))
                this.sin_lng = sin(Math.toRadians(this.lng))
            }
        }
        json.get("distance")?.let {
            this.distance = it.asDouble.toInt()
        }
        json.get("noise")?.let {
            this.noise = it.asDouble
        }
        json.get("cleanliness")?.let {
            this.cleanliness = it.asDouble
        }
        json.get("kindness")?.let {
            this.kindness = it.asDouble
        }
        json.get("wifi")?.let {
            this.wifi = it.asDouble
        }
        json.get("bookmarked")?.let {
            this.bookmarked = it.asInt != 0
        }
        Log.d("MapStoreModel", this.toString())
        return this
    }

    override fun toString(): String {
        return "StoreModel(id=$id, categoryID='$categoryID', imageURL='$imageURL', storeName='$storeName', hashTag='$hashTag', address='$address', total=$total, current=$current, lat=$lat, lng=$lng, cos_lat=$cos_lat, sin_lat=$sin_lat, cos_lng=$cos_lng, sin_lng=$sin_lng, distance=$distance, noise=$noise, cleanliness=$cleanliness, kindness=$kindness, wifi=$wifi, bookmarked=$bookmarked)"
    }

}

data class ReqMapStore(
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
