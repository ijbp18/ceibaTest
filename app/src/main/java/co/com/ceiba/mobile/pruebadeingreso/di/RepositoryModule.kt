package co.com.ceiba.mobile.pruebadeingreso.di

import co.com.ceiba.mobile.pruebadeingreso.data.database.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.network.APIService
import co.com.ceiba.mobile.pruebadeingreso.data.repository.Repository
import co.com.ceiba.mobile.pruebadeingreso.data.repository.RepositoryImpl
import co.com.ceiba.mobile.pruebadeingreso.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(
        apiService: APIService,
        dispatcherProvider: DispatcherProvider
    ): Repository {
        return RepositoryImpl(apiService, dispatcherProvider)
    }
}