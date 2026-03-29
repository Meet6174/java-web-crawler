package com.meet.crawler;

import javax.swing.*;
import java.awt.*;

public class CrawlerUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Web Crawler");

        JLabel label = new JLabel("Enter URL:");
        label.setBounds(20, 20, 120, 30);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField urlField = new JTextField();
        urlField.setBounds(150, 20, 500, 30);
        urlField.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton linkBtn = new JButton("Show Links");
        linkBtn.setBounds(150, 70, 180, 40);
        linkBtn.setFont(new Font("Arial", Font.BOLD, 16));

        JButton imgBtn = new JButton("Download Images");
        imgBtn.setBounds(350, 70, 220, 40);
        imgBtn.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea output = new JTextArea();
        output.setFont(new Font("Monospaced", Font.BOLD, 20)); // 🔥 BIG FONT
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(output);
        scroll.setBounds(20, 140, 1200, 600);

        frame.add(label);
        frame.add(urlField);
        frame.add(linkBtn);
        frame.add(imgBtn);
        frame.add(scroll);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 🔥 SHOW LINKS BUTTON
        linkBtn.addActionListener(e -> {

            String url = urlField.getText();
            output.setText("Crawling links...\n");

            ResultStore.reset();

            new Thread(() -> {
                try {
                    ManageQ crawler = new ManageQ();

                    // start crawler
                    new Thread(() -> {
                        try {
                            crawler.startCrawlingFromGUI(url, 1);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }).start();

                    // 🔥 LIVE UPDATE LOOP
                    while (true) {

                        StringBuilder sb = new StringBuilder();

                        int i = 1;
                        for (String link : ResultStore.links) {
                            sb.append(i++).append(". ").append(link).append("\n");
                        }

                        SwingUtilities.invokeLater(() -> {
                            output.setText(sb.toString());
                        });

                        Thread.sleep(1000);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        });

        // 🔥 DOWNLOAD IMAGES BUTTON
        imgBtn.addActionListener(e -> {

            String url = urlField.getText();
            output.setText("Downloading images...\n");

            ResultStore.reset();

            new Thread(() -> {
                try {
                    ManageQ crawler = new ManageQ();

                    new Thread(() -> {
                        try {
                            crawler.startCrawlingFromGUI(url, 2);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }).start();

                    // 🔥 LIVE COUNT UPDATE
                    while (true) {

                        int count = ResultStore.imageCount.get();

                        SwingUtilities.invokeLater(() -> {
                            output.setText("Images downloaded: " + count);
                        });

                        Thread.sleep(1000);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        });
    }
}