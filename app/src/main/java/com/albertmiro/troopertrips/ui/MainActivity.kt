package com.albertmiro.troopertrips.ui

import android.os.Bundle
import com.albertmiro.troopertrips.R
import com.albertmiro.troopertrips.ui.base.BaseActivity

class MainActivity : BaseActivity() {

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
