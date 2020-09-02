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
import pnu.hakathon.anyone.repoimpl.*
import pnu.hakathon.anyone.repository.*
import pnu.hakathon.anyone.viewmodel.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

val appModule = module {
    single<HomeRepository> {
        HomeRepositoryImpl(
            get()
        )
    }
    single<BookmarkRepository> {
        BookmarkRepositoryImpl(
            get()
        )
    }
    single<MapRepository> {
        MapRepositoryImpl(
            get(),
            get()
        )
    }
    single<StoreDetailRepository> { StoreDetailRepositoryImpl(get()) }
    single<MainRepository> { MainRepositoryImpl(get(), get()) }

    factory { provideServerApi(provideServerRetrofit()) }
    factory { provideExecutor() }
    factory { AppDatabase.getDatabase(androidApplication()) }
    factory { get<AppDatabase>().bookmarkDao() }
    factory { get<AppDatabase>().searchHistoryDao() }
    factory { get<AppDatabase>().storeListDao() }

    viewModel { (handle: SavedStateHandle) -> HomeViewModel(handle, get()) }
    viewModel { (handle: SavedStateHandle) -> MapViewModel(handle, get()) }
    viewModel { BookmarkViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { StoreDetailViewModel(get()) }

//    viewModel { SearchViewModel(get()) }

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