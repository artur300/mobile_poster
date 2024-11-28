package com.example.poster

import android.widget.Button


fun updateConfirmButtonState(confirmPurchaseButton: Button, cartList: List<Ticket>) {
    if (cartList.isEmpty()) {
        confirmPurchaseButton.isEnabled = false
        confirmPurchaseButton.alpha = 0.5f
    } else {
        confirmPurchaseButton.isEnabled = true
        confirmPurchaseButton.alpha = 1f
    }
}
