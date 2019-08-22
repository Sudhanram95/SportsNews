package com.sudhan.assignments.utils

import android.content.Context
import com.google.gson.Gson
import com.sudhan.assignments.players.model.PlayerModel
import kotlin.collections.ArrayList
import com.google.gson.reflect.TypeToken
import com.sudhan.assignments.games.model.GameModel


object Utility {

    fun savePlayersApiResponse(context: Context?, playersList: ArrayList<PlayerModel>) {
        val prefs = context?.getSharedPreferences("response_offline", Context.MODE_PRIVATE)
        val editor = prefs?.edit()
        editor?.remove("player_list")?.apply()
        val data = Gson().toJson(playersList)
        editor?.putString("player_list", data)
        editor?.apply()
    }

    fun getPlayersApiResponseOffline(context: Context): ArrayList<PlayerModel> {
        val prefs = context.getSharedPreferences("response_offline", Context.MODE_PRIVATE)
        val playerResponse = prefs.getString("player_list", "")
        return Gson().fromJson(playerResponse, object : TypeToken<ArrayList<PlayerModel>>() {}.type)
    }

    fun saveGamesApiResponse(context: Context?, gamesList: ArrayList<GameModel>) {
        val prefs = context?.getSharedPreferences("response_offline", Context.MODE_PRIVATE)
        val editor = prefs?.edit()
        editor?.remove("game_list")?.apply()
        val data = Gson().toJson(gamesList)
        editor?.putString("game_list", data)
        editor?.apply()
    }

    fun getGamesApiResponseOffline(context: Context): ArrayList<GameModel> {
        val prefs = context.getSharedPreferences("response_offline", Context.MODE_PRIVATE)
        val gameResponse = prefs.getString("game_list", "")
        return Gson().fromJson(gameResponse, object : TypeToken<ArrayList<GameModel>>() {}.type)
    }

}