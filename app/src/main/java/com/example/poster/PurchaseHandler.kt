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
        val message = context.getString(R.string.select_ticket_type_message)
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        return
    }


    val ticketType = if (isAdult) {
        context.getString(R.string.adult)
    } else {
        context.getString(R.string.child)
    }
    val ticketPrice = if (isAdult) 20 else 10
    val numberOfTickets = ticketSeekBar.progress

    if (numberOfTickets == 0) {
        val message = context.getString(R.string.select_at_least_one_ticket_message)
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        return
    }

    val totalPrice = ticketPrice * numberOfTickets
    val cinema = cinemaSpinner.selectedItem.toString()
    val time = timeSpinner.selectedItem.toString()
    val newTicket = Ticket(cinema, time, ticketType, numberOfTickets, totalPrice)

    cartList.add(newTicket)

    val message = context.getString(
        R.string.tickets_added_message,
        numberOfTickets, // %1$d
        ticketType,      // %2$s
        totalPrice       // %3$d
    )
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

}

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
        Toast.makeText(context, context.getString(R.string.cart_empty_message), Toast.LENGTH_LONG).show()
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
    cinemaNameText.text = context.getString(R.string.cinema_none_confirm, cinemaName)
    dateText.text = context.getString(R.string.screening_time_none_confirm, screeningTime)
    numberOfTicketsText.text = context.getString(R.string.number_of_tickets_0_confirm, totalTickets)
    totalCostText.text = context.getString(R.string.total_cost_0_confirm, totalPrice)

    closeButton.text = context.getString(R.string.close_confirm)
    closeButton.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}

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
        Toast.makeText(context, context.getString(R.string.cart_empty_message), Toast.LENGTH_LONG).show()
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
    cinemaNameText.text = context.getString(R.string.cinema_none_confirm, cinemaName)
    dateText.text = context.getString(R.string.screening_time_none_confirm, screeningTime)
    numberOfTicketsText.text = context.getString(R.string.number_of_tickets_0_confirm, totalTickets)
    totalCostText.text = context.getString(R.string.total_cost_0_confirm, totalPrice)
    orderStatusText.text = context.getString(R.string.order_status_placed_confirm)

    closeButton.text = context.getString(R.string.close_confirm)
    closeButton.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}
