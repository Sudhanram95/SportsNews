package com.sudhan.assignments.games.presenter

import com.sudhan.assignments.games.model.GamesResponseModel

interface IGamesPresenter {
    fun onGetAllGames()
    fun onApiSuccess(gamesResponseModel: GamesResponseModel)
    fun onHandleOffline()
    fun onApiFailure(message: String)
}