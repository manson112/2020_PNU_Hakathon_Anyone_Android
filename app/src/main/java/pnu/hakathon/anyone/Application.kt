package pnu.hakathon.anyone

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import pnu.hakathon.anyone.di.appModule
import timber.log.Timber

class Application : Application() {
    private val DiModule = listOf(appModule)
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Application)
            modules(DiModule)
        }
    }

}