package com.smarteryo.computerone.ui.splash

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smarteryo.computerone.ui.base.BaseFragment
import com.smarteryo.computerone.ui.main.MainActivity
import com.smarteryo.computerone.R
import com.smarteryo.computerone.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash){

    private val mainActivityContext: MainActivity by lazy(LazyThreadSafetyMode.NONE) {
        (activity as MainActivity)
    }


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivityContext.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        findNavController().navigate(R.id.action_splashFragment_to_menuFragment)
    }
}