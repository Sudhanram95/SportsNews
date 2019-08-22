package com.sudhan.assignments.activity.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import com.sudhan.assignments.R
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.content.LocalBroadcastManager
import android.support.v4.view.ViewPager
import android.widget.TextView
import com.sudhan.assignments.games.view.GamesFragment
import com.sudhan.assignments.players.view.PlayersAdapter
import com.sudhan.assignments.players.view.PlayersFragment


class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        val txtPlayerCount = findViewById<TextView>(R.id.txt_players_count)
        val txtGamesCount = findViewById<TextView>(R.id.txt_games_count)
        viewPager = findViewById(R.id.viewpager)
        tabLayout = findViewById(R.id.tabs)

        setSupportActionBar(toolbar)
        collapsingToolbarLayout.isTitleEnabled = false

        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        LocalBroadcastManager.getInstance(this).registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val playerCount = intent.getIntExtra("players", 0)
                txtPlayerCount.text = playerCount.toString()
            }
        }, IntentFilter("Player_Count"))

        LocalBroadcastManager.getInstance(this).registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val gamesCount = intent.getIntExtra("games", 0)
                txtGamesCount.text = gamesCount.toString()
            }
        }, IntentFilter("Game_Count"))

//        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
//        mainViewModel.getPlayersCount().observe(this, Observer { playersCount ->
//            txtPlayerCount.text = playersCount.toString()
//        })

        val appBarLayout = findViewById(R.id.app_bar_layout) as AppBarLayout
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = false
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar.setTitle("The NBA Scout")
                    isShow = true
                } else if (isShow) {
                    toolbar.setTitle("")
                    isShow = false
                }
            }
        })
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val viewPagerAdapter = ViewPagerAdapter(getSupportFragmentManager())
        viewPagerAdapter.addFragment(PlayersFragment(), "Players")
        viewPagerAdapter.addFragment(GamesFragment(), "Games")
        viewPager.adapter = viewPagerAdapter
    }
}
