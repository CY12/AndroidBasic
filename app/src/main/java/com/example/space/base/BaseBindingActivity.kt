package com.example.space.base

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.space.thread.Student
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class BaseBindingActivity<T,VB : ViewBinding> : AppCompatActivity() {
    lateinit var binding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun getViewBindingClass(type: ParameterizedType):Class<VB>?{
        type.actualTypeArguments.forEach {
            if (Student::javaClass == it){
                Log.e("Test","Student")

            }
            if (it == ViewBinding::javaClass ){
                Log.e("Test","ViewBinding")

            }
            Log.e("Test",it.toString())
            Log.e("Test",getTypeInfo(type))
        }
        return  null
    }

    open fun testType(){
        //利用反射，调用指定ViewBinding中的inflate方法填充视图
        val type = javaClass.genericSuperclass
//        Type
        if (type is ParameterizedType) {
            getViewBindingClass(type)
            val clazz = type.actualTypeArguments[1] as Class<VB>
            val method = clazz.getMethod("inflate", LayoutInflater::class.java)
            binding = method.invoke(null, layoutInflater) as VB
            setContentView(binding.root)
        }

    }

      fun getTypeInfo(type: Type): String? {
        val typeName = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            type.typeName
        } else {
            "null"
        }
          val clazz: Class<*> = type.javaClass
        val interfaces = clazz.interfaces
        val typeInterface = StringBuilder()
        for (clazzType in interfaces) {
            typeInterface.append(clazzType.simpleName).append(",")
        }
        return "【" + typeName + "】    【" + clazz.simpleName + "】    【" + typeInterface + "】"
    }

    abstract fun getT(): T


    abstract fun init()
}
