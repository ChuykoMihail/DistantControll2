package com.example.distantcontroll2.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.distantcontroll2.R
import io.github.controlwear.virtual.joystick.android.JoystickView
import kotlin.math.PI
import kotlin.math.sin
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import com.example.distantcontroll2.connection.Socket
import kotlin.concurrent.thread

class FirstControllFragment : Fragment() {

    companion object {
        fun newInstance() = FirstControllFragment()
    }

    private lateinit var connectionButton: Button
    private lateinit var disconnectionButton: Button
    private lateinit var settingsButton: ImageButton
    private lateinit var joystickView: JoystickView
    private lateinit var leftWheelText: TextView
    private lateinit var rightWheelText: TextView
    private lateinit var viewModel: FitstControllViewModel

    private val firstControllViewModel:FitstControllViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fitst_controll_fragment, container, false)
        connectionButton = view.findViewById(R.id.connection_button)
        disconnectionButton = view.findViewById(R.id.disconnection_button)
        settingsButton = view.findViewById(R.id.settingsImageButton)
        joystickView = view.findViewById(R.id.joystick)
        leftWheelText = view.findViewById(R.id.left_wheel_power)
        rightWheelText = view.findViewById(R.id.right_wheel_power)
        var leftWheelPower: Int
        var rightWheelPower: Int
        val viewModel = ViewModelProvider(requireActivity())[FitstControllViewModel::class.java]

        joystickView.setOnMoveListener { angle, strength ->
            val (left, right) = wheelControl(angle)
            leftWheelPower = (255*(strength.toDouble()/100) * left).toInt()
            rightWheelPower = (255*(strength.toDouble()/100) * right).toInt()
            leftWheelText.setText(leftWheelPower.toString())
            rightWheelText.setText(rightWheelPower.toString())
            if (viewModel.isConnect) {
                viewModel.sendMessage("$leftWheelPower $rightWheelPower")
            }
        }

        connectionButton.setOnClickListener {
            IPConnectFragment().apply {
                show(this@FirstControllFragment.parentFragmentManager,"ConnectionDialog")
            }
        }

        disconnectionButton.setOnClickListener{
            viewModel.isConnect = false
            viewModel.ip.value = ""
            viewModel.disconnect(activity)
        }

        viewModel.ip.observe(viewLifecycleOwner, Observer {
            if (it != "") viewModel.connect(it, 4004, activity)
        })
        return view
    }

    fun wheelControl(angle: Int): Pair<Double,Double>{
        var left = 0.0
        var right = 0.0
        when (angle){
            in 0..90 -> {left = 1.0; right = sin(angle * PI / 180)}
            in 91..180 -> {left = sin(angle * PI / 180); right = 1.0}
            in 181..270 -> {left = -1.0; right = sin(angle * PI / 180)}
            in 271..360 -> {right = -1.0; left = sin(angle * PI / 180)}
        }
        return Pair(left, right)
    }

    override fun onStop() {
        super.onStop()
    }
}