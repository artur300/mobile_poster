package com.example.poster

import android.widget.CheckBox

fun setupCheckBoxListeners(adultCheckBox: CheckBox, childCheckBox: CheckBox) {
    adultCheckBox.setOnCheckedChangeListener { _, isChecked ->
        if (isChecked) {
            childCheckBox.isChecked = false
        }
    }

    childCheckBox.setOnCheckedChangeListener { _, isChecked ->
        if (isChecked) {
            adultCheckBox.isChecked = false
        }
    }
}
