package com.sudhan.assignments.games.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sudhan.assignments.R
import com.sudhan.assignments.games.presenter.GamesPresenter

class GamesFragment : Fragment(), IGamesView{

    lateinit var rvGames: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_games, container, false)
        rvGames = view.findViewById(R.id.rv_games)
        val iGamesPresenter = GamesPresenter(activity, this)
        iGamesPresenter.onGetAllGames()
        return view
    }

    override fun onApiSuccessResult(gamesAdapter: GamesAdapter) {
        rvGames.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvGames.adapter = gamesAdapter
    }
}
