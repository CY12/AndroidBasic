package com.example.space.ext

import android.util.Log

open class Base{
    open fun talk(){
        Log.e("Test", "this is Base talk")

    }
}

class Extension : Base(){

    override fun talk(){
        Log.e("Test", "this is Extension talk")

    }
}

fun Base.say() {
    Log.e("Test", "this is Base say")
}

fun Extension.say() {
    Log.e("Test", "this is Extension say")
}
