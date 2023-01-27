package com.udacity.shoestore.models
import android.content.Context
import android.os.Parcelable
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.parcel.Parcelize


@Parcelize

data class Shoe(var name: String, var size: String, var company: String, var description: String,
                val images: List<String> = mutableListOf()) : Parcelable
