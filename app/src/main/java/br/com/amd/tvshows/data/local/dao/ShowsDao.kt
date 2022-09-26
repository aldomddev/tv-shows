package br.com.amd.tvshows.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.amd.tvshows.data.local.model.FavoriteShowEntity
import br.com.amd.tvshows.domain.model.FavoriteShow
import kotlinx.coroutines.flow.Flow

@Dao
interface ShowsDao {

    @Query("SELECT * FROM favorite_shows ORDER BY name")
    fun all(): Flow<List<FavoriteShowEntity>>

    @Query("SELECT * FROM favorite_shows WHERE show_id = :showId")
    fun findByShowId(showId: Long): FavoriteShowEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveOrUpdate(show: FavoriteShowEntity)

    @Delete
    fun delete(show: FavoriteShowEntity)
}