package co.com.ceiba.mobile.pruebadeingreso.data.network

import co.com.ceiba.mobile.pruebadeingreso.data.network.Endpoints.GET_POST_USER
import co.com.ceiba.mobile.pruebadeingreso.data.network.Endpoints.GET_USERS
import co.com.ceiba.mobile.pruebadeingreso.data.network.responsemodel.PostResponse
import co.com.ceiba.mobile.pruebadeingreso.data.network.responsemodel.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET(GET_USERS)
    suspend fun getUsers(): List<UserResponse>

    @GET(GET_POST_USER)
    suspend fun getPostsUser(@Query("userId") userId: Int): List<PostResponse>
}