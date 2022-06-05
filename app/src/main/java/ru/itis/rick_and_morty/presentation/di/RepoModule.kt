package ru.itis.rick_and_morty.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.rick_and_morty.data.repositories.CharactersRepositoryImpl
import ru.itis.rick_and_morty.domain.repositories.CharactersRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun characterRepository(
        impl: CharactersRepositoryImpl
    ): CharactersRepository
}
