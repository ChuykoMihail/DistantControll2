package com.example.distantcontroll2.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.distantcontroll2.R

class MainFragment : Fragment() {

    private var callbacks: Callbacks? = null
    private lateinit var firstButton: Button
    private lateinit var secondButton: Button

    interface Callbacks{
        fun menuSelected(item: Boolean)
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.main_fragment, container, false)
        firstButton = view.findViewById(R.id.first_model_button) as Button
        secondButton = view.findViewById(R.id.second_model_button) as Button

        firstButton.setOnClickListener {
            callbacks?.menuSelected(true)
        }
        secondButton.setOnClickListener {
            callbacks?.menuSelected(false)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as MainFragment.Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }


}