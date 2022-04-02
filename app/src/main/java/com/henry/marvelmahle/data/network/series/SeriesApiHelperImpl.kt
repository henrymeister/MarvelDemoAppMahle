package com.henry.marvelmahle.data.network.series

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.series.SeriesResponse
import retrofit2.Response

class SeriesApiHelperImpl(private val apiService: SeriesApiService) : SeriesApiHelper {

    override suspend fun getCharacterSeries(characterId: CharacterId, offset: Int): Response<SeriesResponse> {
        return apiService.getCharacterSeries(characterId, offset)
    }
}