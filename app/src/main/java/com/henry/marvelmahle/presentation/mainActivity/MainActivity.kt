package com.henry.marvelmahle.presentation.mainActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.henry.marvelmahle.R
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToSplashFragment()
    }
    // endregion

    //region NAVIGATION ----------------------------------------------------------------------------
    private fun navigateToSplashFragment() {
        //navController.navigate(R.id.mainFragment)
    }
    // endregion
}