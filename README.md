# java-web-crawler
# Multithreaded Web Crawler (Java)

## 📌 Overview

This project is a multithreaded web crawler built in Java that crawls web pages, extracts image links along with their source URLs, and stores them in a MySQL database.

It demonstrates core backend concepts like web scraping, concurrency, and data storage.

---

## 🚀 Features

* Crawls web pages starting from a seed URL
* Extracts image links (`<img>` tags) from HTML
* Stores image URLs along with their page source
* Uses multithreading for faster crawling
* Avoids duplicate URLs using a visited set
* Displays real-time crawling output in terminal

---

## 🛠️ Tech Stack

* **Language:** Java
* **Web Scraping:** Jsoup
* **Database:** MySQL
* **Database Connectivity:** JDBC
* **Concurrency:** Threads, BlockingQueue

---

## ⚙️ System Workflow

1. Start with an initial URL
2. Add URL to a shared queue
3. Worker threads process URLs in parallel
4. HTML is fetched using Jsoup
5. Image links (`img src`) are extracted
6. Data (image URL + page URL) is stored in MySQL
7. New links are discovered and added to the queue
8. Duplicate URLs are avoided using a visited set

---

## 🧠 Key Concepts Demonstrated

* Multithreading and thread coordination
* Producer–Consumer pattern using BlockingQueue
* BFS-like traversal of web pages
* Basic data pipeline (crawl → extract → store)

---

## 🔮 Future Improvements

* Implement thread pool using `ExecutorService`
* Add rate limiting to prevent excessive requests
* Improve error handling and retry logic
* Add filtering (e.g., specific domains or image types)
* Build a proper UI for real-time monitoring

---

## ▶️ How to Run

### Prerequisites

* Java (JDK 8 or above)
* MySQL installed and running

### Steps

1. Clone the repository
2. Open the project in IntelliJ / Eclipse
3. Configure database credentials in code
4. Run the main class

---
## 📁 Project Structure

```id="projstruct"
src/
 └── main/
      └── java/
           └── com/meet/crawler/
pom.xml
README.md
```

---
## 👨‍💻 Author

Meet
Computer Science Student
