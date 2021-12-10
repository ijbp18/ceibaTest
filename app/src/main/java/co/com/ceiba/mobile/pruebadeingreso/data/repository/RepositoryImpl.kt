package co.com.ceiba.mobile.pruebadeingreso.data.repository

import co.com.ceiba.mobile.pruebadeingreso.data.network.APIService
import co.com.ceiba.mobile.pruebadeingreso.data.network.mappers.mapFromEntityList
import co.com.ceiba.mobile.pruebadeingreso.data.network.mappers.mapFromPostEntityList
import co.com.ceiba.mobile.pruebadeingreso.domain.common.Result
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.PostUI
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.UsersUI
import co.com.ceiba.mobile.pruebadeingreso.utils.DispatcherProvider
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val myServiceAPI: APIService,
    private val dispatchers: DispatcherProvider
) : Repository {

    override fun getUsers(): Flow<Result<ArrayList<UsersUI>>> {

        return flow<Result<ArrayList<UsersUI>>> {

            val response = myServiceAPI.getUsers()
//            userDao.saveUsers(response)
            emit(Result.Success(data = mapFromEntityList(response)))

        }.onStart {
            emit(Result.Loading)
        }.catch {
            emit(Result.Failure(null))
        }.flowOn(dispatchers.io())
    }

    override fun getPostsUser(userId: Int): Flow<Result<ArrayList<PostUI>>>{

        return flow<Result<ArrayList<PostUI>>> {

            val response = myServiceAPI.getPostsUser(userId)
            emit(Result.Success(data = mapFromPostEntityList(response)))

        }.onStart {
            emit(Result.Loading)
        }.catch {
            emit(Result.Failure(null))
        }.flowOn(dispatchers.io())

    }
}