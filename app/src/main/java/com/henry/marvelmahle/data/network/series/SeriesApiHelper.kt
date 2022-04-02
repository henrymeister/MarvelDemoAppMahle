package com.henry.marvelmahle.data.network.series

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.series.SeriesResponse
import retrofit2.Response

interface SeriesApiHelper {
    suspend fun getCharacterSeries(characterId: CharacterId): Response<SeriesResponse>
}