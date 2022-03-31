package com.henry.marvelmahle.data.network

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResponse
import com.henry.marvelmahle.data.model.series.SeriesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val PATH = "characters"
    }

    @GET(PATH)
    suspend fun getCharacters(): Response<CharacterResponse>

    //todo change this
    @GET(PATH)
    suspend fun getCharacterSeries(characterId: CharacterId): Response<SeriesResponse>
}