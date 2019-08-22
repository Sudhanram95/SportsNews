package com.sudhan.assignments.games.model

import com.google.gson.annotations.SerializedName

class GamesResponseModel {
    @SerializedName("data")
    var gamesList = ArrayList<GameModel>()
}