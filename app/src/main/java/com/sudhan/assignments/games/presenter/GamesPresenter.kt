package com.sudhan.assignments.games.presenter

import android.content.Context
import com.sudhan.assignments.games.model.GamesResponseModel
import com.sudhan.assignments.games.view.GamesAdapter
import com.sudhan.assignments.games.view.IGamesView
import com.sudhan.assignments.network.GamesNetworkManager
import com.sudhan.assignments.utils.Utility

class GamesPresenter(var context: Context?, var iGamesView: IGamesView): IGamesPresenter {

    override fun onGetAllGames() {
        GamesNetworkManager(context!!, this).getAllGames()
    }

    override fun onApiSuccess(gamesResponseModel: GamesResponseModel) {
        Utility.saveGamesApiResponse(context, gamesResponseModel.gamesList)
        val gamesAdapter = GamesAdapter(context!!, gamesResponseModel.gamesList)
        iGamesView.onApiSuccessResult(gamesAdapter)
    }

    override fun onHandleOffline() {
        val gamesAdapter = GamesAdapter(context!!, Utility.getGamesApiResponseOffline(context!!))
        iGamesView.onApiSuccessResult(gamesAdapter)
    }

    override fun onApiFailure(message: String) {

    }
}