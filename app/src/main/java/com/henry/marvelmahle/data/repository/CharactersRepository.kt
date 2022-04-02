package com.henry.marvelmahle.data.repository

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResponse
import com.henry.marvelmahle.data.network.character.CharacterApiHelper
import retrofit2.Response

class CharactersRepository (
    private val apiHelper: CharacterApiHelper
) {
    suspend fun getCharacters(offset: Int = 0): Response<CharacterResponse> {
        return apiHelper.getCharacters(offset)
    }

    suspend fun getCharacterById(characterId: CharacterId): Response<CharacterResponse> {
        return apiHelper.getCharacterById(characterId)
    }

    suspend fun searchCharacter(nameStartsWith: String): Response<CharacterResponse> {
        return apiHelper.searchCharacter(nameStartsWith)
    }
}