package com.smarteryo.computerone.ui.menu

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.smarteryo.computerone.R
import com.smarteryo.computerone.databinding.FragmentMenuBinding
import com.smarteryo.computerone.ui.base.BaseFragment
import com.smarteryo.computerone.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : BaseFragment<FragmentMenuBinding>(R.layout.fragment_menu),
    MenuMainActionsListener {

    private val contextActivity: MainActivity by lazy(LazyThreadSafetyMode.NONE) {
        (activity as MainActivity)
    }
    private val menuViewModel: MenuViewModel by viewModels()

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contextActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding.menuMainViewModelBinding = menuViewModel
        binding.menuMainActionsListener = this
    }

    override fun menuMainTwo() {
        menuViewModel.setTimerTime(0)
        findNavController().navigate(R.id.action_menuFragment_to_secondaryMenuFragment)
    }

    override fun menuToKeepOn() {
        findNavController().navigate(R.id.action_menuFragment_to_mainTwoFragment)
    }

    override fun menuMainToSettings() {
        findNavController().navigate(R.id.action_menuFragment_to_optionFragment)
    }
}