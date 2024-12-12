package com.example.playlistmaker

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var settingsSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)

        // Find the back button, switch, and root container
        val buttonSettingsBack = findViewById<Button>(R.id.button_settings_back)
        settingsSwitch = findViewById(R.id.settings_switch)
        val container: View = findViewById(R.id.settings_main)

        // Find all the TextViews
        val textViews: List<TextView> = listOf(
            findViewById(R.id.text_settings),
            findViewById(R.id.text_darkmode),
            findViewById(R.id.text_share),
            findViewById(R.id.text_support),
            findViewById(R.id.text_eua)
        )

        // Restore switch state from SharedPreferences
        val isDarkMode = sharedPreferences.getBoolean("DARK_MODE", false)
        settingsSwitch.isChecked = isDarkMode
        applyTheme(isDarkMode, textViews, container, buttonSettingsBack)

        // Set click listener for the back button
        buttonSettingsBack.setOnClickListener {
            finish() // Close SettingsActivity and return to MainActivity
        }

        // Set listener for the switch to toggle theme
        settingsSwitch.setOnCheckedChangeListener { _, isChecked ->
            applyTheme(isChecked, textViews, container, buttonSettingsBack)
            saveThemeState(isChecked)
        }
    }

    private fun applyTheme(
        isDarkMode: Boolean,
        textViews: List<TextView>,
        container: View,
        button: Button
    ) {
        if (isDarkMode) {
            // Dark mode: update text and background colors
            container.setBackgroundColor(resources.getColor(R.color.darkBackground, theme))
            textViews.forEach { it.setTextColor(resources.getColor(R.color.white, theme)) }
            button.setBackgroundColor(resources.getColor(R.color.darkBackground, theme))
            button.setTextColor(resources.getColor(R.color.white, theme))
            button.compoundDrawableTintList = getColorStateList(R.color.white)
        } else {
            // Light mode: update text and background colors
            container.setBackgroundColor(resources.getColor(R.color.white, theme))
            textViews.forEach { it.setTextColor(resources.getColor(R.color.black, theme)) }
            button.setBackgroundColor(resources.getColor(R.color.white, theme))
            button.setTextColor(resources.getColor(R.color.black, theme))
            button.compoundDrawableTintList = getColorStateList(R.color.black)
        }
    }

    private fun saveThemeState(isDarkMode: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("DARK_MODE", isDarkMode)
        editor.apply()
    }
}
