package com.example.space.thread;

import android.os.HandlerThread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadExecutors {
    public static final String THREAD_NAME_PREFIX = "KKMH-";

    public static HandlerThread startHandlerThread(String name) {
        HandlerThread handlerThread = new HandlerThread(THREAD_NAME_PREFIX + name);
        handlerThread.start();
        return handlerThread;
    }

    public static HandlerThread startHandlerThread(String name, int priority) {
        HandlerThread handlerThread = new HandlerThread(THREAD_NAME_PREFIX + name, priority);
        handlerThread.start();
        return handlerThread;
    }

    public static class DefaultThreadFactory implements ThreadFactory {
        private final AtomicInteger mThreadNumber = new AtomicInteger(1);
        private final int mPriority;
        private final String mName;
        private final ThreadGroup mGroup;

        public DefaultThreadFactory(String name, int priority) {
            this(null, name, priority);
        }

        public DefaultThreadFactory(String group, String name, int priority) {
            this.mPriority = priority;
            this.mName = name;
            if (group == null) {
                SecurityManager s = System.getSecurityManager();
                this.mGroup = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            } else {
                this.mGroup = new ThreadGroup(group);
            }
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(mGroup, r, mName + "-" + mThreadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != mPriority)
                t.setPriority(mPriority);
            return t;
        }
    }

    public static class MyThreadFactory extends DefaultThreadFactory {

        public MyThreadFactory(String name, int priority) {
            super(null, THREAD_NAME_PREFIX + name, priority);
        }

        public MyThreadFactory(String group, String name, int priority) {
            super(group, THREAD_NAME_PREFIX + name, priority);
        }
    }

    public static ThreadPoolExecutor newCachedThreadPool(int coreSize,
                                                         int priority,
                                                         String name) {
        long keepAliveTime = 60L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new SynchronousQueue<>();
        ThreadFactory factory = new MyThreadFactory(name, priority);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize, Integer.MAX_VALUE, keepAliveTime,
                unit, workQueue, factory);
        executor.allowCoreThreadTimeOut(true);
        return executor;
    }

    // Fixed Thread Pool
    public static ThreadPoolExecutor newFixedThreadPool(int maximumPoolSize, String name) {
        return newFixedThreadPool(maximumPoolSize, maximumPoolSize, Thread.NORM_PRIORITY, name);
    }

    public static ThreadPoolExecutor newFixedThreadPool(int corePoolSize,
                                                        int maximumPoolSize,
                                                        int priority,
                                                        String name) {
        long keepAliveTime = 60L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        ThreadFactory factory = new MyThreadFactory(name, priority);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                unit, workQueue, factory, handler);
    }

    public static ScheduledExecutorService newScheduledThreadPool(int coreSize, String name) {
        return Executors.newScheduledThreadPool(coreSize, new MyThreadFactory(name, Thread.NORM_PRIORITY));
    }
}
