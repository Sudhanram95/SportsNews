package com.sudhan.assignments.games.model

import com.google.gson.annotations.SerializedName

class GameModel {
    @SerializedName("home_team")
    lateinit var homeTeam:TeamModel
    @SerializedName("visitor_team")
    lateinit var visitorTeam:TeamModel
}