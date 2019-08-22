package com.sudhan.assignments.players.view

interface IPlayersView {
    fun onApiSuccessResult(playersAdapter: PlayersAdapter, isLastPage: Boolean)
}