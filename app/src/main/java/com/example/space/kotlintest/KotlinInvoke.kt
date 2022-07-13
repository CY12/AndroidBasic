package com.example.space.kotlintest

import android.os.Handler
import android.os.IBinder
import leakcanary.ServiceWatcher
import shark.SharkLog
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Proxy

class KotlinInvoke :Function1<String,Unit> {
    //高阶函数类型变量
    override fun invoke(p1: String) {
        println("Kotlin Invoke : $p1")
    }
    private val activityThreadClass by lazy { Class.forName("android.app.ActivityThread") }

    private val activityThreadInstance by lazy {
        activityThreadClass.getDeclaredMethod("currentActivityThread").invoke(null)!!
    }

    private var uninstallActivityThreadHandlerCallback: Function<Unit>? = null


    private fun swapActivityThreadHandlerCallback(swap: (Handler.Callback?) -> Handler.Callback?) {
        val mHField =
            activityThreadClass.getDeclaredField("mH").apply { isAccessible = true }
        val mH = mHField[activityThreadInstance] as Handler

        val mCallbackField =
            Handler::class.java.getDeclaredField("mCallback").apply { isAccessible = true }
        val mCallback = mCallbackField[mH] as Handler.Callback?
        mCallbackField[mH] = swap(mCallback)
    }

    fun get(){



        try {
            swapActivityThreadHandlerCallback { mCallback ->
                uninstallActivityThreadHandlerCallback = {
                    swapActivityThreadHandlerCallback {
                        mCallback
                    }
                }
                Handler.Callback { msg ->
                    // https://github.com/square/leakcanary/issues/2114
                    // On some Motorola devices (Moto E5 and G6), the msg.obj returns an ActivityClientRecord
                    // instead of an IBinder. This crashes on a ClassCastException. Adding a type check
                    // here to prevent the crash.
                    if (msg.obj !is IBinder) {
                        return@Callback false
                    }

//                    if (msg.what == ServiceWatcher.STOP_SERVICE) {
//                        val key = msg.obj as IBinder
//                        activityThreadServices[key]?.let {
//                            onServicePreDestroy(key, it)
//                        }
//                    }
                    mCallback?.handleMessage(msg) ?: false
                }
            }
//            swapActivityManager { activityManagerInterface, activityManagerInstance ->
//                uninstallActivityManager = {
//                    swapActivityManager { _, _ ->
//                        activityManagerInstance
//                    }
//                }
//                Proxy.newProxyInstance(
//                    activityManagerInterface.classLoader, arrayOf(activityManagerInterface)
//                ) { _, method, args ->
//                    if (ServiceWatcher.METHOD_SERVICE_DONE_EXECUTING == method.name) {
//                        val token = args!![0] as IBinder
//                        if (servicesToBeDestroyed.containsKey(token)) {
//                            onServiceDestroyed(token)
//                        }
//                    }
//                    try {
//                        if (args == null) {
//                            method.invoke(activityManagerInstance)
//                        } else {
//                            method.invoke(activityManagerInstance, *args)
//                        }
//                    } catch (invocationException: InvocationTargetException) {
//                        throw invocationException.targetException
//                    }
//                }
//            }
        } catch (ignored: Throwable) {
            SharkLog.d(ignored) { "Could not watch destroyed services" }
        }

    }

}

fun main(args: Array<String>){
    println("start")

    val kotlinInvoke = KotlinInvoke()
    println("end")
}