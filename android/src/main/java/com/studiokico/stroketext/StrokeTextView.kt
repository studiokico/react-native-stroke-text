package com.studiokico.stroketext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import kotlin.math.ceil
import android.os.Build

class StrokeTextView(context: Context) : View(context) {
    var text: String = ""
        set(value) { if (field != value) { field = value; requestLayout(); invalidate() } }

    var fontSize: Float = 14f
        set(value) { if (field != value) { field = value; requestLayout(); invalidate() } }

    var textColor: Int = 0xFF000000.toInt()
        set(value) { if (field != value) { field = value; invalidate() } }

    var strokeColor: Int = 0xFFFFFFFF.toInt()
        set(value) { if (field != value) { field = value; invalidate() } }

    var strokeWidth: Float = 0f
        set(value) { if (field != value) { field = value; requestLayout(); invalidate() } }

    var fontFamily: String = ""
        set(value) {
            if (field != value) {
                field = value
                cachedTypeface = FontUtil.getFont(context, value)
                requestLayout()
                invalidate()
            }
        }

    var align: String = "left"
        set(value) { if (field != value) { field = value; requestLayout(); invalidate() } }

    var numberOfLines: Int = 0
        set(value) { if (field != value) { field = value; requestLayout(); invalidate() } }

    var ellipsis: Boolean = false
        set(value) { if (field != value) { field = value; requestLayout(); invalidate() } }

    var customWidth: Float = 0f
        set(value) { if (field != value) { field = value; requestLayout(); invalidate() } }


    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private var cachedTypeface: Typeface? = null
    private var textLayout: StaticLayout? = null // 여기가 핵심: 텍스트 레이아웃 엔진

    private fun createLayout(width: Int): StaticLayout? {
        if (text.isEmpty()) return null

        val validWidth = if (width > 0) width else resources.displayMetrics.widthPixels

        val scaledFontSize = getScaledSize(fontSize)
        textPaint.textSize = scaledFontSize
        textPaint.typeface = cachedTypeface ?: Typeface.DEFAULT

        val alignment = when (align) {
            "center" -> Layout.Alignment.ALIGN_CENTER
            "right" -> Layout.Alignment.ALIGN_OPPOSITE
            else -> Layout.Alignment.ALIGN_NORMAL
        }

        val builder = StaticLayout.Builder.obtain(text, 0, text.length, textPaint, validWidth)
            .setAlignment(alignment)
            .setLineSpacing(0f, 1f)
            .setIncludePad(true)

        if (numberOfLines > 0) {
            builder.setMaxLines(numberOfLines)
            if (ellipsis) {
                builder.setEllipsize(TextUtils.TruncateAt.END)
            }
        }

        return builder.build()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val availableWidth = if (customWidth > 0) {
             getScaledSize(customWidth).toInt()
        } else if (widthSize > 0) {
             widthSize
        } else {
             resources.displayMetrics.widthPixels
        }

        val scaledStrokeWidth = getScaledSize(strokeWidth)
        val contentWidth = (availableWidth - paddingLeft - paddingRight - (scaledStrokeWidth * 2)).toInt()

        if (textLayout == null || textLayout?.width != contentWidth) {
            textLayout = createLayout(contentWidth.coerceAtLeast(0))
        }

        val layout = textLayout

        var finalWidth = 0
        var finalHeight = 0

        if (layout != null) {
            finalHeight = (layout.height + paddingTop + paddingBottom + (scaledStrokeWidth * 2)).toInt()

            if (widthMode == MeasureSpec.EXACTLY) {
                finalWidth = widthSize
            } else {
                var maxLineWidth = 0f
                for (i in 0 until layout.lineCount) {
                    maxLineWidth = maxOf(maxLineWidth, layout.getLineWidth(i))
                }
                finalWidth = (ceil(maxLineWidth) + paddingLeft + paddingRight + (scaledStrokeWidth * 2)).toInt()

                if (widthMode == MeasureSpec.AT_MOST && widthSize > 0) {
                    finalWidth = minOf(finalWidth, widthSize)
                }
            }
        }

        setMeasuredDimension(finalWidth, finalHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layout = textLayout ?: return
        val scaledStrokeWidth = getScaledSize(strokeWidth)

        canvas.save()

        val translateX = paddingLeft + scaledStrokeWidth
        val translateY = paddingTop + scaledStrokeWidth
        canvas.translate(translateX, translateY)

        if (strokeWidth > 0) {
            textPaint.style = Paint.Style.STROKE
            textPaint.strokeWidth = scaledStrokeWidth
            textPaint.color = strokeColor
            layout.draw(canvas)
        }

        textPaint.style = Paint.Style.FILL
        textPaint.color = textColor
        layout.draw(canvas)

        canvas.restore()
    }

    private fun getScaledSize(size: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            size,
            resources.displayMetrics
        )
    }
}
