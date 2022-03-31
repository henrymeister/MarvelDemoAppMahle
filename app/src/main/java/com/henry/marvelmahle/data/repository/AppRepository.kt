package com.henry.marvelmahle.data.repository

import com.henry.marvelmahle.data.model.Character
import com.henry.marvelmahle.data.network.ApiHelper
import retrofit2.Response

class AppRepository (private val apiHelper: ApiHelper) {
    suspend fun getCharacters(): Response<Character> {
        return apiHelper.getCharacters()
    }
}