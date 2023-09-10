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
        paint.textSize = 30f
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

// Вычисляем угол для часовой стрелки
        val hourAngle = ((hour % 12 + minute / 60f) * 360 / 12) - 90

// Вычисляем угол для минутной стрелки
        val minuteAngle = ((minute + second / 60f) * 360 / 60) - 90



// Рисуем часовую стрелку
        drawHand(canvas, centerX, centerY, radius * 0.4f, hourAngle)

// Рисуем минутную стрелку
        drawHand(canvas, centerX, centerY, radius * 0.6f, minuteAngle)

// Рисуем секундную стрелку
        drawHand(canvas, centerX, centerY, radius * 0.8f, (second * 360 / 60).toFloat())

        // Рисуем цифры внутри циферблата
        val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint.color = Color.BLACK
        textPaint.textSize = 50f // Увеличиваем размер шрифта для цифр

        for (i in 1..12) {
            val angle = Math.PI / 6 * (i - 3) // Вычисляем угол для каждой цифры
            val x = centerX + (radius * 0.85f) * Math.cos(angle).toFloat()
            val y = centerY + (radius * 0.85f) * Math.sin(angle).toFloat()

            // Рисуем цифру внутри циферблата
            val text = i.toString()
            val textWidth = textPaint.measureText(text)
            canvas.drawText(text, x - textWidth / 2, y + textPaint.textSize / 2, textPaint)
        }
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