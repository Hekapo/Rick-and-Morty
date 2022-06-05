package ru.itis.rick_and_morty.presentation.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.itis.rick_and_morty.domain.models.CharacterModel
import ru.itis.rick_and_morty.domain.usecase.GetAllCharactersByPageUseCase
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharactersByPageUseCase: GetAllCharactersByPageUseCase
) : ViewModel() {
    private val _characters: MutableLiveData<Result<CharacterModel>> = MutableLiveData()
    val characters: LiveData<Result<CharacterModel>> = _characters

    private val _error: MutableLiveData<Exception> = MutableLiveData()
    val error: LiveData<Exception> = _error

    private val disposables = CompositeDisposable()

    fun fetchWeather(page: Int) {
        Log.e("DEBUG", "vm start")
        disposables += getAllCharactersByPageUseCase(page)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {

            }.doAfterSuccess {

            }.subscribeBy(
                onSuccess = { _characters.value = Result.success(it) },
                onError = { error ->
                    _characters.value = Result.failure(error)
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}
