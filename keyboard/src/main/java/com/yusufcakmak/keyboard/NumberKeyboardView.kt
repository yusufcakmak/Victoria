package com.yusufcakmak.keyboard

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout

class NumberKeyboardView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RelativeLayout(context, attrs, defStyleAttr) {

    interface KeyboardListener {
        fun onNumberChanged(str: String)
    }

    private lateinit var numberButtonList: MutableList<Button>
    private var listener: KeyboardListener? = null
    private var btnDelete: RelativeLayout? = null
    private var inputText = ""


    init {
        inflateViews()
    }

    private fun inflateViews() {
        val container = View.inflate(context, R.layout.keyboard_layout, this)

        btnDelete = container.findViewById(R.id.rlytDelete)

        numberButtonList = ArrayList(SUM_KEYS)

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

        for (i in numberButtonList.indices) {
            val btn = numberButtonList[i]
            btn.setOnClickListener {
                listener?.onNumberChanged(addToText(i))
            }
        }

        btnDelete?.setOnClickListener {
            listener?.onNumberChanged(dropLast())
        }


        btnDelete?.setOnLongClickListener {
            cleanText()
            true
        }
    }

    fun setKeyboardListener(listener: KeyboardListener) {
        this.listener = listener
    }

    private fun addToText(num: Int): String {
        inputText += num
        return inputText
    }

    private fun dropLast(): String {
        inputText = if (inputText.length == 1) {
            ""
        } else {
            inputText.dropLast(1)
        }

        return inputText
    }

    private fun cleanText() {
        inputText = ""
        listener?.onNumberChanged(inputText)
    }

    companion object {
        const val SUM_KEYS = 10
    }
}