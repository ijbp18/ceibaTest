package co.com.ceiba.mobile.pruebadeingreso.domain.usecase

import co.com.ceiba.mobile.pruebadeingreso.domain.common.Result
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.PostUI
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.UsersUI
import kotlinx.coroutines.flow.Flow

interface UsersUseCase {
    suspend fun invoke() : Flow<Result<ArrayList<UsersUI>>>
}