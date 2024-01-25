package org.bohan.ioc;
import java.util.List;
public class Press {
    private String title; // 标题
    private String content; // 内容
    private List<Ad> ads; // 多个广告
    private List<Comments> comments; // 多个评论

    public Press(String title, String content, List<Ad> ads, List<Comments> comments) {
        this.title = title;
        this.content = content;
        this.ads = ads;
        this.comments = comments;
    }

    public Press() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Press{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", ads=" + ads +
                ", comments=" + comments +
                '}';
    }
}
