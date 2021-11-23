package com.smarteryo.computerone.ui.secondary_menu

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.smarteryo.computerone.R
import com.smarteryo.computerone.databinding.FragmentSecondaryMenuBinding
import com.smarteryo.computerone.ui.base.BaseFragment
import com.smarteryo.computerone.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondaryMenuFragment :
    BaseFragment<FragmentSecondaryMenuBinding>(R.layout.fragment_secondary_menu),
    SecondaryMenuActionsListener {

    private val contextActivity: MainActivity by lazy(LazyThreadSafetyMode.NONE) {
        (activity as MainActivity)
    }
    private val secondaryMenuViwModel: SecondaryMenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.secondaryMenuViewModelBinding = secondaryMenuViwModel
        binding.secondaryMenuActionsListener = this
    }

    private fun anim(
        mainImg: ImageButton,
        twoImg: ImageButton,
        threeImg: ImageButton,
        mainPicture: Int,
        twoPicture: Int,
        threePicture: Int
    ) {
        mainImg.setBackgroundResource(mainPicture)
        twoImg.setBackgroundResource(twoPicture)
        threeImg.setBackgroundResource(threePicture)
    }

    override fun secondaryMenuOneMin() {
        anim(
            binding.secondaryMenuOne,
            binding.secondaryMenuTwo,
            binding.secondaryMenuThree,
            R.drawable.ic_secondary_menu_one_border,
            R.drawable.ic_secondary_menu_two,
            R.drawable.ic_secondary_menu_three
        )
        secondaryMenuViwModel.setTimerTime(1)
    }

    override fun secondaryMenuTwoMin() {
        anim(
            binding.secondaryMenuTwo,
            binding.secondaryMenuOne,
            binding.secondaryMenuThree,
            R.drawable.ic_secondary_menu_two_border,
            R.drawable.ic_secondary_menu_one,
            R.drawable.ic_secondary_menu_three
        )
        secondaryMenuViwModel.setTimerTime(2)
    }

    override fun secondaryMenuThreeMin() {
        anim(
            binding.secondaryMenuThree,
            binding.secondaryMenuTwo,
            binding.secondaryMenuOne,
            R.drawable.ic_secondary_menu_three_border,
            R.drawable.ic_secondary_menu_two,
            R.drawable.ic_secondary_menu_one
        )
        secondaryMenuViwModel.setTimerTime(3)
    }

    override fun secondaryMenuContinue() {
        with(secondaryMenuViwModel) {
            if (getTimerTime() != 0) {
                setCheckExample("")
                setScore(0)
                setSecondRemaining(0L)
                binding.secondaryMenuContinue.setBackgroundResource(R.drawable.ic_secondary_menu_continue_border)
                findNavController().navigate(R.id.action_secondaryMenuFragment_to_mainTwoFragment)
            }
        }
    }

    override fun secondaryMenuBack() {
        secondaryMenuViwModel.setTimerTime(0)
        contextActivity.onBackPressed()
    }
}