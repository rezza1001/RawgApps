package com.rezzza.rawgapps.ui.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.balysv.materialripple.MaterialRippleLayout
import com.bumptech.glide.Glide
import com.rezzza.rawgapps.R
import com.rezzza.rawgapps.database.table.GamesDB
import com.rezzza.rawgapps.model.GamesModel
import com.rezzza.rawgapps.viewmodel.DetailViewModel

class DetailGamesActivity : AppCompatActivity() {

    var mrly_back       : MaterialRippleLayout ?= null
    var mrly_favorite   : MaterialRippleLayout ?= null
    var imvw_image      : ImageView ?= null
    var imvw_favorite      : ImageView ?= null
    var txvw_title      : TextView ?= null
    var txvw_release    : TextView ?= null
    var txvw_rating     : TextView ?= null
    var txvw_palyed     : TextView ?= null
    var txvw_desc       : TextView ?= null
    var txvw_publisher  : TextView ?= null

    var favorite : Boolean = false
    var gamesDB = GamesDB()

    private lateinit var vimeModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mrly_back       = findViewById(R.id.mrly_back)
        imvw_image      = findViewById(R.id.imvw_image)
        txvw_title      = findViewById(R.id.txvw_title)
        txvw_release    = findViewById(R.id.txvw_release)
        txvw_rating     = findViewById(R.id.txvw_rating)
        txvw_desc       = findViewById(R.id.txvw_desc)
        txvw_palyed     = findViewById(R.id.txvw_palyed)
        txvw_publisher  = findViewById(R.id.txvw_publisher)
        mrly_favorite   = findViewById(R.id.mrly_favorite)
        imvw_favorite   = findViewById(R.id.imvw_favorite)

        initListener()
        initData()
    }

    fun initListener(){
        mrly_back!!.setOnClickListener {
            onBackPressed()
        }
        mrly_favorite!!.setOnClickListener {
            if (favorite){
                gamesDB.unFavorite(this)
            }
            else {
                gamesDB.favorite(this)
            }
            checkFavorite(gamesDB.id)
        }
    }

    private fun initData(){
        vimeModel = ViewModelProvider(this)[DetailViewModel::class.java]
        val data = intent.getSerializableExtra("data") as GamesModel

        checkFavorite(data.id)


        Glide.with(this).load(data.imageUrl).into(imvw_image!!)

        vimeModel.getDetailGame(data.id)
        vimeModel.postModelListLiveData?.observe(this, Observer {
            if (it != null){
                Glide.with(this).load(it.background_image).into(imvw_image!!)
                var publisher = ""
                for (sPub in it.publishers) {
                     if (publisher.isEmpty()){
                         publisher = sPub.name
                     }
                    else {
                         publisher += ", "+it.name
                     }
                }
                txvw_publisher!!.text = publisher
                txvw_title!!.text = it.name

                val release = "Release date "+ it.released
                txvw_release!!.text = release
                txvw_rating!!.text = it.rating.toString()

                val played = it.playtime.toString()+" played"
                txvw_palyed!!.text = played

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    txvw_desc!!.text = Html.fromHtml(it.description,Html.FROM_HTML_MODE_COMPACT)
                }
                else {
                    txvw_desc!!.text = Html.fromHtml(it.description)
                }

                gamesDB.id      = it.id
                gamesDB.title   = it.name
                gamesDB.release = it.released
                gamesDB.image   = it.background_image
                gamesDB.ratting = it.rating
            }
        })
    }

    private fun checkFavorite(id : Int){
        gamesDB = GamesDB()
        gamesDB.getData(this,id)
        favorite = gamesDB.id > 0

        if (favorite){
            imvw_favorite?.setColorFilter(Color.RED)
        }
        else {
            imvw_favorite?.setColorFilter(Color.WHITE)
        }
    }

}