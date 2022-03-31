package com.henry.marvelmahle.data.model.characters


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterData(val results: List<CharacterResult>) : Parcelable