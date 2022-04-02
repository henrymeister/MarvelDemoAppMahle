package com.henry.marvelmahle.data.network.character

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResponse
import com.henry.marvelmahle.data.model.comic.ComicResponse
import com.henry.marvelmahle.data.model.series.SeriesResponse
import retrofit2.Response

class CharacterApiHelperImpl(private val apiService: CharacterApiService) : CharacterApiHelper {
    override suspend fun getCharacters(offset: Int): Response<CharacterResponse> {
        return apiService.getCharacters(offset = offset)
    }

    override suspend fun getCharacterById(characterId: CharacterId): Response<CharacterResponse> {
        return apiService.getCharacterById(characterId)
    }

    override suspend fun searchCharacter(nameStartsWith: String): Response<CharacterResponse> {
        return apiService.searchCharacter(nameStartsWith)
    }
}