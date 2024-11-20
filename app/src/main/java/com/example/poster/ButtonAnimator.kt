package com.example.poster

import android.animation.ObjectAnimator
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat


fun animateButton(button: View) {
    // אנימציה של שינוי גודל
    val scaleX = ObjectAnimator.ofFloat(button, "scaleX", 1f, 0.95f, 1f)
    val scaleY = ObjectAnimator.ofFloat(button, "scaleY", 1f, 0.95f, 1f)

    scaleX.duration = 150
    scaleY.duration = 150

    scaleX.start()
    scaleY.start()

    // שמירה על הרקע המקורי
    val originalBackground = button.background

    // בדיקה אם הרקע הוא GradientDrawable (כדי לשנות צבע עם פינות מעוגלות)
    if (originalBackground is GradientDrawable) {
        val drawable = originalBackground.mutate() as GradientDrawable
        drawable.setColor(ContextCompat.getColor(button.context, R.color.pink)) // שינוי צבע זמני

        // מחזיר את הצבע המקורי אחרי 200ms
        button.postDelayed({
            drawable.setColor(ContextCompat.getColor(button.context, R.color.purple_700))
        }, 200)
    }
}
