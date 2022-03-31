package com.henry.marvelmahle.data.network

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResponse
import com.henry.marvelmahle.data.model.series.SeriesResponse
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getCharacters(): Response<CharacterResponse> {
        return apiService.getCharacters()
    }

    override suspend fun getCharacterSeries(characterId: CharacterId): Response<SeriesResponse> {
        return apiService.getCharacterSeries(characterId)
    }
}