package ru.itis.rick_and_morty.data.remote

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.itis.rick_and_morty.data.remote.dto.CharacterDto

interface RickAndMortyApi {

    @GET("character")
    fun getAllCharacters(@Query("page") page: Int = 1): Single<Response<CharacterDto>>
}
