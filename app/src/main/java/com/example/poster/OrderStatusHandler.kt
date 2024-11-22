package com.example.poster

import android.widget.RadioButton
import android.widget.Toast
import android.content.Context

// פונקציה לעדכון כפתור הרדיו
fun updateOrderStatus(orderStatusButton: RadioButton, isOrderPlaced: Boolean, context: Context) {
    orderStatusButton.isChecked = isOrderPlaced // עדכון מצב הכפתור
    if (isOrderPlaced) {
        orderStatusButton.text = context.getString(R.string.order_placed) // שימוש בטקסט
    } else {
        orderStatusButton.text = context.getString(R.string.order_not_placed) // שימוש בטקסט
    }
}


// פונקציה לטיפול בלחיצה על Confirm Purchase
fun handleConfirmPurchase(
    context: Context,
    orderStatusButton: RadioButton
): Boolean {
    Toast.makeText(context, context.getString(R.string.purchase_confirmed), Toast.LENGTH_SHORT).show()
    updateOrderStatus(orderStatusButton, true, context) // עדכון כפתור הרדיו
    return true // מחזיר שההזמנה בוצעה
}
