package com.henry.marvelmahle.data.repository

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.series.SeriesResponse
import com.henry.marvelmahle.data.network.series.SeriesApiHelper
import retrofit2.Response

class SeriesRepository (
    private val apiHelper: SeriesApiHelper
) {
    suspend fun getCharacterSeries(characterId: CharacterId, offset: Int = 0): Response<SeriesResponse> {
        return apiHelper.getCharacterSeries(characterId, offset)
    }
}