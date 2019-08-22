package com.sudhan.assignments.network

import android.content.Context
import android.util.Log
import com.sudhan.assignments.players.model.PlayersResponseModel
import com.sudhan.assignments.players.presenter.IPlayersPresenter
import com.sudhan.assignments.utils.Utility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PlayersNetworkManager(var iPlayersPresenter: IPlayersPresenter, var context: Context) {
    val apiEndpoints = NetworkUtil.retrofitHelper()?.create(ApiEndpoints::class.java)

    fun getPlayersList(page:Int, searchKey: String) {
        Log.e("PlayersNetworkManager", "Page:::"+page)
        apiEndpoints?.getAllPlayers(page, searchKey, 20)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<PlayersResponseModel>() {
                override fun onSuccess(playersResponseModel: PlayersResponseModel) {
                    iPlayersPresenter.onApiSuccess(playersResponseModel)
                }

                override fun onError(e: Throwable) {
                    val response = Utility.getPlayersApiResponseOffline(context)
                    if(response == null)
                        iPlayersPresenter.onApiFailure("Something went wrong!!!")
                    else
                        iPlayersPresenter.onHandleOffline()
                }
            })
    }
}