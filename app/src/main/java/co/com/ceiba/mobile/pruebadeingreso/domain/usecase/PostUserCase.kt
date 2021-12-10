package co.com.ceiba.mobile.pruebadeingreso.domain.usecase

import co.com.ceiba.mobile.pruebadeingreso.domain.common.Result
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.PostUI
import kotlinx.coroutines.flow.Flow

interface PostUserCase {
    suspend fun invoke(userId: Int): Flow<Result<ArrayList<PostUI>>>
}