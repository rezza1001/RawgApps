package com.rezzza.rawgapps.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rezzza.rawgapps.R
import com.rezzza.rawgapps.model.GamesModel
import com.rezzza.rawgapps.ui.adapter.GamesAdapter
import com.rezzza.rawgapps.viewmodel.FavoriteViewModel
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

    }

    override fun initListener() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadData(){
        if (context == null){
            return
        }
        viewModel.getAllFavorite(requireContext())
//        viewModel.postModelListLiveData?.observe(viewLifecycleOwner, Observer {
//
//            if (it != null){
//                val data = it.results
//                for (item in data){
//                    val model = GamesModel()
//                    model.id = item.id!!
//                    model.title = item.name!!
//                    model.rating = item.rating!!
//                    model.imageUrl = item.background_image!!
//
//                    val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
//                    model.releaseDate = formatDate.parse(item.released!!) as Date
//                    listGames.add(model)
//                }
//                adapterGame?.notifyDataSetChanged()
//
//            }
//            else {
//                Log.d("TAGRZ","IT IS NULL")
//            }
//
//        })

    }
}