package co.com.ceiba.mobile.pruebadeingreso.data.network.mappers

import co.com.ceiba.mobile.pruebadeingreso.data.network.responsemodel.UserResponse
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.UsersUI

fun mapFromEntity(entity: UserResponse): UsersUI {
    return UsersUI(
        entity.id,
        entity.name,
        entity.email,
        entity.phone
    )
}

fun mapFromEntityList(entityList: List<UserResponse>): ArrayList<UsersUI> {
    return ArrayList(entityList.map { mapFromEntity(it) })
}

fun UserResponse.toUsersUI() = UsersUI(
    id = id,
    name = name,
    email = email,
    phone = phone
)



