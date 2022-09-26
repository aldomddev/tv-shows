package br.com.amd.tvshows.data.local.mapper

import br.com.amd.tvshows.data.local.model.FavoriteShowEntity
import br.com.amd.tvshows.domain.model.FavoriteShow

fun FavoriteShowEntity.toFavouriteShowDomain(): FavoriteShow {
    return FavoriteShow(
        id = id,
        showId = showId,
        name = name,
        imageUrl = imageUrl.orEmpty()
    )
}

fun List<FavoriteShowEntity>.toFavouriteShowDomain() = map { it.toFavouriteShowDomain() }

fun FavoriteShow.toFavouriteShowData(): FavoriteShowEntity {
    return FavoriteShowEntity(
        id = id,
        showId = showId,
        name = name,
        imageUrl = imageUrl
    )
}