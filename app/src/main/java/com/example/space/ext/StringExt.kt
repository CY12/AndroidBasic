package com.example.space.ext

class StringExt {

    fun String.hello(world: String): String {
        return "hello " + world + this.length;
    }

}