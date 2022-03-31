package com.henry.marvelmahle.data.model.characters

import android.os.Parcelable
import com.henry.marvelmahle.data.model.characters.CharacterData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterResponse(val data: CharacterData) : Parcelable