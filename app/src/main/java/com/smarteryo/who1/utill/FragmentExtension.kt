package com.smarteryo.computerone.utill

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun <T> Fragment.setNavigationResult(key: String, value: T) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(
        key, value
    )
}

fun Fragment.getNavigationResult(key: String, action: () -> Unit) {
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(key)
        ?.observe(viewLifecycleOwner) { flag ->
            if (flag) action.invoke()
        }
}