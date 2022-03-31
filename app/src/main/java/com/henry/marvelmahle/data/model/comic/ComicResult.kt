package com.henry.marvelmahle.data.model.comic

typealias ComicId = String
data class ComicResult(
    val id: String,
    val title: String,
    val description: String,
    val thumbnail: ComicThumbnail
) {
    data class ComicThumbnail(val path: String, val extension: String)
}