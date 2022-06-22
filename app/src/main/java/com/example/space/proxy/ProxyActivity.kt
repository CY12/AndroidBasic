package com.example.space.proxy

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.example.space.R
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

class ProxyActivity : AppCompatActivity() {

    var mByeInterface: ByeInterface? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proxy)

        findViewById<TextView>(R.id.tv_proxy).setOnClickListener {
            var bye: ByeInterface = Bye()
            var handler: InvocationHandler = ProxyHandler(bye)
            var proxyBye: ByeInterface = Proxy.newProxyInstance(bye.javaClass.classLoader,bye.javaClass.interfaces,handler) as ByeInterface
            proxyBye.sayBye()
            proxyBye.sayHello()

            mByeInterface?.sayBye()
            mByeInterface?.sayHello()
        }
        setOnByeListener(object :ByeInterface{
            override fun sayBye() {
                Log.e("Test","anonymous sayBye")
            }

            override fun sayHello() {
                Log.e("Test","anonymous sayHello")
            }

        })

    }

    private fun setOnByeListener(byeInterface: ByeInterface){
        val handler: InvocationHandler = ProxyHandler(byeInterface)
        val clazz: Class<*> = byeInterface.javaClass
        mByeInterface = Proxy.newProxyInstance(clazz.classLoader,getInterfaces(clazz),handler) as ByeInterface

    }

    @VisibleForTesting
    fun getInterfaces(clazz: Class<*>?): Array<Class<*>?>? {
        var interfaces: Array<Class<*>?>? = null
        if (interfaces != null) {
            return interfaces
        }
        val superInterfaces = clazz!!.interfaces
        val superClazz = clazz.superclass
        if (clazz.isAnonymousClass) {
            if (superInterfaces != null && superInterfaces.size > 0) {
                return getInterfaces(superInterfaces[0])
            }
            return if (superClazz != Any::class.java) {
                getInterfaces(superClazz)
            } else null
        }
        val ret: MutableSet<Class<*>?> = HashSet()
        if (clazz.isInterface) {
            ret.add(clazz)
        }
        if (superClazz != null && superClazz != Any::class.java) {
            interfaces = getInterfaces(superClazz)
            if (interfaces != null) {
                for (inter in interfaces) {
                    ret.add(inter)
                }
            }
        }
        if (superInterfaces != null) {
            for (inter in superInterfaces) {
                interfaces = getInterfaces(inter)
                if (interfaces != null) {
                    for (i in interfaces) {
                        ret.add(i)
                    }
                }
            }
        }
        interfaces = arrayOfNulls<Class<*>?>(ret.size)
        var index = 0
        for (inter in ret) {
            interfaces[index++] = inter
        }
        return interfaces
    }

}