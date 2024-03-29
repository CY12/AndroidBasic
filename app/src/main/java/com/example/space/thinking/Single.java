package com.example.space.thinking;

public class Single {

    private static volatile Single single;

    public Single (){};

    public Single getSingleInstance(){
        if (single == null){
            synchronized (Single.class){
                if (single == null){
                    single = new Single();
                }
            }
        }
        return single;
    }

    /**
     * voliate关键字的两个作用
     * 1、 保证变量的可见性：当一个被volatile关键字修饰的变量被一个线程修改的时候，其他线程可以立刻得到修改之后的结果。当一个线程向被volatile关键字修饰的变量写入数据的时候，虚拟机会强制它被值刷新到主内存中。当一个线程用到被volatile关键字修饰的值的时候，虚拟机会强制要求它从主内存中读取。
     * 2、 屏蔽指令重排序：指令重排序是编译器和处理器为了高效对程序进行优化的手段，它只能保证程序执行的结果时正确的，但是无法保证程序的操作顺序与代码顺序一致。这在单线程中不会构成问题，但是在多线程中就会出现问题。非常经典的例子是在单例方法中同时对字段加入voliate，就是为了防止指令重排序。
     *
     * 编译期重排序的典型就是通过调整指令顺序，做到在不改变程序语义的前提下，尽可能减少寄存器的读取、存储次数，充分复用寄存器的存储值。
     * 比如我们有如下代码：
     *
     * int x = 10;
     * int y = 9;
     * x = x+10;
     * 假设编译器直接对上面代码进行编译，不进行重排序的话，我们简单分析一下执行这段代码的过程，首先加载x变量的内存地址到地址寄存器，然后会加载10到数据寄存器，然后CPU通过mov指令把10写入到地址寄存器中指定的内存地址中。然后加载y变量的内存地址到地址寄存器，加载9到数据寄存器，把9写入到内存地址中。进行第三行执行时，我们发现CPU需要重新加载x的内存地址和数据到寄存器，但如果我把第三行和第二行换一下顺序，那么执行过程中对于寄存器的存取就可以少很多次，同时对于程序结果没有任何影响。
     *
     * 另一个例子可以看下面的双重检查锁构造单例的代码
     *
     * public class Singleton {
     *     private volatile static Singleton singleton;
     *
     *     private Singleton() {}
     *
     *     public static Singleton getInstance() {
     *         if (singleton == null) { // 1
     *             synchronized(Singleton.class) {
     *                 if (singleton == null) {
     *                     singleton = new Singleton(); // 2
     *                 }
     *             }
     *         }
     *         return singleton;
     *     }
     * }
     * 实际上当程序执行到2处的时候，如果我们没有使用volatile关键字修饰变量singleton，就可能会造成错误。这是因为使用new关键字初始化一个对象的过程并不是一个原子的操作，它分成下面三个步骤进行：
     *
     * a. 给 singleton 分配内存
     * b. 调用 Singleton 的构造函数来初始化成员变量
     * c. 将 singleton 对象指向分配的内存空间（执行完这步 singleton 就为非 null 了）
     *
     * 如果虚拟机存在指令重排序优化，则步骤b和c的顺序是无法确定的。如果A线程率先进入同步代码块并先执行了c而没有执行b，此时因为singleton已经非null。这时候线程B到了1处，判断singleton非null并将其返回使用，因为此时Singleton实际上还未初始化，自然就会出错。synchronized可以解决内存可见性，但是不能解决重排序问题。
     *
     * 作者：鹅鹅鹅_
     * 链接：https://www.jianshu.com/p/a67dc1c11088
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
