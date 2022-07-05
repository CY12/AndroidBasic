package com.example.space.ext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.space.databinding.ActivityExtensionBinding

/**
 *  https://blog.csdn.net/zhangying1994/article/details/105038673
 */
class ExtensionActivity : AppCompatActivity() {

    private val binding: ActivityExtensionBinding by inflate()
    private val a:String = getString()
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("Test","ExtensionActivity onCreate")

        super.onCreate(savedInstanceState)
//        binding = ActivityExtensionBinding.inflate(layoutInflater)
//        setContentView(binding.getRoot())
        Log.e("Test","start binding")

        binding.tvChangeCorner.setOnClickListener {
            var v: ViewBindingTestActivity = ViewBindingTestActivity()
            v.testType()
            toSay()
        }
    }

    private fun getString():String{
        Log.e("Test","init getString")
        return "sss"
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