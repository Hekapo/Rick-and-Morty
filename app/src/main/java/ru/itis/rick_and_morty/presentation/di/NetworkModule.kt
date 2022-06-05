package ru.itis.rick_and_morty.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.itis.rick_and_morty.data.mappers.CharacterMapper
import ru.itis.rick_and_morty.data.remote.RickAndMortyApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideCharacterMapper(): CharacterMapper = CharacterMapper()

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideApi(
        gsonConverter: GsonConverterFactory
    ): RickAndMortyApi =
        Retrofit.Builder()
            .baseUrl(ru.itis.rick_and_morty.BuildConfig.API_URL)
            .addConverterFactory(gsonConverter)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)

}
