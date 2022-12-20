package com.rezzza.rawgapps.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rezzza.rawgapps.R
import com.rezzza.rawgapps.adapter.GamesAdapter
import com.rezzza.rawgapps.model.GamesModel
import com.rezzza.rawgapps.model.results
import com.rezzza.rawgapps.viewmodel.HomeViewModel
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : MyFragment() {

    private val listGames = ArrayList<GamesModel>()
    private var adapterGame : GamesAdapter ?= null
    private var isLoading : Boolean = false
    private lateinit var vm:HomeViewModel

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

        rcvw_data.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listGames.size - 1) {
                        loadData()
                        isLoading = true
                    }
                }
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this)[HomeViewModel::class.java]

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

        Handler(Looper.getMainLooper()).postDelayed({
            isLoading = false
        }, 1000)

        vm.fetchAllPosts()
        vm.postModelListLiveData?.observe(viewLifecycleOwner, Observer {
            if (it != null){
                val data = it.results
                for (item in data){
                    Log.d("TAGRZ","Data "+item.name)
                }
            }
            else {
                Log.d("TAGRZ","IT IS NULL")
            }
        })

    }


}