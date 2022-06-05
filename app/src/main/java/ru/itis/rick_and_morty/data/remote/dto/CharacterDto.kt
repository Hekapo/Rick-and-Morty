package ru.itis.rick_and_morty.data.remote.dto


data class CharacterDto(val info: Info? = null, val results: List<Result>)

data class Info(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)

data class Result(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String

)

data class Location(
    val name: String,
    val url: String
)

data class Origin(
    val name: String,
    val url: String
)
