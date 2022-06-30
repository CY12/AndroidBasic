package com.example.space.ext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.space.R
import kotlinx.android.synthetic.main.activity_extension.*

/**
 *  https://blog.csdn.net/zhangying1994/article/details/105038673
 */
class ExtensionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extension)
        tv_change_corner.setOnClickListener {
            toSay()
        }
    }

    fun toSay(){
        val insatnce: Base = Extension()
        val instance1: Extension = Extension()
        insatnce.say()
        instance1.say()

        insatnce.talk()
        instance1.talk()

    }
}