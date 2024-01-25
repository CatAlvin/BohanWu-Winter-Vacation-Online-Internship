package org.bohan.news;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        NewsRepository repository = new NewsRepository("localhost", "root", "wbh53100", "news");
        try {
            //·通过邮箱创建用户
            User user = new User("chenglan@163.com", "123456", "chenglan");
            repository.createUser(user.getEmail(), user.getPassword(), user.getNickname());

            //·用户编辑自己的昵称等账号信息
            repository.updateUserNickname(1, "chenglancat");
            repository.updateUserAvatarUrl(1, "http://www.example.com/avatar.jpeg");
            repository.updateUserPassword(1, "12345678");

            //·用户登陆
            boolean login1 = repository.login("963360557@163.com", "123456");
            boolean login2 = repository.login("chenglan@163.com", "12345678");
            System.out.println("login1: " + login1);
            System.out.println("login2: " + login2);

            //·（模拟）投放新闻
            for (int i = 0; i < 20; i++) {
                NewsItem newsItem = new NewsItem("新闻标题" + i, new Date(System.currentTimeMillis()), "新闻作者" + i, "新闻正文" + i, 1);
                repository.insertNews(newsItem);
            }

            //·用户获取主页新闻列表
            List<NewsItem> newsList1 = repository.fetchLatestNews(1, 10);
            System.out.println("newsList1: ");
            for (NewsItem newsItem : newsList1) {
                System.out.println(newsItem.getTitle() + "->" + newsItem.getAuthor() + " " + newsItem.getPublishDate());
            }
            //·用户按分区浏览新闻列表
            List<NewsItem> newsList2 = repository.fetchNewsByCategoryId(1, 1, 10);
            System.out.println("newsList2: ");
            for (NewsItem newsItem : newsList2) {
                System.out.println(newsItem.getTitle() + "->" + newsItem.getAuthor() + " " + newsItem.getPublishDate());
            }
            //·用户按标题和内容搜索新闻列表
            List<NewsItem> newsList3 = repository.fetchNewsByKeyword("新闻标题", "新闻内容", 1, 10);
            System.out.println("newsList3: ");
            for (NewsItem newsItem : newsList3) {
                System.out.println(newsItem.getTitle() + "->" + newsItem.getAuthor() + " " + newsItem.getPublishDate());
            }
            //·用户对新闻评论
            Comment comment1 = new Comment(1, 1, "评论内容");
            repository.commentNews(comment1);

            //·用户访问新闻，并且加载新闻下对应的评论
            NewsItem newsItem = repository.fetchNewsById(1);
            System.out.println("newsItem: " + newsItem.getTitle() + "->" + newsItem.getAuthor() + " " + newsItem.getPublishDate() + " " + newsItem.getBody());
            Comment comment = repository.fetchCommentByNewsId(1);
            System.out.println("comment: " + comment.getContent() + "->" + comment.getCommentTime());

            //·用户给新闻进行收藏、分享
            repository.favoriteNews(1, 1);
            repository.shareNews(1);
            NewsItem newsItem1 = repository.fetchNewsById(1);
            System.out.println("newsItem1: favorites_count: " + newsItem1.getFavoritesCount() + " shares_count: " + newsItem1.getSharesCount());

            //·用户对评论进行点赞/点踩
            repository.upvoteComment(1);
            repository.downvoteComment(1);
            Comment comment2 = repository.fetchCommentByNewsId(1);
            System.out.println("comment2: upvotes: " + comment2.getUpvotes() + " downvotes: " + comment2.getDownvotes());

            //·用户删除自己的评论
            repository.deleteComment(1, 1);

            //·用户在个人页中观看自己的评论历史
            List<Comment> commentHistory = repository.fetchCommentHistory(1);
            System.out.println("commentHistory: ");
            for (Comment comment3 : commentHistory) {
                System.out.println(comment3.getContent() + "->" + comment3.getCommentTime());
            }
            //·用户在个人页中观看自己的浏览历史
            List<NewsItem> browsingHistory = repository.fetchBrowsingHistory(1);
            System.out.println("browsingHistory: ");
            for (NewsItem newsItem2 : browsingHistory) {
                System.out.println(newsItem2.getTitle() + "->" + newsItem2.getAuthor() + " " + newsItem2.getPublishDate() + " " + newsItem2.getBody());
            }

            //·投放广告
            Advertisement advertisement1 = new Advertisement("广告标题", "广告文字内容", "首页列表内嵌广告", "广告主");
            repository.insertAdvertisement(advertisement1);

            //·按照内容类型获取广告
            List<Advertisement> advertisementList = repository.fetchAdvertisementsByPlacement("首页列表内嵌广告", 1, 10);
            System.out.println("advertisementList: ");
            for (Advertisement advertisement : advertisementList) {
                System.out.println(advertisement.getTitle() + "->" + advertisement.getTextContent() + " " + advertisement.getImageUrl());
            }

            //·关闭数据库连接
            repository.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
