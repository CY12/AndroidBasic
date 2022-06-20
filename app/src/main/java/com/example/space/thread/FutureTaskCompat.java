package com.example.space.thread;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskCompat<V> extends FutureTask<V> {
    private static final String TAG = "FutureTaskCompat";
    private volatile Exception exception;

    public FutureTaskCompat(Callable<V> callable) {
        super(callable);
    }

    public FutureTaskCompat() {
        super(new Callable<V>() {
            @Override
            public V call() throws Exception {
                throw new IllegalStateException("this should never be called");
            }
        });
    }

    @Override
    public void set(V result) {
        super.set(result);
    }

    public V get(long timeout, V defValue) {
        try {
            return super.get(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            return defValue;
        }
    }

    @Override
    public V get() {
        try {
            return super.get();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    public void setException(Exception e) {
        exception = e;
    }

    public Exception getException() {
        return exception;
    }
}
