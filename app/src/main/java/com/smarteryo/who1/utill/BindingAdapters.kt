package com.smarteryo.computerone.utill

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import com.smarteryo.computerone.R
import com.smarteryo.computerone.ui.main_two.MyTextWatcher

@BindingAdapter("bindEnabledImage")
fun ImageView.bindEnabledImage(enabled: Boolean) {
    if (enabled) {
        setImageResource(R.drawable.ic_option_switch_sound_on)
    } else {
        setImageResource(R.drawable.ic_option_switch_sound_off)
    }
}

@BindingAdapter(value = ["vibrationEnabled", "soundEnabled", "listener"], requireAll = false)
fun View.bindOnClick(
    vibrationEnabled: Boolean,
    soundEnabled: Boolean,
    listener: View.OnClickListener
) {
    setOnClickListener {
        if (vibrationEnabled) {
            val vibration = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                vibration.vibrate(
                    VibrationEffect.createOneShot(
                        100L,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            else vibration.vibrate(100L)
        }
        if (soundEnabled) {
            val player = MediaPlayer.create(context, R.raw.sound)
            player.start()
        }
        listener.onClick(it)
    }
}

@BindingAdapter("onKeyListener")
fun AppCompatEditText.bindOnKeyListener(
    onKeyListener: View.OnKeyListener
) {
    setOnKeyListener(onKeyListener)
}

@BindingAdapter("textWatcher")
fun EditText.setTextWatcher(action: (entered: String) -> Unit) {
    object : MyTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            action.invoke(s.toString())
        }
    }
}