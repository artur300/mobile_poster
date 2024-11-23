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
import android.widget.RadioButton
import com.example.poster.animateButton


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
        val icon: ImageView = findViewById(R.id.icon)




        // הפעלת האנימציה האינסופית
        startSizeAnimation(icon)

        // שינוי צבע זמני בעת לחיצה
        icon.setOnClickListener {
            handleIconClick(icon)
            animatePoster(posterImageView)
        }


        // מציאת הכפתורים
        val confirmPurchaseButton: Button = findViewById(R.id.confirm_purchase)
        val orderStatusButton: RadioButton = findViewById(R.id.orderStatusButton)

        // מצב התחלתי: אדום
        // מצב התחלתי: אדום
        updateOrderStatus(orderStatusButton, isOrderPlaced, this)

// מאזין ללחיצה על "Confirm Purchase"
        confirmPurchaseButton.setOnClickListener {
            animateButton(confirmPurchaseButton)
            isOrderPlaced = handleConfirmPurchase(this, orderStatusButton) // משנה את המצב ל-true
            updateOrderStatus(orderStatusButton, isOrderPlaced, this) // מעדכן את מצב הכפתור
            showConfirmPurchaseSummaryDialog(this, cartList)
            getTicketsButton.isEnabled = false
            getTicketsButton.alpha = 0.5f // מוריד את השקיפות כדי לסמן שהוא נעול
        }



        // הגדרות רכיבים
        setupCinemaSpinner(this, cinemaSpinner)
        setupTimeSpinner(this, timeSpinner)
        setupCheckBoxListeners(adultCheckBox, childCheckBox)
        setupSeekBar(ticketSeekBar, ticketCountTextView, this)

        // מאזין לכפתור "Get Tickets"
        getTicketsButton.setOnClickListener {

            animateButton(getTicketsButton)
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

            ticketSeekBar.progress = 0
            ticketCountTextView.text = "Number of Tickets: 0"

            // איפוס CheckBoxes
            adultCheckBox.isChecked = false
            childCheckBox.isChecked = false


            // מאזין לכפתור "Reset"
            resetButton.setOnClickListener {
                animateButton(resetButton)
                // מציאת ה-TextView מתוך ה-Dialog
                val dialogView = layoutInflater.inflate(R.layout.dialog_purchase_summary, null)
                val statusText: TextView = dialogView.findViewById(R.id.statusText)

                // קריאה לפונקציה resetSelections
                resetSelections(
                    context = this,
                    cinemaSpinner = cinemaSpinner,
                    timeSpinner = timeSpinner,
                    adultCheckBox = adultCheckBox,
                    childCheckBox = childCheckBox,
                    ticketSeekBar = ticketSeekBar,
                    ticketCountTextView = ticketCountTextView,
                    statusText = statusText,
                    cartList = cartList,
                    orderStatusButton = orderStatusButton
                )


                getTicketsButton.isEnabled = true
                getTicketsButton.alpha = 1f
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
