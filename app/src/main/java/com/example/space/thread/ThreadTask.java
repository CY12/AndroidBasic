package com.example.space.thread;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;

public interface ThreadTask<T> {

    @Nullable
    @WorkerThread
    T doInBackground();

    @UiThread
    void onResult(@Nullable T result);

}
