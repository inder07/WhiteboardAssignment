package com.android.whiteboardassignment.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.android.whiteboardassignment.models.Stroke

class WhiteboardView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint().apply {
        isAntiAlias = true
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    private val currentPath = Path()
    private val strokes = mutableListOf<Stroke>()

    var currentColor = Color.BLACK
    var strokeWidth = 15f
    var isEraser = false

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath.reset()
                currentPath.moveTo(x, y)
                strokes.add(Stroke(mutableListOf(x to y), currentColor, strokeWidth))
            }
            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(x, y)
                strokes.last().points.add(x to y)
            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (stroke in strokes) {
            paint.color = stroke.color
            paint.strokeWidth = stroke.width
            paint.style = Paint.Style.STROKE
            canvas.drawPath(pathFromPoints(stroke.points), paint)
        }
    }

    private fun pathFromPoints(points: List<Pair<Float, Float>>): Path {
        val path = Path()
        if (points.isNotEmpty()) {
            path.moveTo(points[0].first, points[0].second)
            for (i in 1 until points.size) {
                path.lineTo(points[i].first, points[i].second)
            }
        }
        return path
    }

    fun clear() {
        strokes.clear()
        invalidate()
    }
}