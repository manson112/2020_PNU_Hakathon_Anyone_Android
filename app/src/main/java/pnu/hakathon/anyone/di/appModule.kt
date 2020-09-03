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

val appModule = module {
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
    single<SearchRepository> { SearchRepositoryImpl(get()) }

    factory { provideServerApi(provideServerRetrofit()) }
    factory { AppDatabase.getDatabase(androidApplication()) }
    factory { get<AppDatabase>().storeListDao() }

    viewModel { (handle: SavedStateHandle) -> MapViewModel(handle, get()) }
    viewModel { BookmarkViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { StoreDetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}

fun provideServerApi(retrofit: Retrofit): RetrofitService =
    retrofit.create(RetrofitService::class.java)

fun provideServerRetrofit(): Retrofit {
    val host = "http://3.34.52.3/"
    val gsonBuilder = GsonBuilder()
    gsonBuilder.registerTypeAdapter(ServerResponse::class.java, ServerResponseDeserializer())
    val gson = gsonBuilder.create()
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(host)
        .build()
}
