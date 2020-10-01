package com.albertmiro.troopertrips.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.albertmiro.troopertrips.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadTripsListFragment()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
