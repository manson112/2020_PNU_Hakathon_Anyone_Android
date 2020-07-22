package pnu.hakathon.anyone.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [SearchHistory::class, Bookmark::class], version = 5)
abstract class AppDatabase: RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

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

                INSTANCE?.let { appDatabase ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(appDatabase.searchHistoryDao(), appDatabase.bookmarkDao())
                    }
                }
            }
        }

        fun populateDatabase(searchHistoryDao: SearchHistoryDao, bookmarkDao: BookmarkDao) {
//            searchHistoryDao.deleteAll()
//
//            var searchHistory = SearchHistory(0,"KKK")
//            searchHistoryDao.insert(searchHistory)
//
//            searchHistory = SearchHistory(0,"KKK2")
//            searchHistoryDao.insert(searchHistory)

            bookmarkDao.deleteAll()
            bookmarkDao.insert(
                Bookmark(
                    0,
                    "1",
                    "카페",
                    "부산 커피",
                    "부산 금정구 장전온천천로89번길 2",
                    "search_image_sample1",
                    "2020-07-17"
                )
            )
            bookmarkDao.insert(
                Bookmark(
                    1,
                    "1",
                    "카페",
                    "커피프린스",
                    "부산 금정구 장전온천천로89번길 4",
                    "search_image_sample2",
                    "2020-07-18"
                )
            )
            bookmarkDao.insert(
                Bookmark(
                    2,
                    "1",
                    "카페",
                    "노스 커피 문창점",
                    "부산 금정구 부산대학로64번길 40",
                    "search_image_sample3",
                    "2020-07-21"
                )
            )

        }
    }
}