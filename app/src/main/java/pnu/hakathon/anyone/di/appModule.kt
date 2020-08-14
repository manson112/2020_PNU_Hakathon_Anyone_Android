package pnu.hakathon.anyone.di

import androidx.lifecycle.SavedStateHandle
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pnu.hakathon.anyone.localdb.AppDatabase
import pnu.hakathon.anyone.network.RetrofitService
import pnu.hakathon.anyone.network.ServerResponse
import pnu.hakathon.anyone.network.ServerResponseDeserializer
import pnu.hakathon.anyone.repoimpl.BookmarkRepositoryImpl
import pnu.hakathon.anyone.repoimpl.HomeRepositoryImpl
import pnu.hakathon.anyone.repoimpl.MapRepositoryImpl
import pnu.hakathon.anyone.repoimpl.StoreDetailRepositoryImpl
import pnu.hakathon.anyone.repository.BookmarkRepository
import pnu.hakathon.anyone.repository.HomeRepository
import pnu.hakathon.anyone.repository.MapRepository
import pnu.hakathon.anyone.repository.StoreDetailRepository
import pnu.hakathon.anyone.viewmodel.BookmarkViewModel
import pnu.hakathon.anyone.viewmodel.HomeViewModel
import pnu.hakathon.anyone.viewmodel.MapViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

val appModule = module {
    single<HomeRepository> {
        HomeRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
    single<BookmarkRepository> {
        BookmarkRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
    single<MapRepository> {
        MapRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
    single<StoreDetailRepository> { StoreDetailRepositoryImpl() }

    factory { provideServerApi(provideServerRetrofit()) }
    factory { provideExecutor() }

    factory { AppDatabase.getDatabase(androidApplication()) }
    factory { get<AppDatabase>().bookmarkDao() }
    factory { get<AppDatabase>().searchHistoryDao() }
    factory { get<AppDatabase>().mapStoreListDao() }
    factory { get<AppDatabase>().nearStoreDao() }

    viewModel { (handle: SavedStateHandle) -> HomeViewModel(handle, get()) }
    viewModel { (handle: SavedStateHandle) -> MapViewModel(handle, get()) }
    viewModel { BookmarkViewModel(get()) }
//    viewModel { SearchViewModel(get()) }
//    viewModel { StoreDetailViewModel(get()) }

}

fun provideServerApi(retrofit: Retrofit): RetrofitService =
    retrofit.create(RetrofitService::class.java)

fun provideServerRetrofit(): Retrofit {
    val host = "http://3.34.52.3/"
//    val host = "http://192.168.21.20:8080/"
    val gsonBuilder = GsonBuilder()
    gsonBuilder.registerTypeAdapter(ServerResponse::class.java, ServerResponseDeserializer())
    val gson = gsonBuilder.create()

    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(host)
        .build()
}

fun provideExecutor(): Executor {
    return Executors.newSingleThreadExecutor()
}