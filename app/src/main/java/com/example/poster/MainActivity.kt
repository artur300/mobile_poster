package com.example.poster

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.SeekBar
import android.widget.CheckBox
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_poster_view)

        // מציאת כל הרכיבים מה-XML
        val cinemaSpinner: Spinner = findViewById(R.id.cinemaSpinner)
        val timeSpinner: Spinner = findViewById(R.id.timeSpinner)
        val ticketSeekBar: SeekBar = findViewById(R.id.ticketSeekBar)
        val ticketCountTextView: TextView = findViewById(R.id.ticketCountTextView)
        val adultCheckBox: CheckBox = findViewById(R.id.adultCheckBox)
        val childCheckBox: CheckBox = findViewById(R.id.childCheckBox)
        val addToCartButton: Button = findViewById(R.id.addToCartButton)
        val viewSummaryButton: Button = findViewById(R.id.viewSummaryButton)
        val resetButton: Button = findViewById(R.id.resetButton)
        val cartList = ArrayList<Ticket>() // רשימת הכרטיסים בסל

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

        // מאזין לכפתור "View Cart"
        viewSummaryButton.setOnClickListener {
            showCartSummaryDialog(this, cartList)
        }

        resetButton.setOnClickListener {
            resetSelections(
                cinemaSpinner,
                timeSpinner,
                adultCheckBox,
                childCheckBox,
                ticketSeekBar,
                ticketCountTextView,
                cartList
            )
        }


    }
}
