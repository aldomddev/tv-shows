package br.com.amd.tvshows.di

import android.content.Context
import androidx.room.Room
import br.com.amd.tvshows.data.local.database.ShowsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideShowDatabase(@ApplicationContext context: Context): ShowsDatabase {
        return Room.databaseBuilder(
            context,
            ShowsDatabase::class.java,
            "tv_shows.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideShowDao(database: ShowsDatabase) = database.showsDao()
}