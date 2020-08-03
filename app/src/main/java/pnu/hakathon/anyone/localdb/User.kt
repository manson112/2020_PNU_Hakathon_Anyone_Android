package pnu.hakathon.anyone.localdb

import androidx.room.Entity

@Entity(tableName = "user")
data class User(
    var uid: String = "",
    var phoneNumber: String = "",
    var fcmToken: String = ""
) {
}