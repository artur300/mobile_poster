package com.example.poster

import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import android.widget.RadioButton

// משתנה גלובלי לציון אם ההזמנה הושלמה או לא
var isOrderPlaced = false

// פונקציה לאיפוס הבחירות
fun resetSelections(
    cinemaSpinner: Spinner,
    timeSpinner: Spinner,
    adultCheckBox: CheckBox,
    childCheckBox: CheckBox,
    ticketSeekBar: SeekBar,
    ticketCountTextView: TextView,
    statusText: TextView,
    cartList: ArrayList<Ticket>,
    orderStatusButton: RadioButton

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

    // איפוס סטטוס ההזמנה
    isOrderPlaced = false
    statusText.text = "Order Status: Not Placed"
    statusText.setTextColor(ContextCompat.getColor(cinemaSpinner.context, R.color.black))
    updateOrderStatus(orderStatusButton, isOrderPlaced)
    // הודעה למשתמש
    Toast.makeText(cinemaSpinner.context, "Selections have been reset.", Toast.LENGTH_SHORT).show()


}
