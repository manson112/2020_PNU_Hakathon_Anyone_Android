package pnu.hakathon.anyone.entity

import androidx.room.Entity

@Entity(tableName = "user")
data class User(
    var uid: String = "",
    var phoneNumber: String = "",
    var fcmToken: String = ""
) {
}