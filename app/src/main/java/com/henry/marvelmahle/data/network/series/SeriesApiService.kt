package com.henry.marvelmahle.data.network.series

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.series.SeriesResponse
import com.henry.marvelmahle.data.network.character.CharacterApiService
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApiService {
    companion object {
        const val CHARACTER_SERIES_PATH = "${CharacterApiService.CHARACTER_BY_ID_PATH}/series"
    }

    @GET(CHARACTER_SERIES_PATH)
    suspend fun getCharacterSeries(
        @Path("characterId") characterId: CharacterId,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): Response<SeriesResponse>
}