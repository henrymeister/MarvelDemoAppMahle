package com.henry.marvelmahle.data.network.series

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResponse
import com.henry.marvelmahle.data.model.comic.ComicResponse
import com.henry.marvelmahle.data.model.series.SeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApiService {
    companion object {
        const val PATH = "characters"
        const val CHARACTER_ID_PARAM = "characterId"
        const val CHARACTER_BY_ID_PATH = "$PATH/{$CHARACTER_ID_PARAM}"

        const val CHARACTER_SERIES_PATH = "$CHARACTER_BY_ID_PATH/series"
    }

    @GET(CHARACTER_SERIES_PATH)
    suspend fun getCharacterSeries(
        @Path("characterId") characterId: CharacterId
    ): Response<SeriesResponse>
}