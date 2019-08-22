package com.sudhan.assignments.players.model

import com.google.gson.annotations.SerializedName

class PlayerModel {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("first_name")
    lateinit var firstName:String
    @SerializedName("last_name")
    lateinit var lastName:String
    @SerializedName("position")
    lateinit var position:String
    @SerializedName("team")
    lateinit var team: TeamModel
}