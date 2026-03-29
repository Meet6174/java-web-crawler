package com.meet.crawler;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

public class Worker implements Runnable {

    BlockingQueue<String> queue;
    Set<String> visited;
    TestJsoup parser;
    database db;
    static AtomicInteger count = new AtomicInteger(0);
    int choice;

    public Worker(BlockingQueue<String> queue, Set<String> visited,
                  TestJsoup parser, database db,int choice) {
        this.queue = queue;
        this.visited = visited;
        this.parser = parser;
        this.db = db;
        this.choice=choice;
    }

    public void run() {

        try {
            while (true) {

                String url = queue.take();
                
                
                try {
                        if(choice==1)
                            {
                                 ResultStore.links.add(url);
                            System.out.println(count.incrementAndGet()+"---->"+Thread.currentThread().getName()+ " processing: " + url);
                            }
                            else if (choice == 2)
                            {
                                
                                parser.getimages(url,db);
                            }
                            // else if( choice == 3)
                            // {

                            // }
                            else
                            {
                                System.out.println("enter correct choice");
                            }
                        
                        List<String> links = parser.extractLinks(url);
                        
                    for (String link : links) {

                        if (visited.add(link)) {
                            queue.put(link);
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Error on: " + url + e);
                }
            }

        } catch (InterruptedException e) {
            System.out.println("Thread stopped: " + Thread.currentThread().getName());
        }
    }
}