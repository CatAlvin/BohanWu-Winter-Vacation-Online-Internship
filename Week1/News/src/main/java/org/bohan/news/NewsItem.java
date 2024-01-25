package org.bohan.news;

import java.sql.Date;

public class NewsItem {
    private int newsId;
    private String title;
    private String coverImageUrl;
    private Date publishDate;
    private String author;
    private int categoryId;
    private int viewsCount;
    private int favoritesCount;
    private int sharesCount;
    private boolean sponsored;
    private String body;

    public NewsItem() {
        this.newsId = 0;
        this.title = null;
        this.coverImageUrl = null;
        this.publishDate = null;
        this.author = null;
        this.categoryId = 0;
        this.viewsCount = 0;
        this.favoritesCount = 0;
        this.sharesCount = 0;
        this.sponsored = false;
        this.body = null;
    }

    public NewsItem(String title, Date publishDate, String author, String body, int categoryId) {
        this.title = title;
        this.coverImageUrl = null;
        this.publishDate = publishDate;
        this.author = author;
        this.categoryId = categoryId;
        this.viewsCount = 0;
        this.favoritesCount = 0;
        this.sharesCount = 0;
        this.sponsored = false;
        this.body = body;
    }

    public NewsItem(int newsId, String title, String coverImageUrl, Date publishDate, String author, int viewsCount, int favoritesCount, int sharesCount, boolean sponsored, String body) {
        this.newsId = newsId;
        this.title = title;
        this.coverImageUrl = coverImageUrl;
        this.publishDate = publishDate;
        this.author = author;
        this.viewsCount = viewsCount;
        this.favoritesCount = favoritesCount;
        this.sharesCount = sharesCount;
        this.sponsored = sponsored;
        this.body = body;
    }

    public int getNewsId() {
        return newsId;
    }
    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    public String getCoverImageUrl() {
        return coverImageUrl;
    }
    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public int getViewsCount() {
        return viewsCount;
    }
    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }
    public int getFavoritesCount() {
        return favoritesCount;
    }
    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }
    public int getSharesCount() {
        return sharesCount;
    }
    public void setSharesCount(int sharesCount) {
        this.sharesCount = sharesCount;
    }
    public boolean isSponsored() {
        return sponsored;
    }
    public void setSponsored(boolean sponsored) {
        this.sponsored = sponsored;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}