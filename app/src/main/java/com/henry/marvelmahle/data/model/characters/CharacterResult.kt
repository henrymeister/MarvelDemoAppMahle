package com.henry.marvelmahle.data.model.characters

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

typealias CharacterId = String

@Parcelize
data class CharacterResult(
    var id: CharacterId = "",
    var name: String = "",
    var description: String = "",
    var thumbnail: Thumbnail = Thumbnail("", ""),
    var comics: Comics = Comics(""),
    var series: Series = Series(""),
    var urls: List<Url> = ArrayList()
) : Parcelable {
    @Parcelize
    data class Thumbnail(val path: String, val extension: String) : Parcelable

    @Parcelize
    data class Url(val type: String, val url: String) : Parcelable

    @Parcelize
    data class Comics(val available: String) : Parcelable

    @Parcelize
    data class Series(val available: String) : Parcelable
}