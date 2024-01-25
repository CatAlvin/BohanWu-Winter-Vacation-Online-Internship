package org.bohan.ioc;

public class Comments {
    private String author; // 评论人
    private String text; // 评论内容

    public Comments(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public Comments() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

