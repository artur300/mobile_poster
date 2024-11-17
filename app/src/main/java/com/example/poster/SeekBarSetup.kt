package com.example.poster

import android.widget.SeekBar
import android.widget.TextView

fun setupSeekBar(
    ticketSeekBar: SeekBar,
    ticketCountTextView: TextView
) {
    var numberOfTickets = 0
    ticketSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            // מעדכנים את מספר הכרטיסים ומציגים אותו בטקסט
            numberOfTickets = progress
            ticketCountTextView.text = "Number of Tickets: $numberOfTickets"
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    })
}
