package com.example.space.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    AtomicInteger integer = new AtomicInteger();

    private void testInteger(){
        integer.incrementAndGet();

    }
}
