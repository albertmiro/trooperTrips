package com.albertmiro.troopertrips.ui

import android.os.Bundle
import com.albertmiro.troopertrips.R
import com.albertmiro.troopertrips.ui.base.BaseActivity
import com.albertmiro.troopertrips.ui.viewmodel.TripsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    // Using the Activity to create the ViewModel, would be shared between
    // the fragments due to the simplicity of the app.
    // The relation should be 1 Fragment <-> 1 ViewModel
    val tripsViewModel: TripsViewModel by viewModel()

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
