package org.bohan.news;

import java.util.Date;

public class Comment {
    private int comment_id;
    private int user_id;
    private int news_id;
    private String content;
    private Date comment_time;
    private int replied_comment_id;
    private int upvotes;
    private int downvotes;

    public Comment() {
        this.comment_id = 0;
        this.user_id = 0;
        this.news_id = 0;
        this.content = null;
        this.comment_time = null;
        this.replied_comment_id = 0;
        this.upvotes = 0;
        this.downvotes = 0;
    }

    public Comment(int user_id, int news_id, String content) {
        this.comment_id = 0;
        this.user_id = user_id;
        this.news_id = news_id;
        this.content = content;
        this.comment_time = null;
        this.replied_comment_id = 0;
        this.upvotes = 0;
        this.downvotes = 0;
    }

    public int getCommentId() {
        return comment_id;
    }

    public void setCommentId(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getNewsId() {
        return news_id;
    }

    public void setNewsId(int news_id) {
        this.news_id = news_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentTime() {
        return comment_time;
    }

    public void setCommentTime(Date comment_time) {
        this.comment_time = comment_time;
    }

    public int getRepliedCommentId() {
        return replied_comment_id;
    }

    public void setRepliedCommentId(int replied_comment_id) {
        this.replied_comment_id = replied_comment_id;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }
}
