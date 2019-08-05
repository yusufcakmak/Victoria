package com.yusufcakmak.numberkeyboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yusufcakmak.keyboard.NumberKeyboardView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NumberKeyboardView.KeyboardListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numView.setKeyboardListener(this)
    }

    override fun onNumberChanged(str: String) {
        et.setText(str)
    }
}
