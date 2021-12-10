package co.com.ceiba.mobile.pruebadeingreso.data.repository

import androidx.lifecycle.LiveData
import co.com.ceiba.mobile.pruebadeingreso.data.database.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.network.responsemodel.UserResponse

class RepositoryDBImpl(private val userDao: UserDao): RepositoryDB {
    override fun getAllUsers(): LiveData<List<UserResponse>> {
        return userDao.loadUsers()
    }

    override suspend fun addUsers(users: List<UserResponse>) {
        return userDao.saveUsers(users)
    }
}