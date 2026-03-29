package com.meet.crawler;

import java.util.concurrent.*;
import java.util.*;

public class ManageQ {

    BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    Set<String> visited = ConcurrentHashMap.newKeySet();

    ExecutorService executor = Executors.newFixedThreadPool(5);

    TestJsoup parser = new TestJsoup();
    database db = new database();

    int choice;
    int MAX_URLS = 500;

    public void startCrawlingFromGUI(String seed, int choice) throws Exception {

    this.choice = choice;

    queue.clear();
    visited.clear();

    queue.add(seed);
    visited.add(seed);

    for (int i = 0; i < 5; i++) {
        executor.execute(new Worker(queue, visited, parser, db, choice));
    }

    while (visited.size() < MAX_URLS) {
        Thread.sleep(2000);
    }

    executor.shutdownNow();
}
    public void startCrawling() throws Exception {

        System.out.println("Enter your link to Crawl : ");
        Scanner sc = new Scanner(System.in);
        String seed = sc.nextLine();
        System.out.println("Press 1 for links :");
        System.out.println("Press 2 for images :");
       // System.out.println("Press 3 for tables :");
        choice=sc.nextInt();
        queue.add(seed);
        visited.add(seed);
        
       for (int i = 0; i < 5; i++) {
            executor.execute(new Worker(queue, visited, parser, db,choice));
        }

        while (visited.size() < MAX_URLS) {
            Thread.sleep(5000);
        }
        executor.shutdownNow();
        sc.close();
    }
}