package com.example.poster

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.SeekBar
import android.widget.CheckBox
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import android.widget.*



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
        val getTicketsButton: Button = findViewById(R.id.getTicketsButton)
        val resetButton: Button = findViewById(R.id.resetButton)
        val posterImageView: ImageView = findViewById(R.id.Poster)
        val cartList = ArrayList<Ticket>()
        val orderStatusButton: RadioButton = findViewById(R.id.orderStatusButton)


        // הגדרות רכיבים
        setupCinemaSpinner(this, cinemaSpinner)
        setupTimeSpinner(this, timeSpinner)
        setupCheckBoxListeners(adultCheckBox, childCheckBox)
        setupSeekBar(ticketSeekBar, ticketCountTextView)

        // מאזין לכפתור "Get Tickets"
        getTicketsButton.setOnClickListener {
            // קודם כל מוסיפים לעגלה
            addToCart(
                context = this,
                cinemaSpinner = cinemaSpinner,
                timeSpinner = timeSpinner,
                adultCheckBox = adultCheckBox,
                childCheckBox = childCheckBox,
                ticketSeekBar = ticketSeekBar,
                cartList = cartList
            )


            // מאזין לכפתור "Reset"
            resetButton.setOnClickListener {
                // מציאת ה-TextView מתוך ה-Dialog
                val dialogView = layoutInflater.inflate(R.layout.dialog_purchase_summary, null)
                val statusText: TextView = dialogView.findViewById(R.id.statusText)

                // קריאה לפונקציה resetSelections
                resetSelections(
                    cinemaSpinner = cinemaSpinner,
                    timeSpinner = timeSpinner,
                    adultCheckBox = adultCheckBox,
                    childCheckBox = childCheckBox,
                    ticketSeekBar = ticketSeekBar,
                    ticketCountTextView = ticketCountTextView,
                    statusText = statusText,
                    cartList = cartList
                )
            }

            // לאחר מכן מציגים את סיכום הרכישה
            showCartSummaryDialog(this, cartList)
        }

        // אנימציה לפוסטר - סיבוב ושקיפות
        posterImageView.setOnClickListener {
            animatePoster(posterImageView)
        }
    }
}
