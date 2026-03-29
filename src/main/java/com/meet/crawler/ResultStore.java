package com.meet.crawler;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ResultStore {

    public static CopyOnWriteArrayList<String> links = new CopyOnWriteArrayList<>();

    public static AtomicInteger imageCount = new AtomicInteger(0);

    public static void reset() {
        links.clear();
        imageCount.set(0);
    }
}