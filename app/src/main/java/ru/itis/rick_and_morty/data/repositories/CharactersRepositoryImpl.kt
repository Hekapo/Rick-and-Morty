package ru.itis.rick_and_morty.data.repositories

import io.reactivex.rxjava3.core.Single
import ru.itis.rick_and_morty.data.mappers.CharacterMapper
import ru.itis.rick_and_morty.data.remote.RickAndMortyApi
import ru.itis.rick_and_morty.domain.models.CharacterModel
import ru.itis.rick_and_morty.domain.repositories.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi,
    private val mapper: CharacterMapper
) : CharactersRepository {

    override fun getCharacters(page: Int): Single<CharacterModel> {
        return api.getAllCharacters(page).map {
            it.body()?.let { it1 -> mapper.map(it1) }
        }
    }
}
