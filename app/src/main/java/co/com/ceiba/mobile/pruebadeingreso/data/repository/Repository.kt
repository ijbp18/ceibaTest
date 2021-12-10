package co.com.ceiba.mobile.pruebadeingreso.data.repository

import co.com.ceiba.mobile.pruebadeingreso.domain.common.Result
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.PostUI
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.UsersUI
import kotlinx.coroutines.flow.Flow

interface Repository {
     fun getUsers(): Flow<Result<ArrayList<UsersUI>>>
     fun getPostsUser(userId: Int): Flow<Result<ArrayList<PostUI>>>
}