package pnu.hakathon.anyone.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import pnu.hakathon.anyone.dao.BookmarkDao
import pnu.hakathon.anyone.dao.SearchHistoryDao
import pnu.hakathon.anyone.dao.StoreListDao
import pnu.hakathon.anyone.entity.Bookmark
import pnu.hakathon.anyone.entity.SearchHistory
import pnu.hakathon.anyone.entity.StoreModel

@Database(
    entities = [SearchHistory::class, Bookmark::class,  StoreModel::class],
    version = 14
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun storeListDao(): StoreListDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                instance
            }
        }

        private class AppDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

//                INSTANCE?.let { appDatabase ->
//                    scope.launch(Dispatchers.IO) {
//                        populateDatabase(
//                            appDatabase.searchHistoryDao(),
//                            appDatabase.bookmarkDao(),
//                            appDatabase.storeListDao()
//                        )
//                    }
//                }
            }
        }

        fun populateDatabase(
            bookmarkDao: BookmarkDao,
            nearStoreDao: StoreListDao
        ) {
        }
    }
}