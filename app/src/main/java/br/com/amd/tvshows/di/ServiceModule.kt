package br.com.amd.tvshows.di

import br.com.amd.tvshows.BuildConfig
import br.com.amd.tvshows.data.remote.api.TvMazeApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            clientBuilder.addInterceptor(interceptor)
        }

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideTvMazeApi(
        okHttpClient: OkHttpClient
    ): TvMazeApi {
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()

        val retrofit = Retrofit.Builder()
            .baseUrl(TvMazeApi.HOST)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        return retrofit.create(TvMazeApi::class.java)
    }
}