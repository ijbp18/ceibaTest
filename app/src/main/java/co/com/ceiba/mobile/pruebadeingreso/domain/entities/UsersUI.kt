package co.com.ceiba.mobile.pruebadeingreso.domain.entities

import java.io.Serializable

data class UsersUI(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String
):Serializable