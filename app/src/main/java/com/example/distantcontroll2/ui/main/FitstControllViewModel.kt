package com.example.distantcontroll2.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FitstControllViewModel : ViewModel() {
    var controlType = "joystick"
    var ip = MutableLiveData<String>()

//    val typeOfControl: String
//        get() = controlType


}