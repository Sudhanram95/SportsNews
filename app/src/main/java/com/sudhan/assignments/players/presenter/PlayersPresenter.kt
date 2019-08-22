package com.sudhan.assignments.players.presenter

import android.content.Context
import android.util.Log
import com.sudhan.assignments.network.PlayersNetworkManager
import com.sudhan.assignments.players.model.PlayerModel
import com.sudhan.assignments.players.model.PlayersResponseModel
import com.sudhan.assignments.players.view.IPlayersView
import com.sudhan.assignments.players.view.PlayersAdapter
import com.sudhan.assignments.utils.Utility

class PlayersPresenter(var context: Context?, var iPlayersView: IPlayersView, var playersAdapter: PlayersAdapter): IPlayersPresenter {

    var originalPlayerList = ArrayList<PlayerModel>()

    override fun onGetPlayersList(pageCount: Int, searchKey: String) {
//        showProgressBar()
        PlayersNetworkManager(this, context!!).getPlayersList(pageCount, searchKey)
    }

    override fun onApiSuccess(playersResponseModel: PlayersResponseModel) {
//        hideProgressBar()
        playersAdapter.addAllPlayers(playersResponseModel.playerList)
        originalPlayerList = playersAdapter.getAllPlayers()
        Utility.savePlayersApiResponse(context, originalPlayerList)
        Log.e("PlayersNetworkManager", "TotalPage:::"+playersResponseModel.meta.totalPage)
        iPlayersView.onApiSuccessResult(playersAdapter, onCheckLastPage(playersResponseModel.meta.currentPage, playersResponseModel.meta.totalPage))
    }

    override fun onCheckLastPage(currentPage: Int, totalPage: Int): Boolean {
        return currentPage == totalPage
    }

    override fun onHandleOffline() {
        originalPlayerList= Utility.getPlayersApiResponseOffline(context!!)
        playersAdapter.addAllPlayers(originalPlayerList)
        iPlayersView.onApiSuccessResult(playersAdapter, true)
    }

    override fun onApiFailure(message: String) {

    }

    override fun onFilterBySearch(searchKey: String) {
        val players = originalPlayerList
        val filteredPlayers = players.filter { player -> (player.firstName.toLowerCase().contains(searchKey) || player.lastName.toLowerCase().contains(searchKey)) }
        playersAdapter.showSearchResults(ArrayList(filteredPlayers))
    }

//    fun showProgressBar() {
//        mProgressDialog = ProgressDialog(context)
//        mProgressDialog.setMessage("Please wait...")
//        mProgressDialog.setCancelable(false)
//        mProgressDialog.show()
//    }
//
//    fun hideProgressBar() {
//        if(mProgressDialog != null) {
//            mProgressDialog.dis
//        }
//    }
}