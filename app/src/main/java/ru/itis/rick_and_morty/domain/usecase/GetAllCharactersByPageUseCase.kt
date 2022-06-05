package ru.itis.rick_and_morty.domain.usecase

import android.util.Log
import dagger.Reusable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.itis.rick_and_morty.domain.models.CharacterModel
import ru.itis.rick_and_morty.domain.repositories.CharactersRepository
import javax.inject.Inject

@Reusable
class GetAllCharactersByPageUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    operator fun invoke(
        page: Int
    ): Single<CharacterModel> {
        Log.e("DEBUG", "usecase start")

        return repository.getCharacters(page).subscribeOn(Schedulers.io())
    }
}
