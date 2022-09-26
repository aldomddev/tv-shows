package br.com.amd.tvshows.di

import br.com.amd.tvshows.data.repository.ShowsRepositoryImpl
import br.com.amd.tvshows.domain.repository.ShowsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ShowRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindShowRepository(repositoryImpl: ShowsRepositoryImpl): ShowsRepository
}