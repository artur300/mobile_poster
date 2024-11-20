package com.example.poster

import android.content.Context
import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Toast
import android.app.Dialog
import android.widget.Button
import android.widget.TextView



// פונקציה להוספת הכרטיסים לעגלה
fun addToCart(
    context: Context,
    cinemaSpinner: Spinner,
    timeSpinner: Spinner,
    adultCheckBox: CheckBox,
    childCheckBox: CheckBox,
    ticketSeekBar: SeekBar,
    cartList: ArrayList<Ticket>
) {
    val isAdult = adultCheckBox.isChecked
    val isChild = childCheckBox.isChecked

    if (!isAdult && !isChild) {
        Toast.makeText(context, "Please select Adult or Child ticket", Toast.LENGTH_LONG).show()
        return
    }

    val ticketType = if (isAdult) "Adult" else "Child"
    val ticketPrice = if (isAdult) 20 else 10

    val numberOfTickets = ticketSeekBar.progress
    if (numberOfTickets == 0) {
        Toast.makeText(context, "Please select at least one ticket", Toast.LENGTH_LONG).show()
        return
    }

    val totalPrice = ticketPrice * numberOfTickets
    val cinema = cinemaSpinner.selectedItem.toString()
    val time = timeSpinner.selectedItem.toString()
    val newTicket = Ticket(cinema, time, ticketType, numberOfTickets, totalPrice)

    cartList.add(newTicket)

    Toast.makeText(context, "Added $numberOfTickets $ticketType tickets. Total: $$totalPrice", Toast.LENGTH_LONG).show()
}

// פונקציה להצגת סיכום העגלה עם זמן מהספינר
fun showCartSummaryDialog(context: Context, cartList: ArrayList<Ticket>) {
    val dialog = Dialog(context)
    dialog.setContentView(R.layout.dialog_purchase_summary)
    dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_style)
    dialog.window?.setLayout(
        android.view.WindowManager.LayoutParams.MATCH_PARENT,
        android.view.WindowManager.LayoutParams.WRAP_CONTENT
    )

    val cinemaNameText: TextView = dialog.findViewById(R.id.cinema_name)
    val dateText: TextView = dialog.findViewById(R.id.Date)
    val numberOfTicketsText: TextView = dialog.findViewById(R.id.Number_Of_Tickets)
    val totalCostText: TextView = dialog.findViewById(R.id.Total_cost)
    val closeButton: Button = dialog.findViewById(R.id.closeButton)

    if (cartList.isEmpty()) {
        Toast.makeText(context, "Your cart is empty.", Toast.LENGTH_LONG).show()
        return
    }

    // חישוב מספר הכרטיסים הכולל והמחיר הכולל
    var totalTickets = 0
    var totalPrice = 0
    val cinemaName = cartList[0].cinema
    val screeningTime = cartList[0].time

    for (ticket in cartList) {
        totalTickets += ticket.numberOfTickets
        totalPrice += ticket.totalPrice
    }

    // הצגת המידע בדיאלוג
    cinemaNameText.text = "Cinema: $cinemaName"
    dateText.text = "Screening Time: $screeningTime"
    numberOfTicketsText.text = "Number of Tickets: $totalTickets"
    totalCostText.text = "Total Cost: $$totalPrice"



    closeButton.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}


//------------------------------------------------------------

fun showConfirmPurchaseSummaryDialog(context: Context, cartList: ArrayList<Ticket>) {
    val dialog = Dialog(context)
    dialog.setContentView(R.layout.dialog_confirm_purchase_summary)
    dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_style)
    dialog.window?.setLayout(
        android.view.WindowManager.LayoutParams.MATCH_PARENT,
        android.view.WindowManager.LayoutParams.WRAP_CONTENT
    )

    val cinemaNameText: TextView = dialog.findViewById(R.id.cinema_name)
    val dateText: TextView = dialog.findViewById(R.id.Date)
    val numberOfTicketsText: TextView = dialog.findViewById(R.id.Number_Of_Tickets)
    val totalCostText: TextView = dialog.findViewById(R.id.Total_cost)
    val orderStatusText: TextView = dialog.findViewById(R.id.order_status)
    val closeButton: Button = dialog.findViewById(R.id.closeButton)

    if (cartList.isEmpty()) {
        Toast.makeText(context, "Your cart is empty.", Toast.LENGTH_LONG).show()
        return
    }

    // חישוב מספר הכרטיסים הכולל והמחיר הכולל
    var totalTickets = 0
    var totalPrice = 0
    val cinemaName = cartList[0].cinema
    val screeningTime = cartList[0].time

    for (ticket in cartList) {
        totalTickets += ticket.numberOfTickets
        totalPrice += ticket.totalPrice
    }

    // הצגת המידע בדיאלוג
    cinemaNameText.text = "Cinema: $cinemaName"
    dateText.text = "Screening Time: $screeningTime"
    numberOfTicketsText.text = "Number of Tickets: $totalTickets"
    totalCostText.text = "Total Cost: $$totalPrice"
    orderStatusText.text = "Order Status: Placed"

    closeButton.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}

