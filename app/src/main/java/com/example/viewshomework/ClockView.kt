package com.example.viewshomework

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

class ClockView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val calendar = Calendar.getInstance()

    init {
        paint.color = Color.BLACK
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (Math.min(centerX, centerY) - 10).toFloat()

        // Рисуем циферблат
        canvas.drawCircle(centerX, centerY, radius, paint)

        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)

        // Рисуем часовую стрелку
        drawHand(canvas, centerX, centerY, radius * 0.5f, (hour % 12 + minute / 60f) * 360 / 12)

        // Рисуем минутную стрелку
        drawHand(canvas, centerX, centerY, radius * 0.7f, (minute * 360 / 60).toFloat())

        // Рисуем секундную стрелку
        drawHand(canvas, centerX, centerY, radius * 0.9f, (second * 360 / 60).toFloat())
    }

    private fun drawHand(canvas: Canvas, centerX: Float, centerY: Float, length: Float, angle: Float) {
        val radians = Math.toRadians(angle.toDouble())
        val startX = centerX
        val startY = centerY
        val endX = centerX + length * Math.cos(radians).toFloat()
        val endY = centerY + length * Math.sin(radians).toFloat()
        canvas.drawLine(startX, startY, endX, endY, paint)
    }

    fun setCurrentTime(calendar: Calendar) {
        this.calendar.timeInMillis = calendar.timeInMillis
        invalidate()
    }
}