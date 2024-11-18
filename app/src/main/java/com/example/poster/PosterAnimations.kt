package com.example.poster

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.widget.ImageView

// פונקציה לביצוע אנימציה על הפוסטר
fun animatePoster(posterImageView: ImageView) {
    // סיבוב של הפוסטר
    val rotate = ObjectAnimator.ofFloat(posterImageView, "rotationY", 0f, 360f).apply {
        duration = 1000
    }

    // שקיפות - דהייה החוצה
    val fadeOut = ObjectAnimator.ofFloat(posterImageView, "alpha", 1f, 0f).apply {
        duration = 500
    }

    // שקיפות - דהייה פנימה
    val fadeIn = ObjectAnimator.ofFloat(posterImageView, "alpha", 0f, 1f).apply {
        duration = 500
    }

    // סט אנימציות - הפעלה סדרתית
    val animatorSet = AnimatorSet()
    animatorSet.play(rotate).with(fadeOut)
    animatorSet.play(fadeIn).after(fadeOut)
    animatorSet.start()
}
