package com.rezzza.rawgapps.ui.fragment

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rezzza.rawgapps.R
import com.rezzza.rawgapps.model.GamesModel
import com.rezzza.rawgapps.ui.activity.DetailGamesActivity
import com.rezzza.rawgapps.ui.adapter.GamesAdapter
import com.rezzza.rawgapps.viewmodel.FavoriteViewModel
import java.text.SimpleDateFormat
import java.util.*

class FavoriteFragment : MyFragment() {

    private val listGames = ArrayList<GamesModel>()
    private var adapterGame : GamesAdapter?= null
    private lateinit var viewModel : FavoriteViewModel

    companion object{
        fun newInstance(): FavoriteFragment {
            val args = Bundle()
            val fragment = FavoriteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setLayout(): Int {
        return R.layout.fragment_favorite
    }

    override fun initLayout(view: View?) {
        if (view == null) {
            return
        }
        val rcvw_data = view.findViewById<RecyclerView>(R.id.rcvw_data)
        rcvw_data.layoutManager = LinearLayoutManager(activity)

        adapterGame = GamesAdapter(context, listGames)
        rcvw_data.adapter = adapterGame

        val intentFilter = IntentFilter()
        intentFilter.addAction("REFRESH")
        context?.registerReceiver(receiver,intentFilter )
    }

    override fun initListener() {
        adapterGame!!.setOnSelectListener(object  : GamesAdapter.OnSelectListener {
            override fun onSelect(data: GamesModel) {
                val intent = Intent(context, DetailGamesActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        loadData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadData(){
        listGames.clear()
        if (context == null){
            return
        }
        viewModel.getAllFavorite(requireContext())
        viewModel.postModelListLiveData?.observe(viewLifecycleOwner) {
            if (it != null) {
                val data = it
                for (holder in data){
                    val model = GamesModel()
                    model.id = holder.id
                    model.title = holder.title
                    model.rating = holder.ratting
                    model.imageUrl = holder.image

                    val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    model.releaseDate = formatDate.parse(holder.release) as Date
                    listGames.add(model)
                }
            }

            adapterGame?.notifyDataSetChanged()

        }
    }

    private val receiver: BroadcastReceiver = object :BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            loadData()
        }
    }

}