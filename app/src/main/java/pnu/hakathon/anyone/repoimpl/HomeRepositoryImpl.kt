package pnu.hakathon.anyone.repoimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pnu.hakathon.anyone.dao.StoreListDao
import pnu.hakathon.anyone.repository.HomeRepository
import kotlin.math.cos
import kotlin.math.sin

class HomeRepositoryImpl(
    private val storeListDao: StoreListDao
) : HomeRepository {
    override suspend fun getStoresNearBy(
        categoryID: String,
        lat: Double,
        lng: Double
    ) = flow {
        val cos_lat = cos(Math.toRadians(lat)).toString()
        val sin_lat = sin(Math.toRadians(lat)).toString()
        val cos_lng = cos(Math.toRadians(lng)).toString()
        val sin_lng = sin(Math.toRadians(lng)).toString()
        val distance = cos(0.5 / 6317).toString()
        val list = storeListDao.getStoresNear(categoryID, cos_lat, sin_lat, cos_lng, sin_lng, distance)
        emit(list)
    }.flowOn(Dispatchers.IO)
}