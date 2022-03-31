package com.henry.marvelmahle.data.model.series

typealias SeriesId = String
data class SeriesResult(
    val id: SeriesId,
    val title: String,
    val description: String,
    val thumbnail: SeriesThumbnail
) {
    data class SeriesThumbnail(val path: String, val extension: String)
}