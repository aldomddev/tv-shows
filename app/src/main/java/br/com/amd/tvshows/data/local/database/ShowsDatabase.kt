package br.com.amd.tvshows.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.amd.tvshows.data.local.dao.ShowsDao
import br.com.amd.tvshows.data.local.model.FavoriteShowEntity

@Database(
    entities = [FavoriteShowEntity::class],
    exportSchema = true,
    version = 1
)
abstract class ShowsDatabase : RoomDatabase() {
    abstract fun showsDao(): ShowsDao
}