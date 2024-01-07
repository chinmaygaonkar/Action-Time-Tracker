package com.offsyncstudios.actiontime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import com.offsyncstudios.actiontimetracker.ActionTimeTracker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var context = this
        var apiButton = this.findViewById<Button>(R.id.api_button)
        apiButton.setOnClickListener {
            var actionTimeTracker = ActionTimeTracker()
            actionTimeTracker.startTimer()
            Handler(Looper.getMainLooper()).postDelayed({
                Toast.makeText(this, "API Action performed", Toast.LENGTH_LONG).show()
                actionTimeTracker.stopTimer(context, "API Action")
            }, 15000)

        }

        var dbButton = this.findViewById<Button>(R.id.db_button)
        dbButton.setOnClickListener {
            var actionTimeTracker = ActionTimeTracker()
            actionTimeTracker.startTimer()
            Handler(Looper.getMainLooper()).postDelayed({
                Toast.makeText(applicationContext, "Database Action performed", Toast.LENGTH_LONG).show()
                actionTimeTracker.stopTimer(this, "Database Action")
            }, 5000)
        }

    }
}