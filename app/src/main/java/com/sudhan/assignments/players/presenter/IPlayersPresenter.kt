package com.sudhan.assignments.players.presenter

import com.sudhan.assignments.players.model.PlayersResponseModel

interface IPlayersPresenter {
    fun onGetPlayersList(pageCount: Int, searchKey: String)
    fun onApiSuccess(playersResponseModel: PlayersResponseModel)
    fun onFilterBySearch(searchKey: String)
    fun onCheckLastPage(currentPage: Int, totalPage: Int): Boolean
    fun onHandleOffline()
    fun onApiFailure(message: String)
}