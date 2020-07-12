package pnu.hakathon.anyone.model

import com.google.gson.JsonObject

data class StoreDetail(
    var id: Int = 0,
    var categoryID: String = "",
    var category: String = "",
    var storeName: String = "",
    var tags: String = "",
    var totalSeat: String = "",
    var currentSeat: String = "",
    var phoneNumber: String = "",
    var address: String = "",
    var lat: String = "",
    var lng: String = "",
    var noise: String = "",
    var cleanliness: String = "",
    var kindness: String = "",
    var wifi: String = ""
) {
    fun jsonToObj(json: JsonObject): StoreDetail {
        json.get("id")?.let {
            this.id = it.asInt
        }
        json.get("category_id")?.let {
            this.categoryID = it.asString
        }
        json.get("category")?.let {
            this.category = it.asString
        }
        json.get("store_name")?.let {
            this.storeName = it.asString
        }
        json.get("tags")?.let {
            this.tags = it.asString
        }
        json.get("total_seat")?.let {
            this.totalSeat = it.asString
        }
        json.get("current_seat")?.let {
            this.currentSeat = it.asString
        }
        json.get("phone_number")?.let {
            this.phoneNumber = it.asString
        }
        json.get("address")?.let {
            this.address = it.asString
        }
        json.get("lat")?.let {
            this.lat = it.asString
        }
        json.get("lng")?.let {
            this.lng = it.asString
        }
        json.get("noise")?.let {
            this.noise = it.asString
        }
        json.get("cleanliness")?.let {
            this.cleanliness = it.asString
        }
        json.get("kindness")?.let {
            this.kindness = it.asString
        }
        json.get("wifi")?.let {
            this.wifi = it.asString
        }
        return this
    }

    override fun toString(): String {
        return "StoreDetail(id=$id, categoryID='$categoryID', category='$category', storeName='$storeName', tags='$tags', totalSeat='$totalSeat', currentSeat='$currentSeat', phoneNumber='$phoneNumber', address='$address', lat='$lat', lng='$lng', noise='$noise', cleanliness='$cleanliness', kindness='$kindness', wifi='$wifi')"
    }

}