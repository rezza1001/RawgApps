package com.rezzza.rawgapps.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.balysv.materialripple.MaterialRippleLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.rezzza.rawgapps.R
import com.rezzza.rawgapps.model.GamesModel
import com.rezzza.rawgapps.ui.adapter.GamesAdapter
import com.rezzza.rawgapps.viewmodel.FindViewModel
import java.text.SimpleDateFormat
import java.util.*

class SearchActivity : AppCompatActivity() {

    var edtx_search : EditText ?= null
    var imvw_clear : ImageView ?= null
    var shmr_load : ShimmerFrameLayout?= null

    private val listGames = ArrayList<GamesModel>()
    private var adapterGame : GamesAdapter?= null

    private lateinit var viewModel: FindViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        edtx_search = findViewById(R.id.edtx_search)
        imvw_clear = findViewById(R.id.imvw_clear)
        imvw_clear?.visibility = View.INVISIBLE

        shmr_load = findViewById(R.id.shmr_load)
        shmr_load?.visibility = View.GONE

        val rcvw_data = findViewById<RecyclerView>(R.id.rcvw_data)
        rcvw_data.layoutManager = LinearLayoutManager(this)

        adapterGame = GamesAdapter(this, listGames)
        rcvw_data.adapter = adapterGame

        initListener()
        init()
    }

    fun init(){
        viewModel = ViewModelProvider(this)[FindViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initListener(){
        findViewById<MaterialRippleLayout>(R.id.mrly_back).setOnClickListener { onBackPressed() }

        edtx_search?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(text: Editable?) {
                if (text.toString().isEmpty() && imvw_clear?.visibility == View.VISIBLE){
                    imvw_clear?.visibility = View.INVISIBLE
                }
                else if (text.toString().isNotEmpty() && imvw_clear?.visibility == View.INVISIBLE){
                    imvw_clear?.visibility = View.VISIBLE
                }

                mainHandler.removeMessages(1)
                if (text.toString().isNotEmpty()){
                    mainHandler.sendEmptyMessageDelayed(1, 1000)
                }
                else {
                    if (shmr_load!!.visibility == View.VISIBLE){
                        shmr_load!!.stopShimmerAnimation()
                        shmr_load!!.visibility = View.GONE

                        listGames.clear()
                        adapterGame?.notifyDataSetChanged()
                    }
                }
            }

        })

        imvw_clear?.setOnClickListener {
            edtx_search?.text = null
            listGames.clear()
            adapterGame?.notifyDataSetChanged()
        }

        adapterGame!!.setOnSelectListener(object  : GamesAdapter.OnSelectListener {
            override fun onSelect(data: GamesModel) {
                val intent = Intent(applicationContext, DetailGamesActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }

        })
    }

    private val mainHandler = Handler(Looper.getMainLooper()) {
        if (shmr_load!!.visibility == View.GONE) {
            shmr_load!!.startShimmerAnimation()
            shmr_load!!.visibility = View.VISIBLE
        }
        callAPI()
        true
    }

    @SuppressLint("NotifyDataSetChanged")
    fun callAPI(){
        listGames.clear()
        viewModel.findGames(edtx_search?.text.toString().trim())
        viewModel.postModelListLiveData?.observe(this) {
            if (it != null) {
                val data = it.results
                for (item in data) {
                    val model = GamesModel()
                    model.id = item.id!!
                    model.title = item.name!!
                    model.rating = item.rating!!
                    if (item.background_image != null) {
                        model.imageUrl = item.background_image!!
                    }

                    if (item.released != null) {
                        val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        model.releaseDate = formatDate.parse(item.released!!) as Date
                    }
                    listGames.add(model)
                }
                adapterGame?.notifyDataSetChanged()
            } else {
                Log.d("TAGRZ", "IT IS NULL")
            }
            if (shmr_load!!.visibility == View.VISIBLE) {
                shmr_load!!.stopShimmerAnimation()
                shmr_load!!.visibility = View.GONE
            }
        }
    }

}