package com.rezzza.rawgapps.model

import java.io.Serializable
import java.util.*

class GamesModel : Serializable{

    var id : Int = 0
    var title : String = ""
    var imageUrl : String = ""
    var rating : Double = 0.0
    var releaseDate : Date ? = Date()
    var isLoading : Boolean = false



}