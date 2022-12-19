package com.rezzza.rawgapps.model

import java.io.Serializable
import java.util.*

class GamesModel : Serializable{

    var title : String = ""
    var imageUrl : String = ""
    var rating : Double = 0.0
    lateinit var releaseDate : Date



}