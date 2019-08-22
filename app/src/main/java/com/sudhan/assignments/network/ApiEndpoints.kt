package com.sudhan.assignments.network

import com.sudhan.assignments.games.model.GamesResponseModel
import com.sudhan.assignments.players.model.PlayersResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoints {
    @GET("players")
    fun getAllPlayers(@Query("page") page:Int, @Query("Search") key: String, @Query("per_page") playerCount: Int): Single<PlayersResponseModel>

    @GET("games")
    fun getAllGames(): Single<GamesResponseModel>
}