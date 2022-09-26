package br.com.amd.tvshows.data.local.dao

import androidx.room.*
import br.com.amd.tvshows.data.local.model.FavoriteShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteShowsDao {

    @Query("SELECT * FROM favorite_shows ORDER BY name")
    fun getAll(): List<FavoriteShowEntity>

    @Query("SELECT * FROM favorite_shows ORDER BY name LIMIT :itemsPerPage OFFSET :page")
    fun getPagedList(itemsPerPage: Int, page: Int): List<FavoriteShowEntity>

    @Query("SELECT * FROM favorite_shows WHERE show_id = :showId")
    fun findByShowId(showId: Long): FavoriteShowEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveOrUpdate(show: FavoriteShowEntity)

    @Delete
    fun delete(show: FavoriteShowEntity)
}