package com.example.poster

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.PorterDuff
import android.widget.ImageView
import androidx.core.content.ContextCompat

// אנימציה של גדילה והקטנה (לופ אינסופי)
fun startSizeAnimation(icon: ImageView) {
    val scaleX = ObjectAnimator.ofFloat(icon, "scaleX", 1f, 1.2f, 1f)
    val scaleY = ObjectAnimator.ofFloat(icon, "scaleY", 1f, 1.2f, 1f)
    scaleX.repeatCount = ObjectAnimator.INFINITE
    scaleY.repeatCount = ObjectAnimator.INFINITE
    scaleX.duration = 1000
    scaleY.duration = 1000

    val animatorSet = AnimatorSet()
    animatorSet.playTogether(scaleX, scaleY)
    animatorSet.start()

    // הגדרת הצבע הכחול כצבע תמידי
    icon.setColorFilter(
        ContextCompat.getColor(icon.context, android.R.color.holo_blue_bright),
        PorterDuff.Mode.SRC_IN
    )
}

// שינוי צבע זמני לאדום בעת לחיצה
fun handleIconClick(icon: ImageView) {
    // שינוי צבע לאדום
    icon.setColorFilter(
        ContextCompat.getColor(icon.context,R.color.pink),
        PorterDuff.Mode.SRC_IN
    )

    // חזרה לצבע הכחול אחרי 200ms
    icon.postDelayed({
        icon.setColorFilter(
            ContextCompat.getColor(icon.context, android.R.color.holo_blue_bright),
            PorterDuff.Mode.SRC_IN
        )
    }, 200)
}
