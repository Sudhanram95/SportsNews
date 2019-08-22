package com.sudhan.assignments.players.view

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sudhan.assignments.R
import com.sudhan.assignments.players.model.PlayerModel


class PlayersAdapter(var context: Context?) : RecyclerView.Adapter<PlayersAdapter.Companion.MyViewHolder>() {

    var playersList = ArrayList<PlayerModel>()

    companion object {
        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
        {
            var txtFirstname: TextView
            var txtLastname: TextView
            var txtTeam: TextView
            var txtTeamId: TextView
            var txtPosition: TextView
            var txtFullTeamName: TextView
            var txtRegion: TextView
            init {
                txtFirstname = view.findViewById(R.id.txt_first_name)
                txtLastname = view.findViewById(R.id.txt_last_name)
                txtTeam = view.findViewById(R.id.txt_team)
                txtTeamId = view.findViewById(R.id.txt_team_id)
                txtPosition = view.findViewById(R.id.txt_position)
                txtFullTeamName = view.findViewById(R.id.txt_team_full_name)
                txtRegion = view.findViewById(R.id.txt_region)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)
        val myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        val intent = Intent("Player_Count")
        intent.putExtra("players", playersList.size)
        LocalBroadcastManager.getInstance(context!!).sendBroadcast(intent)
        return playersList.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.txtFirstname.text = playersList.get(position).firstName
        holder.txtLastname.text = playersList.get(position).lastName
        holder.txtTeam.text = playersList.get(position).team.name
        holder.txtTeamId.text = "#${playersList.get(position).team.id}"
        holder.txtPosition.text = "Position-${playersList.get(position).position}"
        holder.txtFullTeamName.text = playersList.get(position).team.fullName
        holder.txtRegion.text = "${playersList.get(position).team.conference}/${playersList.get(position).team.division}"
    }

    fun addAllPlayers(paginationList: ArrayList<PlayerModel>) {
        for (player in paginationList) {
            playersList.add(player)
            notifyItemInserted(playersList.size - 1)
        }
    }

    fun getAllPlayers():ArrayList<PlayerModel> {
        return playersList
    }

    fun showSearchResults(result: ArrayList<PlayerModel>) {
        playersList = result
        notifyDataSetChanged()
    }
}