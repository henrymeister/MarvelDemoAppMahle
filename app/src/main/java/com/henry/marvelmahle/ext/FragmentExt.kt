package com.henry.marvelmahle.ext

import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.henry.marvelmahle.R

fun Fragment.showInProgress() {
    try {
        loadingView()?.isVisible = true
        requireActivity().window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    } catch (e: Exception) {
        Log.e("requireActivity()", "Fragment.showInProgress()")
    }
}

fun Fragment.showInProgressTouchable() {
    try {
        loadingView()?.isVisible = true
    } catch (e: Exception) {
        Log.e("requireActivity()", "Fragment.showInProgress()")
    }
}

fun Fragment.hideInProgress() {
    try {
        loadingView()?.isVisible = false
        requireActivity().window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    } catch (e: Exception) {
        Log.e("requireActivity()", "FragmentExt")
    }
}

internal fun Fragment.loadingView(): View? {
    try {
        return view?.findViewById(R.id.progressBar)
            ?: requireActivity().findViewById(R.id.progressBar)
    } catch (e: Exception) {
        Log.e("requireActivity()", "Fragment.loadingView()")
    }

    return null
}