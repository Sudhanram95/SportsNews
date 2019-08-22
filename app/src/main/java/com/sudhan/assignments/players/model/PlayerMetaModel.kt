package com.sudhan.assignments.players.model

import com.google.gson.annotations.SerializedName

class PlayerMetaModel {
    @SerializedName("total_pages")
    var totalPage: Int = 0
    @SerializedName("current_page")
    var currentPage: Int = 0
    @SerializedName("next_page")
    var nextPage: Int = 0
    @SerializedName("per_page")
    var perPage: Int = 0
    @SerializedName("total_count")
    var totalCount: Int = 0
}