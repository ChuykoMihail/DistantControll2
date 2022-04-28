package com.example.distantcontroll2.ui.main

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.distantcontroll2.R
import com.example.distantcontroll2.connection.Socket
import kotlin.concurrent.thread

class FitstControllViewModel() : ViewModel() {
    var ip = MutableLiveData<String>()
    var isConnect = false
    var socket = Socket()

    fun connect(mHost: String, mPort: Int, activity: Activity?) {
        thread {
            if (socket.connect(mHost, mPort)) {
                isConnect = true
                toastMessage(activity, R.string.connection_complete)
            } else {
                toastMessage(activity, R.string.connection_fails)
            }
        }
    }

    fun sendMessage(msg: String){
        thread {
            socket.send(msg)
        }
    }

    fun disconnect(activity: Activity?){
        thread {
            socket.end()
            toastMessage(activity, R.string.connection_closed)
        }
    }

    fun toastMessage(activity: Activity?, msg: Int){
        activity?.runOnUiThread {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT)
                .show() }
    }

//    val typeOfControl: String
//        get() = controlType


}