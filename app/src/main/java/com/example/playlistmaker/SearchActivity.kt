package com.example.playlistmaker

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // Find the back button by ID
        val buttonSearchBack = findViewById<Button>(R.id.button_search_back)

        // Set a click listener to navigate back to MainActivity
        buttonSearchBack.setOnClickListener {
            finish() // Closes the current activity and returns to the previous one
        }
    }
}
