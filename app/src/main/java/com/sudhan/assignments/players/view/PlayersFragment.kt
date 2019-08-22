package com.sudhan.assignments.players.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast

import com.sudhan.assignments.R
import com.sudhan.assignments.customlistener.PaginationScrollListener
import com.sudhan.assignments.players.presenter.IPlayersPresenter
import com.sudhan.assignments.players.presenter.PlayersPresenter

class PlayersFragment : Fragment(), IPlayersView, TextWatcher {

    lateinit var edtSearch: EditText
    lateinit var rvPlayers: RecyclerView
    lateinit var iPlayersPresenter: IPlayersPresenter
    lateinit var linearLayoutManager: LinearLayoutManager
    var isLoading = false
    var isLastPage = false
    var currentPage = 1
    var searchKey = ""
    lateinit var playersAdapter: PlayersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_players, container, false)
        edtSearch = view.findViewById(R.id.edt_search_players)
        rvPlayers = view.findViewById(R.id.rv_players)
        playersAdapter = PlayersAdapter(context)
        iPlayersPresenter = PlayersPresenter( activity, this, playersAdapter)

        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvPlayers.layoutManager = linearLayoutManager
        rvPlayers.adapter = playersAdapter

        isLoading = true
        iPlayersPresenter.onGetPlayersList(currentPage, searchKey)

        edtSearch.addTextChangedListener(this)

        rvPlayers.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {

            override fun loadMoreItems() {
                if(searchKey.isEmpty()) {
                    isLoading = true
                    currentPage += 1
                    iPlayersPresenter.onGetPlayersList(currentPage, searchKey)
                }
            }

            override fun getTotalPageCount(): Int {
                return 125
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })
        return view
    }

    override fun onApiSuccessResult(playersAdapter: PlayersAdapter, isLast: Boolean) {
        isLoading = false
        isLastPage = isLast
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(key: Editable?) {
        searchKey = key.toString()
        iPlayersPresenter.onFilterBySearch(searchKey)
    }
}
