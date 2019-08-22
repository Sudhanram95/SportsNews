package com.sudhan.assignments.games.model

import com.google.gson.annotations.SerializedName

class TeamModel {
    @SerializedName("abbreviation")
    lateinit var abbreviation:String
    @SerializedName("full_name")
    lateinit var fullName:String
}