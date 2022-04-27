package com.example.distantcontroll2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.distantcontroll2.ui.main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FirstControllFragment.newInstance())
                    .commitNow()
        }
    }

}