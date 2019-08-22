package com.sudhan.assignments.players.model

import com.google.gson.annotations.SerializedName

class TeamModel {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("abbreviation")
    lateinit var abbreviation: String
    @SerializedName("city")
    lateinit var city: String
    @SerializedName("conference")
    lateinit var conference: String
    @SerializedName("division")
    lateinit var division: String
    @SerializedName("full_name")
    lateinit var fullName: String
    @SerializedName("name")
    lateinit var name: String
}