package co.com.ceiba.mobile.pruebadeingreso.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import co.com.ceiba.mobile.pruebadeingreso.data.database.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.network.responsemodel.UserResponse
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


@Database(entities = [UserResponse::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    class Callback @Inject constructor(
        private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

        }
    }

}
