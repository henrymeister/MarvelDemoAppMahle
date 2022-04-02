package com.henry.marvelmahle.data.network.comics

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.comic.ComicResponse
import com.henry.marvelmahle.data.network.character.CharacterApiService.Companion.CHARACTER_BY_ID_PATH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicsApiService {
    companion object {
        const val CHARACTER_COMICS_PATH = "$CHARACTER_BY_ID_PATH/comics"
    }

    @GET(CHARACTER_COMICS_PATH)
    suspend fun getCharacterComics(
        @Path("characterId") characterId: CharacterId,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): Response<ComicResponse>
}