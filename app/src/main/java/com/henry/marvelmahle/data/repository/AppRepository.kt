package com.henry.marvelmahle.data.repository

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResponse
import com.henry.marvelmahle.data.model.comic.ComicResponse
import com.henry.marvelmahle.data.model.series.SeriesResponse
import com.henry.marvelmahle.data.network.ApiHelper
import retrofit2.Response

class AppRepository (
    private val apiHelper: ApiHelper
) {
    suspend fun getCharacters(): Response<CharacterResponse> {
        return apiHelper.getCharacters()
    }

    suspend fun getCharacterSeries(characterId: CharacterId): Response<SeriesResponse> {
        return apiHelper.getCharacterSeries(characterId)
    }

    suspend fun getCharacterComics(characterId: CharacterId): Response<ComicResponse> {
        return apiHelper.getCharacterComics(characterId)
    }
}