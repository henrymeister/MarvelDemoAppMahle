package com.henry.marvelmahle.data.network

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResponse
import com.henry.marvelmahle.data.model.comic.ComicResponse
import com.henry.marvelmahle.data.model.series.SeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object {
        const val PATH = "characters"
        const val CHARACTER_ID_PARAM = "characterId"
        const val CHARACTER_BY_ID_PATH = "$PATH/{$CHARACTER_ID_PARAM}"

        const val CHARACTER_SERIES_PATH = "$CHARACTER_BY_ID_PATH/series"
        const val CHARACTER_COMICS_PATH = "$CHARACTER_BY_ID_PATH/comics"
    }

    @GET(PATH)
    suspend fun getCharacters(): Response<CharacterResponse>

    @GET(CHARACTER_SERIES_PATH)
    suspend fun getCharacterSeries(
        @Path("characterId") characterId: CharacterId
    ): Response<SeriesResponse>

    @GET(CHARACTER_COMICS_PATH)
    suspend fun getCharacterComics(
        @Path("characterId") characterId: CharacterId
    ): Response<ComicResponse>
}