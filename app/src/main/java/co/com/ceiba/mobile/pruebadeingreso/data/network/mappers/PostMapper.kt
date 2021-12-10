package co.com.ceiba.mobile.pruebadeingreso.data.network.mappers

import co.com.ceiba.mobile.pruebadeingreso.data.network.responsemodel.PostResponse
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.PostUI

fun mapFromPostEntity(entity: PostResponse): PostUI {
    return PostUI(
        entity.userId,
        entity.id,
        entity.title,
        entity.body
    )
}

fun mapFromPostEntityList(entityList: List<PostResponse>): ArrayList<PostUI>{
    return ArrayList(entityList.map { mapFromPostEntity(it) })
}