package com.example.distantcontroll2.connection

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import java.io.*
import java.net.*
import java.net.Socket
import java.util.*

class Socket() : Thread() {
    private val TAG = "SocketClass"
    var clientSocket: Socket = Socket()
    lateinit var reader:Scanner
    lateinit var writer: BufferedWriter

    fun connect(mHost: String, mPort: Int): Boolean{
        try{
            clientSocket.connect(InetSocketAddress(mHost, mPort),4000)
        } catch (e: SocketTimeoutException){
            clientSocket = Socket()
            return false
        }
        reader = Scanner(clientSocket.getInputStream())//отправлять серверу сообщения
        writer = BufferedWriter(OutputStreamWriter(clientSocket.getOutputStream()))
        return clientSocket.isConnected
    }

    fun send(msg: String) {
        writer.write("$msg \n")
        writer.flush()
    }

    fun end(){
        send("0")
        clientSocket.close()
        clientSocket = Socket()
    }
}