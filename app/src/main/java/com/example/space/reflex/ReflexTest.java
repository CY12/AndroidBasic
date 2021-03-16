package com.example.space.reflex;

import android.util.Log;

public class ReflexTest {
    private String privateAttr;
    protected String protectedAttr;
    public String publicAttr;

    private final String name = "嘻嘻嘻";

    public ReflexTest(){};

    private ReflexTest(String privateAttr){
        this.privateAttr = privateAttr;
        Log.e("Test","ReflexTest privateAttr"+privateAttr);
    }

    private void privateMethod(){
        Log.e("Test","ReflexTest privateMethod");
    }

    protected void protectedMethod(){
        Log.e("Test","ReflexTest protectedMethod");
    }

    public void publicMethod(){
        Log.e("Test","ReflexTest publicMethod");
    }

    private void privateParameter(String name){
        Log.e("Test","ReflexTest privateParameter "+name);
    }


    /**
     * 这就涉及到JVM的内联优化了：
     *
     * 内联函数，编译器将指定的函数体插入并取代每一处调用该函数的地方（上下文），从而节省了每次调用函数带来的额外时间开支。
     *
     * 简单的说，就是JVM在处理代码的时候会帮我们优化代码逻辑，比如上述的final变量，已知final修饰后不会被修改，所以获取这个变量的时候就直接帮你在编译阶段就给赋值了。
     *
     */
    private void printName(){
        Log.e("Test","ReflexTest name"+name+"   hashCode:"+name.hashCode());
    }
    //这个方法实际上是
//    private void printName(){
//        Log.e("Test","嘻嘻嘻")； return name --> return "嘻嘻嘻"
//    }
}
