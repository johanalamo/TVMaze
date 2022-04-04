package com.example.tvmaze.ui.composables

import android.widget.Toast
import com.example.tvmaze.models.AppContainer

fun message(appContainer: AppContainer, message: String) {
    Toast.makeText(appContainer.applicationContext, message, Toast.LENGTH_SHORT).show()
}