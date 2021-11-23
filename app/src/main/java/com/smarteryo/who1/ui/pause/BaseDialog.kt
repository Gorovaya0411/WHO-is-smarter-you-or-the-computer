package com.smarteryo.computerone.ui.pause

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.smarteryo.computerone.utill.autoCleared

abstract class BaseDialog<VB : ViewDataBinding>(
    @LayoutRes val layoutId: Int
) : DialogFragment() {

    protected var binding by autoCleared<VB>()

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<VB>(
            inflater,
            layoutId,
            container,
            false
        ).also { viewBinding ->
            this.binding = viewBinding
        }.root
    }
}