package com.rezzza.rawgapps.model

data class DetailApiModel (
    val id : Int,
    val name : String,
    val description : String,
    val released : String,
    val background_image : String,
    val rating : Double,
    val playtime : Int,
    val publishers: ArrayList<Publishers>
    )

data class Publishers(
    val id : Int,
    val games_count : Int,
    val slug : String,
    val name : String,
    val image_background : String
)