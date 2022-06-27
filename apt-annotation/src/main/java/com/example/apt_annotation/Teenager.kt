package com.example.apt_annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
annotation class Teenager(val visible: Int = 0,
                          val clickable: Boolean = true)