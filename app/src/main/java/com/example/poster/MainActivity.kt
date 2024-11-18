package com.example.poster

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.SeekBar
import android.widget.CheckBox
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.ImageView
import android.animation.AnimatorSet
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private var isOrderPlaced = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_poster_view)


        val cinemaSpinner: Spinner = findViewById(R.id.cinemaSpinner)
        val timeSpinner: Spinner = findViewById(R.id.timeSpinner)
        val ticketSeekBar: SeekBar = findViewById(R.id.ticketSeekBar)
        val ticketCountTextView: TextView = findViewById(R.id.ticketCountTextView)
        val adultCheckBox: CheckBox = findViewById(R.id.adultCheckBox)
        val childCheckBox: CheckBox = findViewById(R.id.childCheckBox)
        val addToCartButton: Button = findViewById(R.id.addToCartButton)
        val viewSummaryButton: Button = findViewById(R.id.viewSummaryButton)
        val resetButton: Button = findViewById(R.id.resetButton)
        val confirmPurchaseButton: Button = findViewById(R.id.confirm_purchase)
        val posterImageView: ImageView = findViewById(R.id.Poster)
        val cartList = ArrayList<Ticket>()


        // הגדרות רכיבים
        setupCinemaSpinner(this, cinemaSpinner)
        setupTimeSpinner(this, timeSpinner)
        setupCheckBoxListeners(adultCheckBox, childCheckBox)
        setupSeekBar(ticketSeekBar, ticketCountTextView)
        // מאזין לכפתור "Add to Cart"
        addToCartButton.setOnClickListener {
            addToCart(
                context = this,
                cinemaSpinner = cinemaSpinner,
                timeSpinner = timeSpinner,
                adultCheckBox = adultCheckBox,
                childCheckBox = childCheckBox,
                ticketSeekBar = ticketSeekBar,
                cartList = cartList
            )
        }

        // אנימציה לפוסטר - סיבוב ושקיפות
        posterImageView.setOnClickListener {
            animatePoster(posterImageView)
        }

        viewSummaryButton.setOnClickListener {
            showCartSummaryDialog(this, cartList)
        }


    }
}
