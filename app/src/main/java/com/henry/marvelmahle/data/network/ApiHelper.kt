package com.henry.marvelmahle.data.network

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResponse
import com.henry.marvelmahle.data.model.comic.ComicResponse
import com.henry.marvelmahle.data.model.series.SeriesResponse
import com.henry.marvelmahle.data.model.series.SeriesResult
import retrofit2.Response

interface ApiHelper {
    suspend fun getCharacters(): Response<CharacterResponse>
    suspend fun getCharacterSeries(characterId: CharacterId): Response<SeriesResponse>
    suspend fun getCharacterComics(characterId: CharacterId): Response<ComicResponse>
    suspend fun searchCharacter(nameStartsWith: String): Response<CharacterResponse>
}