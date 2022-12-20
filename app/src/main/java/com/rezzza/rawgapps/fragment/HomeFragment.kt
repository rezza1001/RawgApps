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
import com.rezzza.rawgapps.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : MyFragment() {

    private val listGames = ArrayList<GamesModel>()
    private var adapterGame : GamesAdapter ?= null
    private var isLoading : Boolean = false
    private lateinit var vm:HomeViewModel
    private var page : Int = 1

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
        vm.fetchAllPosts(page)
        vm.postModelListLiveData?.observe(viewLifecycleOwner, Observer {
            if (it != null){
                val data = it.results
                for (item in data){
                    Log.d("TAGRZ","Data Page $page : "+item.name)
                    val model = GamesModel()
                    model.title = item.name!!
                    model.rating = item.rating!!
                    model.imageUrl = item.background_image!!

                    val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    model.releaseDate = formatDate.parse(item.released!!) as Date
                    listGames.add(model)
                }
                adapterGame?.notifyDataSetChanged()
                page ++
            }
            else {
                Log.d("TAGRZ","IT IS NULL")
            }
            isLoading = false
        })

    }


}