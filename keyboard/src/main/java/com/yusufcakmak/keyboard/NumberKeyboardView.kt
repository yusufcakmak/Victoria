package com.yusufcakmak.keyboard

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.forEach
import androidx.core.widget.ImageViewCompat
import androidx.core.widget.TextViewCompat
import android.graphics.PorterDuffColorFilter


class NumberKeyboardView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RelativeLayout(context, attrs, defStyleAttr) {

    private val numberButtonList: MutableList<Button> = ArrayList(SUM_KEYS)
    private val dividerList: MutableList<View> = ArrayList(4)
    private var listener: KeyboardListener? = null
    private var btnDelete: RelativeLayout? = null
    private var numberText = ""

    interface KeyboardListener {
        fun onNumberChanged(str: String)
    }

    init {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.CustomKeyBoardStyle, defStyleAttr, defStyleAttr)

        val btnColor =
            typedArray.getColor(R.styleable.CustomKeyBoardStyle_keyNumberTextColor, Color.GRAY)

        val dividerBackgroundColor =
            typedArray.getColor(R.styleable.CustomKeyBoardStyle_keyNumberDividerColor, Color.GRAY)

        typedArray.recycle()
        inflateViews(dividerBackgroundColor)
        setTextColorForButtons(btnColor)
    }

    private fun inflateViews(dividerColor: Int) {
        val container = View.inflate(context, R.layout.keyboard_layout, this)
        btnDelete = container.findViewById(R.id.rlytDelete)

        dividerList.apply {
            add(container.findViewById(R.id.divider0))
            add(container.findViewById(R.id.divider1))
            add(container.findViewById(R.id.divider2))
            add(container.findViewById(R.id.divider3))
        }

        dividerList.forEach {
            it.setBackgroundColor(dividerColor)
        }

        numberButtonList.apply {
            add(container.findViewById(R.id.btn0))
            add(container.findViewById(R.id.btn1))
            add(container.findViewById(R.id.btn2))
            add(container.findViewById(R.id.btn3))
            add(container.findViewById(R.id.btn4))
            add(container.findViewById(R.id.btn5))
            add(container.findViewById(R.id.btn6))
            add(container.findViewById(R.id.btn7))
            add(container.findViewById(R.id.btn8))
            add(container.findViewById(R.id.btn9))
        }


        prepareListeners()
    }

    private fun setTextColorForButtons(btnColor: Int) {
        numberButtonList.forEach {
            it.setTextColor(btnColor)
        }

    }

    private fun prepareListeners() {
        numberButtonList.forEach { btn ->
            val selectedNumber = btn.text.toString()
            btn.setOnClickListener {
                listener?.onNumberChanged(addNumberToText(selectedNumber.toInt()))
            }
        }

        btnDelete?.setOnClickListener {
            listener?.onNumberChanged(dropLastNumber())
        }


        btnDelete?.setOnLongClickListener {
            cleanNumberText()
            true
        }
    }

    fun setKeyboardListener(listener: KeyboardListener) {
        this.listener = listener
    }

    private fun addNumberToText(num: Int): String {
        numberText += num
        return numberText
    }

    private fun dropLastNumber(): String {
        numberText = if (numberText.length == 1) {
            ""
        } else {
            numberText.dropLast(1)
        }

        return numberText
    }

    private fun cleanNumberText() {
        numberText = ""
        listener?.onNumberChanged(numberText)
    }

    companion object {
        const val SUM_KEYS = 10
    }
}