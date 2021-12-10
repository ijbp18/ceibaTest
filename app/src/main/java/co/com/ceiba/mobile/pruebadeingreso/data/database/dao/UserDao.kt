package co.com.ceiba.mobile.pruebadeingreso.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.ceiba.mobile.pruebadeingreso.data.network.responsemodel.UserResponse

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun loadUsers(): LiveData<List<UserResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(userEntities: List<UserResponse>)

    @Query("DELETE from users")
    suspend fun deleteAll()
}