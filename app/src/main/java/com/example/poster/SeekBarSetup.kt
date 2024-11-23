package com.example.poster
import android.content.Context

import android.widget.SeekBar
import android.widget.TextView

fun setupSeekBar(
    ticketSeekBar: SeekBar,
    ticketCountTextView: TextView,
    context: Context
) {
    ticketSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            val text = context.getString(R.string.number_of_tickets_format, progress)
            ticketCountTextView.text = text
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    })
}