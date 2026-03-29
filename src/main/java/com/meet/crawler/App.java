package com.meet.crawler;

public class App {

    public static void main(String[] args) throws Exception {

        ManageQ crawler = new ManageQ();
        crawler.startCrawling();
        System.out.println("Crawling Completed");
    }
}
//https://www.gcet.ac.in/index.php
//https://www.gcet.ac.in/staff.php
//https://www.gcet.ac.in/gallery.php
//https://books.toscrape.com/