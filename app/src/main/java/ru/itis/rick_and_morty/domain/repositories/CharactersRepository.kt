package ru.itis.rick_and_morty.domain.repositories

import io.reactivex.rxjava3.core.Single
import ru.itis.rick_and_morty.domain.models.CharacterModel

interface CharactersRepository {
    fun getCharacters(page:Int): Single<CharacterModel>
}
