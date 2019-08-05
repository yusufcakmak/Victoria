package com.yusufcakmak.keyboard

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.core.view.forEach

class NumberKeyboardView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RelativeLayout(context, attrs, defStyleAttr) {

    interface KeyboardListener {
        fun onNumberChanged(str: String)
    }

    private val numberButtonList: MutableList<Button> = ArrayList(SUM_KEYS)
    private var listener: KeyboardListener? = null
    private var btnDelete: RelativeLayout? = null
    private var numberText = ""


    init {
        inflateViews()
    }

    private fun inflateViews() {
        val container = View.inflate(context, R.layout.keyboard_layout, this)

        btnDelete = container.findViewById(R.id.rlytDelete)

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