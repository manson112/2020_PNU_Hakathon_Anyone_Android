package pnu.hakathon.anyone

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pnu.hakathon.anyone.di.appModule

class Application : Application() {
    private val DiModule = listOf(appModule)
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(DiModule)
        }
    }

}