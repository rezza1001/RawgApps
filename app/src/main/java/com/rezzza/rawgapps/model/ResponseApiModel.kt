package com.rezzza.rawgapps.model

data class ResponseApiModel (
    var count : Int ?= 0,
    var next : String = "",
    var previous : String = "",
    var results : ArrayList<results> = ArrayList(),
    var seo_title : String = "",
    var seo_description : String = "",
    var seo_keywords : String = "",
    var noindex : Boolean = false,
    var nofollow : Boolean = false,
    var description : String = "",
)

data class results(
    var id : Int ?= 0,
    var slug : String ?= "",
    var name : String ?= "",
    var released : String ?= "",
    var tba : String ?= "",
    var background_image : String ?= "",
    var rating : Double ?= 0.0,
    var rating_top : Double ?= 0.0,
    var ratings_count : Int ?= 0,
    var reviews_text_count : Int ?= 0,
    var added : Int ?= 0,
    var metacritic : Int ?= 0,
    var playtime : Int ?= 0,
    var suggestions_count : Int ?= 0,
)