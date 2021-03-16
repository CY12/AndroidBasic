package com.example.space.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

/**
 * InterService : 带有线程 (HandlerThread) 的service
 * HandlerThread : 带有 looper 的 thread ( thread 有looper 后 可以发消息给该线程,该线程的looper 获取到消息后可以该线程处理)
 *
 *
 */
public class IntentServiceTest extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServiceTest(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
