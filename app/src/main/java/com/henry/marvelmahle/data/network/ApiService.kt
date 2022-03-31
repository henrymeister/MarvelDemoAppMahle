package com.henry.marvelmahle.data.network

import com.henry.marvelmahle.data.model.Character
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val PATH = "characters"
    }

    @GET(PATH)
    suspend fun getCharacters(): Response<Character>
}