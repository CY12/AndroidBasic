package com.example.space.ext

import com.example.space.base.BaseBindingActivity
import com.example.space.databinding.ActivityExtensionBinding
import com.example.space.thread.Student

class ViewBindingTestActivity: BaseBindingActivity<Student, ActivityExtensionBinding>() {
    override fun getT(): Student {
        TODO("Not yet implemented")
    }

    override fun init() {
        TODO("Not yet implemented")
    }

    override fun testType(){
        super.testType()
    }
}