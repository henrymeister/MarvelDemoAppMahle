package com.henry.marvelmahle.data.network

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResponse
import com.henry.marvelmahle.data.model.comic.ComicResponse
import com.henry.marvelmahle.data.model.series.SeriesResponse
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getCharacters(): Response<CharacterResponse> {
        return apiService.getCharacters()
    }

    override suspend fun getCharacterById(characterId: CharacterId): Response<CharacterResponse> {
        return apiService.getCharacterById(characterId)
    }

    override suspend fun getCharacterSeries(characterId: CharacterId): Response<SeriesResponse> {
        return apiService.getCharacterSeries(characterId)
    }

    override suspend fun getCharacterComics(characterId: CharacterId): Response<ComicResponse> {
        return apiService.getCharacterComics(characterId)
    }

    override suspend fun searchCharacter(nameStartsWith: String): Response<CharacterResponse> {
        return apiService.searchCharacter(nameStartsWith)
    }
}