package pnu.hakathon.anyone.network

interface Request {
    fun toMap(): HashMap<String, String>
}

data class ReqBookmark(
    var data: Data = Data()
) : Request {
    constructor(userID: String) : this(Data(userID))

    data class Data(
        var userID: String = ""
    )

    override fun toMap(): HashMap<String, String> {
        return hashMapOf("userID" to data.userID)
    }
}

data class ReqSetBookmark(
    var data: Data = Data()
): Request {
    constructor(userID: String, storeID: String, checked:Boolean) : this(Data(userID, storeID, checked))
    data class Data(
        var userID: String = "",
        var storeID: String = "",
        var checked: Boolean = false
    )
    override fun toMap(): HashMap<String, String> {
        val m = hashMapOf<String, String>()
        m["userID"] = data.userID
        m["storeID"] = data.storeID
        m["checked"] = data.checked.toString()
        return m
    }
}

data class ReqSearchHistory(
    var data: Data = Data()
) : Request {
    constructor(userID: String) : this(Data(userID))

    data class Data(
        var userID: String = ""
    )

    override fun toMap(): HashMap<String, String> {
        return hashMapOf("userID" to data.userID)
    }
}