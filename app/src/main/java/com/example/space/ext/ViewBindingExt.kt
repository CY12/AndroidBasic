package com.example.space.ext

import android.app.Activity
import android.app.Dialog
import android.util.Log
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

inline fun <reified VB : ViewBinding> Activity.inflate() = lazy {
    Log.e("Test","ViewBinding Inflate")
    inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
}

inline fun <reified VB : ViewBinding> Dialog.inflate() = lazy {
    inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified VB : ViewBinding> inflateBinding(layoutInflater: LayoutInflater) =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as VB
