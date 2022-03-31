package com.henry.marvelmahle.data.model

import com.google.gson.annotations.SerializedName

typealias CharacterId = String
data class Results (

    @SerializedName("id"          ) var id          : CharacterId,
    @SerializedName("name"        ) var name        : String,
    @SerializedName("description" ) var description : String,
    @SerializedName("modified"    ) var modified    : String,
    @SerializedName("resourceURI" ) var resourceURI : String,
    @SerializedName("urls"        ) var urls        : ArrayList<Urls> = arrayListOf(),
    @SerializedName("thumbnail"   ) var thumbnail   : Thumbnail      = Thumbnail(),
    @SerializedName("comics"      ) var comics      : Comics?         = Comics(),
    @SerializedName("stories"     ) var stories     : Stories?        = Stories(),
    @SerializedName("events"      ) var events      : Events?         = Events(),
    @SerializedName("series"      ) var series      : Series?         = Series()

)