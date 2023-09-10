package com.example.viewshomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
class MainActivity : AppCompatActivity() {

    private lateinit var clockView: ClockView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clockView = findViewById(R.id.clockView)

        val calendar = Calendar.getInstance()

        // Запуск таймера для обновления времени каждую секунду
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    calendar.timeInMillis = System.currentTimeMillis()
                    clockView.setCurrentTime(calendar)
                }
            }
        }, 0, 1000)
    }
}