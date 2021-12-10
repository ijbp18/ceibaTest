package co.com.ceiba.mobile.pruebadeingreso.di

import co.com.ceiba.mobile.pruebadeingreso.data.repository.Repository
import co.com.ceiba.mobile.pruebadeingreso.domain.usecase.PostUserCase
import co.com.ceiba.mobile.pruebadeingreso.domain.usecase.PostUserCaseImpl
import co.com.ceiba.mobile.pruebadeingreso.domain.usecase.UsersUseCase
import co.com.ceiba.mobile.pruebadeingreso.domain.usecase.UsersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideUsersUseCase(
        usersRepository: Repository
    ): UsersUseCase {
        return UsersUseCaseImpl(usersRepository)
    }

    @Provides
    @ViewModelScoped
    fun providePostUseCase(
        postRepository: Repository
    ): PostUserCase {
        return PostUserCaseImpl(postRepository)
    }
}