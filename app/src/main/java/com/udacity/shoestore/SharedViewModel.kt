package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel(): ViewModel() {
    private val shoes = ArrayList<Shoe>()
    private val shoesLivedata = MutableLiveData<List<Shoe>>()

    var tmpName =""
    var tmpCompany =""
    var tmpSize =""
    var tmpDescription =""


    fun addShoe() {

        val shoe= Shoe(tmpName,tmpCompany,tmpSize,tmpDescription)

        shoes.add(shoe)
        shoesLivedata.value = shoes
    }
    fun getShoe(): MutableLiveData<List<Shoe>> {
        return shoesLivedata
    }

}


