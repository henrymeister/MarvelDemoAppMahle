package com.henry.marvelmahle.data.network

import com.henry.marvelmahle.data.model.Character
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getCharacters(): Response<Character> {
        return apiService.getCharacters()
    }
}