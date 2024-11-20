package com.example.poster

import android.widget.RadioButton
import android.widget.Toast
import android.content.Context

// פונקציה לעדכון כפתור הרדיו
fun updateOrderStatus(orderStatusButton: RadioButton, isOrderPlaced: Boolean) {
    orderStatusButton.isChecked = isOrderPlaced // עדכון מצב הכפתור
    if (isOrderPlaced) {
        orderStatusButton.text = "Order Placed" // שינוי טקסט הכפתור במצב בוצע
    } else {
        orderStatusButton.text = "Order Not Placed" // שינוי טקסט הכפתור במצב לא בוצע
    }
}

// פונקציה לטיפול בלחיצה על Confirm Purchase
fun handleConfirmPurchase(
    context: Context,
    orderStatusButton: RadioButton
): Boolean {
    Toast.makeText(context, "Purchase Confirmed!", Toast.LENGTH_SHORT).show()
    updateOrderStatus(orderStatusButton, true) // עדכון כפתור הרדיו
    return true // מחזיר שההזמנה בוצעה
}
