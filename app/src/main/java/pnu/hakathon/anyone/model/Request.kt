package pnu.hakathon.anyone.model

interface Request {
    fun toMap(): HashMap<String, String>
}