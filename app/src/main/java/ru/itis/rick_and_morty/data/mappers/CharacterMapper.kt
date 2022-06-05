package ru.itis.rick_and_morty.data.mappers

import ru.itis.rick_and_morty.data.remote.dto.*
import ru.itis.rick_and_morty.domain.models.*

class CharacterMapper {

    fun map(characterDto: CharacterDto): CharacterModel {
        return CharacterModel(
            info = characterDto.info?.let { map(it) },
            results = map(characterDto.results)
        )
    }

    private fun map(results: List<Result>): List<ResultModel> {
        return results.map {
            map(it)
        }
    }

    fun map(info: Info): InfoModel {
        return InfoModel(
            count = info.count,
            next = info.next,
            pages = info.pages,
            prev = info.prev
        )
    }

    fun map(result: Result): ResultModel {
        return ResultModel(
            id = result.id,
            name = result.name,
            status = result.status,
            species = result.species,
            type = result.type,
            gender = result.gender,
            origin = map(result.origin),
            location = map(result.location),
            image = result.image,
            episode = result.episode,
            url = result.url,
            created = result.created
        )
    }

    fun map(location: Location): LocationModel {
        return LocationModel(
            name = location.name,
            url = location.url
        )
    }

    fun map(origin: Origin): OriginModel {
        return OriginModel(
            name = origin.name,
            url = origin.url
        )
    }
}