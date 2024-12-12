package com.example.playlistmaker

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var mainContainer: View
    private lateinit var mainTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)

        // Find the main container and text view
        mainContainer = findViewById(R.id.main)
        mainTextView = findViewById(R.id.text_main)

        // Adjust window insets
        ViewCompat.setOnApplyWindowInsetsListener(mainContainer) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find buttons by ID
        val buttonSearch = findViewById<Button>(R.id.button_search)
        val buttonMedia = findViewById<Button>(R.id.button_media)
        val buttonSettings = findViewById<Button>(R.id.button_settings)

        // Set click listeners to navigate to respective activities
        buttonSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        buttonMedia.setOnClickListener {
            val intent = Intent(this, MediaActivity::class.java)
            startActivity(intent)
        }

        buttonSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Apply the theme state when the activity is resumed
        val isDarkMode = sharedPreferences.getBoolean("DARK_MODE", false)
        applyMainActivityTheme(isDarkMode)
    }

    private fun applyMainActivityTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            mainContainer.setBackgroundColor(resources.getColor(R.color.darkBackground, theme))
            mainTextView.setTextColor(resources.getColor(R.color.white, theme))
        } else {
            mainContainer.setBackgroundColor(resources.getColor(R.color.primaryBackground, theme))
            mainTextView.setTextColor(resources.getColor(R.color.black, theme))
        }
    }
}
