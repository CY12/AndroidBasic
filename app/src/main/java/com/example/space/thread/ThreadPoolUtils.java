package com.example.space.thread;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;


import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ThreadPoolUtils {
    private static final String TAG = "ThreadPoolUtils";
    private static final int MAX_THREAD_COUNT = Math.min(2 * Runtime.getRuntime().availableProcessors() + 1, 10);
    private static AtomicInteger sThreadId = new AtomicInteger();

    private static ThreadPoolExecutor sExecutorMultiple
            = ThreadExecutors.newFixedThreadPool(MAX_THREAD_COUNT, "Multi");

    private static ThreadPoolExecutor sExecutorSingle
            = ThreadExecutors.newFixedThreadPool(1, "Single");

    private static ThreadPoolExecutor sThirdPartLibrarySingleExecutor
            = ThreadExecutors.newFixedThreadPool(1, "Third");

    private static Handler sMainThreadHandler = new Handler(Looper.getMainLooper());
    private static ScheduledExecutorService sTimerExecutor = ThreadExecutors.newScheduledThreadPool(1, "ThreadPoolTimer");

    private ThreadPoolUtils() {
    }

    /**
     * 多线程处理
     * @param command {@link Runnable}
     */
    public static void executeOnMultiple(final Runnable command) {
        if (command == null) {
            return;
        }
        sExecutorMultiple.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    command.run();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 安全多线程处理（避免子线程中开辟子线程）
     * @param command {@link Runnable}
     */
    //什么鬼理论...
    @Deprecated
    public static void executeOnMultipleSafely(final Runnable command) {
        if (command == null) {
            return;
        }
        sMainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                sExecutorMultiple.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            command.run();
                        } catch (Throwable e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /**
     * 多线程处理延迟处理
     */
    public static void executeOnMultiple(final Runnable command, long delayMillis) {
        if (command == null) {
            return;
        }
        sTimerExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                executeOnMultiple(command);
            }
        }, delayMillis, TimeUnit.MILLISECONDS);
    }


    public static Future<?> executorMultiple(Runnable runnable) {
        return sExecutorMultiple.submit(runnable);
    }

    /**
     * 非UI单线程处理
     * @param command {@link Runnable}
     */
    public static void executeOnSingle(final Runnable command) {
        if (command == null) {
            return;
        }
        sExecutorSingle.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    command.run();
                } catch (Throwable e){
                    e.printStackTrace();

                }
            }
        });
    }

    public static void executeOnSingle(final Runnable command, long delayMillis) {
        sTimerExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                executeOnSingle(command);
            }
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * 多线程处理, UI线程返回结果
     * @param task {@link ThreadTask}
     * @param <T>
     */
    public static <T> void executeOnMultiple(final ThreadTask<T> task) {
        if (task == null) {
            return;
        }
        sExecutorMultiple.execute(new Runnable() {
            @Override
            public void run() {
                T result = null;
                try {
                    result = task.doInBackground();
                } finally {
                    final T r = result;
                    sMainThreadHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            task.onResult(r);
                        }
                    });
                }
            }
        });
    }

    public static <T> void executeOnMultiple(final ThreadTask<T> task, long delayMillis) {
        sTimerExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                executeOnMultiple(task);
            }
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * 非UI单线程处理, UI线程返回结果
     * @param task {@link ThreadTask}
     * @param <T>
     */
    public static <T> void executeOnSingle(final ThreadTask<T> task) {
        if (task == null) {
            return;
        }
        sExecutorSingle.execute(new Runnable() {
            @Override
            public void run() {
                T result = null;
                try {
                    result = task.doInBackground();
                } finally {
                    final T r = result;
                    sMainThreadHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            task.onResult(r);
                        }
                    });
                }
            }
        });
    }

    public static <T> void executeOnSingle(final ThreadTask<T> task, long delayMillis) {
        sTimerExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                executeOnSingle(task);
            }
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    public static void executeOnUiThreadImmediately(final Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (isMainThread()) {
            runnable.run();
        } else {
            executeOnUiThread(runnable);
        }
    }

    public static void executeOnUiThreadSync(final Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (isMainThread()) {
            runnable.run();
        } else {
            final FutureTaskCompat future = new FutureTaskCompat();
            executeOnUiThread(new Runnable() {
                @Override
                public void run() {
                    runnable.run();
                    future.set(null);
                }
            });
            future.get();
        }
    }

    public static <T> T executeOnUiThreadSync(@NonNull final Callable<T> callable, T def) {
        T ret = null;
        try {
            ret = executeOnUiThreadSync(callable);
        } catch (Exception e) {
        }
        return ret != null ? ret : def;
    }

    public static <T> T executeOnUiThreadSync(@NonNull final Callable<T> callable) {
        if (callable == null) {
            throw new IllegalArgumentException("callable can not be null!");
        }
        if (isMainThread()) {
            try {
                return callable.call();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            final FutureTaskCompat<T> future = new FutureTaskCompat<>();
            executeOnUiThread(new Runnable() {
                @Override
                public void run() {
                    T ret = null;
                    try {
                        ret = callable.call();
                    } catch (Exception e) {
                        future.setException(e);
                    }
                    future.set(ret);
                }
            });
            T ret = future.get();
            Exception exception = future.getException();
            if (exception instanceof RuntimeException) {
                throw (RuntimeException) exception;
            } else if (exception != null) {
                throw new RuntimeException(exception);
            }
            return ret;
        }
    }

    /**
     * 在UI线程中执行
     * @param command {@link Runnable}
     */
    public static void executeOnUiThread(final Runnable command) {
        if (command == null) {
            return;
        }
        sMainThreadHandler.post(command);
    }

    /**
     * 在UI线程中执行
     * @param command {@link Runnable}
     */
    public static void executeOnUiThread(final Runnable command, long delayMillis) {
        if (command == null) {
            return;
        }
        sMainThreadHandler.postDelayed(command, delayMillis);
    }

    /**
     * 移除UI线程中的Callback
     * @param r {@link Runnable}
     */
    public static void removeUiCallbacks(Runnable r) {
        if (r == null) {
            return;
        }
        sMainThreadHandler.removeCallbacks(r);
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static void executeOnThirdPart(final Runnable runnable) {
        sThirdPartLibrarySingleExecutor.execute(runnable);
    }

    public static void executeOnNewThread(Runnable r) {
        if (r == null) {
            return;
        }
        new Thread(r, ThreadExecutors.THREAD_NAME_PREFIX + "Thread-" + sThreadId.incrementAndGet()).start();
    }

    public static <T> void executeOnNewThread(final ThreadTask<T> task) {
        if (task == null) {
            return;
        }
        new Thread() {
            @Override
            public void run() {
                final T result = task.doInBackground();
                sMainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        task.onResult(result);
                    }
                });

            }
        }.start();
    }

    public static void ensureUIThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new RuntimeException("Method can only be called from ui thread!");
        }
    }

    public static Handler getMainThreadHandler() {
        return sMainThreadHandler;
    }

    public static void configMultiThreadPool(int coreSize, int maxSize,
                                             boolean allowCoreThreadTimeout,
                                             long keepAliveTime) {
        sExecutorMultiple.setCorePoolSize(coreSize);
        sExecutorMultiple.setMaximumPoolSize(maxSize);
        sExecutorMultiple.allowCoreThreadTimeOut(allowCoreThreadTimeout);
        sExecutorMultiple.setKeepAliveTime(keepAliveTime, TimeUnit.MILLISECONDS);
    }
}

