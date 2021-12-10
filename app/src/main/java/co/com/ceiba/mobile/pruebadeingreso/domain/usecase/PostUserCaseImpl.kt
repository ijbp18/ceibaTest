package co.com.ceiba.mobile.pruebadeingreso.domain.usecase

import co.com.ceiba.mobile.pruebadeingreso.data.repository.Repository
import co.com.ceiba.mobile.pruebadeingreso.domain.common.Result
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.PostUI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostUserCaseImpl @Inject constructor(private val remoteRepository: Repository) :
    PostUserCase {
    override suspend fun invoke(userId: Int): Flow<Result<ArrayList<PostUI>>> {
        return remoteRepository.getPostsUser(userId)
    }
}