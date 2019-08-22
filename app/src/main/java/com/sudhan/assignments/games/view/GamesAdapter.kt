package com.sudhan.assignments.games.view

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sudhan.assignments.R
import com.sudhan.assignments.games.model.GameModel

class GamesAdapter(var context: Context, var gamesList: ArrayList<GameModel>) : RecyclerView.Adapter<GamesAdapter.Companion.MyViewHolder>() {

    companion object {
        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
        {
            var txtHomeTeam: TextView
            var txtHomeTeamFull: TextView
            var txtVisitorTeam: TextView
            var txtVisitorTeamFull: TextView
            init {
                txtHomeTeam = view.findViewById(R.id.txt_home_team_short)
                txtHomeTeamFull = view.findViewById(R.id.txt_home_team)
                txtVisitorTeam = view.findViewById(R.id.txt_visitor_team_short)
                txtVisitorTeamFull = view.findViewById(R.id.txt_visitor_team)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        val myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        val intent = Intent("Game_Count")
        intent.putExtra("games", gamesList.size)
        LocalBroadcastManager.getInstance(context!!).sendBroadcast(intent)
        return gamesList.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.txtHomeTeam.text = gamesList.get(position).homeTeam.abbreviation
        holder.txtHomeTeamFull.text = gamesList.get(position).homeTeam.fullName
        holder.txtVisitorTeam.text = gamesList.get(position).visitorTeam.abbreviation
        holder.txtVisitorTeamFull.text = gamesList.get(position).visitorTeam.fullName
    }
}