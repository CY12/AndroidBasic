package com.example.space.ext


import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * @author: hujianqiang
 * @date: 2021/12/1
 */


val RecyclerView?.firstVisibleItemPosition: Int
    get() {
        if (this == null || layoutManager == null) {
            return -1
        }
        val layoutManager = this.layoutManager

        return when (layoutManager) {
            is LinearLayoutManager -> {
                layoutManager.findFirstVisibleItemPosition()
            }
            is GridLayoutManager -> {
                layoutManager.findFirstVisibleItemPosition()
            }
            is StaggeredGridLayoutManager -> {
                layoutManager.findFirstVisibleItemPositions(null)?.get(0) ?: -1
            }
            else -> -1
        }
    }

val RecyclerView?.lastVisibleItemPosition: Int
    get() {
        if (this == null || layoutManager == null) {
            return -1
        }
        val layoutManager = this.layoutManager

        return when (layoutManager) {
            is LinearLayoutManager -> {
                layoutManager.findLastVisibleItemPosition()
            }
            is GridLayoutManager -> {
                layoutManager.findLastVisibleItemPosition()
            }
            is StaggeredGridLayoutManager -> {
                layoutManager.findLastVisibleItemPositions(null)?.get(0) ?: -1
            }
            else -> -1
        }
    }

val RecyclerView?.allVisibleViewHolder: List<RecyclerView.ViewHolder>?
    get() {
        if (this == null) {
            return null
        }
        val firstVisiblePos = firstVisibleItemPosition
        val lastVisiblePos = lastVisibleItemPosition
        return (firstVisiblePos..lastVisiblePos).toList()
            .map {
                findViewHolderForAdapterPosition(it)
            }.filter {
                it != null
            }.map {
                it as RecyclerView.ViewHolder
            }
    }

fun RecyclerView?.noNestScrollHorizontal() {
    this?.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {

        private var initX: Float = 0.toFloat()
        private var initY: Float = 0.toFloat()

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            val action = e.action
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    initX = e.x
                    initY = e.y
                    rv.parent.requestDisallowInterceptTouchEvent(true)
                }
                MotionEvent.ACTION_MOVE -> if (Math.abs(e.y - initY) > Math.abs(e.x - initX)) {
                    rv.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    })
}
fun <T : View> androidx.recyclerview.widget.RecyclerView.ViewHolder.bind(@IdRes idRes: Int): Lazy<T> {
    return lazy { this.itemView.findViewById<T>(idRes) }
}

fun RecyclerView.forceStopRecyclerViewScroll() {
    dispatchTouchEvent(MotionEvent.obtain(
        SystemClock.uptimeMillis(),
        SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0f, 0f, 0))
}
fun RecyclerView.ViewHolder?.string(@StringRes idRes: Int): String {
    if (this == null) {
        return ""
    }
    return this.itemView.context.resources.getString(idRes)
}

fun RecyclerView.ViewHolder?.string(@StringRes idRes: Int, vararg args: Any): String {
    if (this == null) {
        return ""
    }
    return this.itemView.context.resources.getString(idRes, args)
}