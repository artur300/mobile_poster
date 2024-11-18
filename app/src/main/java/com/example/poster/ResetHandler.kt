package com.example.poster

import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

fun resetSelections(
    cinemaSpinner: Spinner,
    timeSpinner: Spinner,
    adultCheckBox: CheckBox,
    childCheckBox: CheckBox,
    ticketSeekBar: SeekBar,
    ticketCountTextView: TextView,
    cartList: ArrayList<Ticket>
) {
    // איפוס הבחירה של הספינרים
    cinemaSpinner.setSelection(0)
    timeSpinner.setSelection(0)

    // איפוס ה-CheckBox
    adultCheckBox.isChecked = false
    childCheckBox.isChecked = false

    // איפוס ה-SeekBar
    ticketSeekBar.progress = 0
    ticketCountTextView.text = "Number of Tickets: 0"

    // איפוס רשימת העגלה
    cartList.clear()

    // הודעה למשתמש על האיפוס
    Toast.makeText(cinemaSpinner.context, "Selections have been reset.", Toast.LENGTH_SHORT).show()
}


