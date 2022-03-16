package com.example.distantcontroll2.connection

import android.util.Log
import java.io.*
import java.net.Socket
import java.util.*

class Socket(mHost: String, mPort: Int) : Thread() {
    private val TAG = "SocketClass"

    private var host: String = mHost
    private var port: Int = mPort
    var clientSocket: Socket = Socket(host, port)

    private val reader: Scanner = Scanner(clientSocket.getInputStream())//отправлять серверу сообщения

    private val writer: BufferedWriter = BufferedWriter(OutputStreamWriter(clientSocket.getOutputStream()))

    init {
        Log.d(TAG, "Socket has been initialized with ${clientSocket.isConnected}")
    }



    fun send(msg: String) {
        writer.write("$msg \n")
        writer.flush()
    }
}