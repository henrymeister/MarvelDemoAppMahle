package com.henry.marvelmahle.data.network.character

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResponse
import retrofit2.Response

interface CharacterApiHelper {
    suspend fun getCharacters(): Response<CharacterResponse>
    suspend fun getCharacterById(characterId: CharacterId): Response<CharacterResponse>
    suspend fun searchCharacter(nameStartsWith: String): Response<CharacterResponse>
}