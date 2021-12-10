package co.com.ceiba.mobile.pruebadeingreso.domain.usecase

import co.com.ceiba.mobile.pruebadeingreso.data.repository.Repository
import co.com.ceiba.mobile.pruebadeingreso.domain.common.Result
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.PostUI
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.UsersUI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersUseCaseImpl @Inject constructor(private val remoteRepository: Repository) :
    UsersUseCase {
    override suspend fun invoke(): Flow<Result<ArrayList<UsersUI>>> {
        return remoteRepository.getUsers()
    }
}