package com.example.distantcontroll2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.distantcontroll2.ui.main.*
import com.example.distantcontroll2.ui.main.MainFragment

class MainActivity : AppCompatActivity(), MainFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

    override fun menuSelected(item: Boolean) {
        if (item){
            val fragment = FirstControllFragment()
            supportFragmentManager.beginTransaction().
                    replace(R.id.container, fragment).
                    commit()
        }
        else {
        }
    }
}