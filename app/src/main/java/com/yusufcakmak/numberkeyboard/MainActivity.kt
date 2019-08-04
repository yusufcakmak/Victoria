package com.yusufcakmak.numberkeyboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yusufcakmak.keyboard.KeyboardListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), KeyboardListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numView.setKeyboardListener(this)
    }

    override fun onTextChanged(str: String) {
        et.setText(str)
    }

}
