package com.henry.marvelmahle.data.network.comics

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.comic.ComicResponse
import retrofit2.Response

class ComicsApiHelperImpl(private val apiService: ComicsApiService) : ComicsApiHelper {

    override suspend fun getCharacterComics(characterId: CharacterId, offset: Int): Response<ComicResponse> {
        return apiService.getCharacterComics(characterId, offset)
    }
}