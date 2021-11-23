package com.smarteryo.computerone.ui.pause

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.smarteryo.computerone.R
import com.smarteryo.computerone.databinding.DialogPauseBinding
import com.smarteryo.computerone.ui.pause.BaseDialog
import com.smarteryo.computerone.utill.setNavigationResult

class PauseDialogFragment : BaseDialog<DialogPauseBinding>(R.layout.dialog_pause) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)

        accessViaBinding()
    }

    private fun accessViaBinding() {
        with(binding) {
            dialogContinueBtn.setOnClickListener {
                setNavigationResult(CONTINUE_KEY, true)
                dismiss()
            }

            dialogNewGameBtn.setOnClickListener {
                setNavigationResult(NEW_GAME_KEY, true)
                dismiss()
            }
        }
    }

    companion object {
        const val CONTINUE_KEY = "continue"
        const val NEW_GAME_KEY = "new_game"
    }
}