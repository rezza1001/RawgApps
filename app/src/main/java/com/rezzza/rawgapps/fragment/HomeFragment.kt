package com.rezzza.rawgapps.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rezzza.rawgapps.R
import com.rezzza.rawgapps.adapter.GamesAdapter
import com.rezzza.rawgapps.model.GamesModel
import java.util.Date

class HomeFragment : MyFragment() {

    val listGames = ArrayList<GamesModel>()
    var adapterGame : GamesAdapter ?= null

    companion object {
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun setLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initLayout(view: View?) {
        if (view == null) {
           return
        }

        val rcvw_data = view.findViewById<RecyclerView>(R.id.rcvw_data)
        rcvw_data.layoutManager = LinearLayoutManager(activity)

        adapterGame = GamesAdapter(context, listGames)
        rcvw_data.adapter = adapterGame

        loadData()
    }

    override fun initListener() {

    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadData(){
        val model = GamesModel()
        model.title = "Pubg Mobile : Play Battle Ground"
        model.rating = 4.5
        model.imageUrl = "https://imgsrv2.voi.id/i2CyOk5DPnqTT9wRPLtKgTtoUq5olboqn4yMm2ZxDpU/auto/1200/675/sm/1/bG9jYWw6Ly8vcHVibGlzaGVycy8xMDE3NDQvMjAyMTExMDcxMzMyLW1haW4uY3JvcHBlZF8xNjM2MjY2Nzc4LmpwZw.jpg"
        model.releaseDate = Date()
        listGames.add(model)
        listGames.add(model)
        listGames.add(model)
        listGames.add(model)
        listGames.add(model)
        listGames.add(model)
        listGames.add(model)

        adapterGame?.notifyDataSetChanged()
    }
}