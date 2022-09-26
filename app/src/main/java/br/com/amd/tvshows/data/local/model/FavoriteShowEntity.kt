package br.com.amd.tvshows.data.local.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_shows")
data class FavoriteShowEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    val id: Long = 0,

    @NonNull
    @ColumnInfo(name = "show_id")
    val showId: Long = 0,

    @NonNull
    @ColumnInfo(name = "name")
    val name: String,

    @Nullable
    @ColumnInfo(name = "image")
    val imageUrl: String? = null
)
