package com.sudhan.assignments.network

import android.content.Context
import com.sudhan.assignments.games.model.GamesResponseModel
import com.sudhan.assignments.games.presenter.IGamesPresenter
import com.sudhan.assignments.utils.Utility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class GamesNetworkManager(var context: Context, var iGamesPresenter: IGamesPresenter) {
    val apiEndpoints = NetworkUtil.retrofitHelper()?.create(ApiEndpoints::class.java)

    fun getAllGames() {
        apiEndpoints?.getAllGames()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<GamesResponseModel>() {
                override fun onSuccess(gamesResponseModel: GamesResponseModel) {
                    iGamesPresenter.onApiSuccess(gamesResponseModel)
                }

                override fun onError(e: Throwable) {
                    val response = Utility.getGamesApiResponseOffline(context)
                    if(response == null)
                        iGamesPresenter.onApiFailure("Something went wrong!!!")
                    else
                        iGamesPresenter.onHandleOffline()
                }
            })
    }
}