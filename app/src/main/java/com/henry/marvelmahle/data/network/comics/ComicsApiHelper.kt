package com.henry.marvelmahle.data.network.comics

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResponse
import com.henry.marvelmahle.data.model.comic.ComicResponse
import com.henry.marvelmahle.data.model.series.SeriesResponse
import retrofit2.Response

interface ComicsApiHelper {
    suspend fun getCharacterComics(characterId: CharacterId, offset: Int): Response<ComicResponse>
}