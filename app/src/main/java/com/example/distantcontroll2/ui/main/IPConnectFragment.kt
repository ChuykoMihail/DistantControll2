package com.example.distantcontroll2.ui.main

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.distantcontroll2.R

class IPConnectFragment: DialogFragment(){
    private lateinit var viewModel: FitstControllViewModel
    private val TAG = "IPConnectFragment"

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel = ViewModelProvider(requireActivity())[FitstControllViewModel::class.java]
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            val viewForFragment = inflater.inflate(R.layout.ip_connection_dialog, null)
            builder.setView(viewForFragment)
                    // Add action buttons
                    .setPositiveButton("Yes",
                            DialogInterface.OnClickListener { dialog, id ->
                                val ipInput = viewForFragment.findViewById<EditText>(R.id.ipinput)
                                viewModel.ip.value = ipInput.text.toString()
                                Log.d(TAG, "IP was given")
                            })
                    .setNegativeButton("No",
                            DialogInterface.OnClickListener { dialog, id ->
                                getDialog()?.cancel()
                            })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}