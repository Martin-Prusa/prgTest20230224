package cz.martin.models;

import java.time.LocalDateTime;

public class Tweet {
    private int id;
    private String content;
    private String author;
    private int likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Tweet(int id, String content, String author, int likes, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Tweet(String content, String author) {
        this.content = content;
        this.author = author;
        this.likes = 0;
    }

    public void like() {
        this.likes++;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public int getLikes() {
        return likes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
