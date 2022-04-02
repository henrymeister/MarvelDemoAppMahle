package com.henry.marvelmahle.data.network.series

import com.henry.marvelmahle.data.model.characters.CharacterId
import com.henry.marvelmahle.data.model.characters.CharacterResponse
import com.henry.marvelmahle.data.model.comic.ComicResponse
import com.henry.marvelmahle.data.model.series.SeriesResponse
import retrofit2.Response

class SeriesApiHelperImpl(private val apiService: SeriesApiService) : SeriesApiHelper {

    override suspend fun getCharacterSeries(characterId: CharacterId): Response<SeriesResponse> {
        return apiService.getCharacterSeries(characterId)
    }
}