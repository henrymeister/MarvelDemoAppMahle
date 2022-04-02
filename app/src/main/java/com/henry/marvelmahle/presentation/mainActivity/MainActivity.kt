package com.henry.marvelmahle.presentation.mainActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.henry.marvelmahle.R
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    // region PROPERTIES ---------------------------------------------------------------------------

    private val navController by lazy { findNavController(R.id.navHostFragment) }
    // endregion

    // region LIFECYCLE ----------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToCharactersMainFragment()
    }
    // endregion

    // region NAVIGATION ----------------------------------------------------------------------------

    private fun navigateToCharactersMainFragment() {
        navController.navigate(R.id.characterMainFragment)
    }
    // endregion
}