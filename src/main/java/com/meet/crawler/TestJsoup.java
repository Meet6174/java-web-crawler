package com.meet.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.util.*;

public class TestJsoup {

    String seedUrl;

    public List<String> extractLinks(String link) throws Exception {// gets one link return all links

        List<String> urls = new ArrayList<>();

        Document doc = Jsoup.connect(link).get();

        Elements links = doc.select("a[href]");

        for (Element s : links) {

            String url = s.absUrl("href");

            if (!url.isEmpty() && url.startsWith("http")) {
                urls.add(url);
            }
        }
        return urls;
    }

    public void getimages(String link, database db) throws Exception {
        String folderpath = "D:\\programming\\project\\webcrawler\\images\\";
        Document doc = Jsoup.connect(link)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/122.0.0.0")
                .timeout(10000)
                .get();
        Elements imagelinks = doc.select("img");
        File dir = new File(folderpath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File folder = new File(folderpath);
        int counter;
        if (folder.list() != null) {
            counter = folder.list().length + 1;
        } else {
            counter = 1;
        }

        for (Element i : imagelinks) {
            String img = i.absUrl("data-src");
            if (img.isEmpty()) {
                img = i.absUrl("src");
            }
            if (img.isEmpty()) {
                continue;
            }
            String fileName = "image_" + counter + ".jpg";

            try (InputStream pipe = new URL(img).openStream()) {
                Files.copy(pipe, Paths.get(folderpath + fileName), REPLACE_EXISTING);
                System.out.println("Saved: " + fileName + " from " + img);
                counter++;
                ResultStore.imageCount.incrementAndGet();
                db.forimage(fileName, link, img);
            } catch (IOException e) {
                System.out.println("Could not download image: " + img);
            }
            try {
               // Thread.sleep(1000);
                //System.out.println("WAIT 1 secound");
            } catch (Exception e) {
                System.out.println("Intrupted");
            }
        }
    }
}
