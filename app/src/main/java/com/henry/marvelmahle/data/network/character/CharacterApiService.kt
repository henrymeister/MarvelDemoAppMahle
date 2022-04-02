package com.henry.marvelmahle.data.network.character

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {
    companion object {
        const val PATH = "characters"
        const val CHARACTER_ID_PARAM = "characterId"
        const val CHARACTER_BY_ID_PATH = "$PATH/{$CHARACTER_ID_PARAM}"
    }

    @GET(PATH)
    suspend fun getCharacters(
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 60
    ): Response<CharacterResponse>

    @GET(CHARACTER_BY_ID_PATH)
    suspend fun getCharacterById(
        @Path("characterId") characterId: CharacterId
    ): Response<CharacterResponse>

    @GET(PATH)
    suspend fun searchCharacter(
        @Query("nameStartsWith") query: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 60
    ): Response<CharacterResponse>
}