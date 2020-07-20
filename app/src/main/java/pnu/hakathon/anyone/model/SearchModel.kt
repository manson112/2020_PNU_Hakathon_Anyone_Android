package pnu.hakathon.anyone.model

data class SearchModel(
    var id: Int,
    var imageURL: String,
    var name: String,
    var hashTag: String,
    var address: String,
    var total: Int,
    var current: Int
)