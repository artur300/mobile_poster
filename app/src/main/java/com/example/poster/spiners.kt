package com.example.poster

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner

fun setupCinemaSpinner(context: Context, spinner: Spinner) {
    val cinemaAdapter = ArrayAdapter.createFromResource(
        context,
        R.array.cinemas_array,
        R.layout.spinner_design
    )
    cinemaAdapter.setDropDownViewResource(R.layout.spinner_item_design)
    spinner.adapter = cinemaAdapter
}

fun setupTimeSpinner(context: Context, spinner: Spinner) {
    val timeAdapter = ArrayAdapter.createFromResource(
        context,
        R.array.screening_times_array,
        R.layout.spinner_design
    )
    timeAdapter.setDropDownViewResource(R.layout.spinner_item_design)
    spinner.adapter = timeAdapter
}


