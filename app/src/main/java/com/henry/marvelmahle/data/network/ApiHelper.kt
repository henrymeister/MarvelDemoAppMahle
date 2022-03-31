package com.henry.marvelmahle.data.network

import com.henry.marvelmahle.data.model.Character
import retrofit2.Response

interface ApiHelper {
    suspend fun getCharacters(): Response<Character>
}