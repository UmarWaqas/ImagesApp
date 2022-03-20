package com.example.imagesapp.models

import android.os.Parcelable
//import kotlinx.parcelize.Parcelize
import kotlinx.android.parcel.Parcelize

@Parcelize
class Categories (
    var id : Int,
    val name : String,
    var movieList :List<Hits>,
    var laoutId:Int

    ): Parcelable