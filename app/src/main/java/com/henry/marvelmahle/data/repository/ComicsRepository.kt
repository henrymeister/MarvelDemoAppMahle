package com.henry.marvelmahle.data.repository

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.comic.ComicResponse
import com.henry.marvelmahle.data.network.comics.ComicsApiHelper
import retrofit2.Response

class ComicsRepository (
    private val apiHelper: ComicsApiHelper
) {
    suspend fun getCharacterComics(characterId: CharacterId): Response<ComicResponse> {
        return apiHelper.getCharacterComics(characterId)
    }
}