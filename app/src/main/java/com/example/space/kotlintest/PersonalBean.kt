package com.example.space.kotlintest

data class PersonalBean(val name:String,val age: Int) {

    operator fun get(index: Int):Any{
        return when(index){
            0-> name
            1-> age
            else -> "name$name"+"age$age"
        }
    }
}

fun main(args: Array<String>){
    val personalBean = PersonalBean("wang",13)
    personalBean.get(3)
    personalBean[3]
}