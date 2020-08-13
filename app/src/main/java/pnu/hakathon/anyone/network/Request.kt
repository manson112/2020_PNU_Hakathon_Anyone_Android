package pnu.hakathon.anyone.network

interface Request {
    fun toMap(): HashMap<String, String>
}