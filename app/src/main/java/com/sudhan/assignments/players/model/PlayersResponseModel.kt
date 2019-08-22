package com.sudhan.assignments.players.model

import com.google.gson.annotations.SerializedName

class PlayersResponseModel {
    @SerializedName("data")
    var playerList = ArrayList<PlayerModel>()
    @SerializedName("meta")
    lateinit var meta: PlayerMetaModel
}