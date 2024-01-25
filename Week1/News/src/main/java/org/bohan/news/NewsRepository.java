package org.bohan.news;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsRepository {
    private final Connection conn;

    public NewsRepository(String hostName, String username, String password, String databaseName) {
        this(hostName, username, password, databaseName, 3306);
    }

    public NewsRepository(String hostName, String username, String password, String databaseName, int portNumber) {
        String url = String.format("jdbc:mysql://%s:%d/%s", hostName, portNumber, databaseName);
        try {
            this.conn = DriverManager.getConnection(url, username, password);
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //-- ·通过邮箱创建用户
    //-- Example:
    //    INSERT INTO
    //    `User` (email, password, nickname)
    //    VALUES (
    //        'chenglancat@163.com',
    //                '123456',
    //                'chenglancat'
    //    );
    public void createUser(String email, String password, String nickname) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO `User` (email, password, nickname) VALUES (?, ?, ?)")) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, nickname);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //-- ·用户编辑自己的昵称等账号信息
    //-- Example:
    //    UPDATE `User` SET nickname = 'chenglancat' WHERE uid = 1;
    //-- 用户修改昵称
    //    UPDATE `User`
    //    SET
    //            avatar_url = 'http://www.example.com/avatar.jpeg'
    //    WHERE uid = 1;
    //-- 用户修改头像
    //    UPDATE `User` SET password = '12345678' WHERE uid = 1;
    //-- 用户修改密码
    public void updateUserNickname(int uid, String nickname) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE `User` SET nickname = ? WHERE uid = ?")) {
            pstmt.setString(1, nickname);
            pstmt.setInt(2, uid);
            pstmt.executeUpdate();
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUserAvatarUrl(int uid, String avatarUrl) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE `User` SET avatar_url = ? WHERE uid = ?")) {
            pstmt.setString(1, avatarUrl);
            pstmt.setInt(2, uid);
            pstmt.executeUpdate();
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUserPassword(int uid, String password) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE `User` SET password = ? WHERE uid = ?")) {
            pstmt.setString(1, password);
            pstmt.setInt(2, uid);
            pstmt.executeUpdate();
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //-- 用户登陆
    public boolean login(String email, String password) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT * FROM `User` WHERE email = ? AND password = ?")) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                rs.close(); // 释放资源
                pstmt.close(); // 释放资源
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return false;
    }

    //-- 用户获取主页新闻列表
    //-- Example:
    //SELECT
    //    news_id,
    //    title,
    //    cover_image_url,
    //    publish_datetime,
    //    author,
    //    views_count,
    //    favorites_count,
    //    shares_count
    //FROM `Content`
    //ORDER BY publish_datetime DESC
    //LIMIT 0, 10;
    public List<NewsItem> fetchLatestNews(int pageIndex, int showNum) throws SQLException {
        List<NewsItem> newsList = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT news_id, title, cover_image_url, publish_datetime, author, views_count, favorites_count, shares_count FROM Content ORDER BY publish_datetime DESC LIMIT ?, ?")) {
            pstmt.setInt(1, (pageIndex - 1) * showNum);
            pstmt.setInt(2, showNum);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                NewsItem newsItem = new NewsItem();
                newsItem.setNewsId(rs.getInt("news_id"));
                newsItem.setTitle(rs.getString("title"));
                newsItem.setCoverImageUrl(rs.getString("cover_image_url"));
                newsItem.setPublishDate(rs.getDate("publish_datetime"));
                newsItem.setAuthor(rs.getString("author"));
                newsItem.setViewsCount(rs.getInt("views_count"));
                newsItem.setFavoritesCount(rs.getInt("favorites_count"));
                newsItem.setSharesCount(rs.getInt("shares_count"));
                newsList.add(newsItem);
            }
            rs.close(); // 释放资源
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newsList;
    }

    //-- ·用户按分区浏览新闻列表
    //-- Example:
    //    SELECT
    //            news_id,
    //            title,
    //            cover_image_url,
    //            publish_datetime,
    //            author,
    //            views_count,
    //            favorites_count,
    //            shares_count
    //    FROM `Content`
    //    WHERE category_id = 1
    //    ORDER BY publish_datetime DESC
    //    LIMIT 0, 10;
    public List<NewsItem> fetchNewsByCategoryId(int categoryId, int pageIndex, int showNum) throws SQLException {
        List<NewsItem> newsList = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT news_id, title, cover_image_url, publish_datetime, author, views_count, favorites_count, shares_count FROM Content WHERE category_id = ? ORDER BY publish_datetime DESC LIMIT ?, ?")) {
            pstmt.setInt(1, categoryId);
            pstmt.setInt(2, (pageIndex - 1) * showNum);
            pstmt.setInt(3, showNum);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                NewsItem newsItem = new NewsItem();
                newsItem.setNewsId(rs.getInt("news_id"));
                newsItem.setTitle(rs.getString("title"));
                newsItem.setCoverImageUrl(rs.getString("cover_image_url"));
                newsItem.setPublishDate(rs.getDate("publish_datetime"));
                newsItem.setAuthor(rs.getString("author"));
                newsItem.setViewsCount(rs.getInt("views_count"));
                newsItem.setFavoritesCount(rs.getInt("favorites_count"));
                newsItem.setSharesCount(rs.getInt("shares_count"));
                newsList.add(newsItem);
            }
            rs.close(); // 释放资源
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newsList;
    }

    //-- ·用户按标题和内容搜索新闻列表
    //-- Example:
    //    SELECT
    //            news_id,
    //            title,
    //            cover_image_url,
    //            publish_date,
    //            author,
    //            views_count,
    //            favorites_count,
    //            shares_count
    //    FROM `Content`
    //    WHERE
    //    title LIKE '新闻标题%'
    //    OR body LIKE '新闻内容%'
    //    ORDER BY publish_datetime DESC
    //    LIMIT 0, 10;
    public List<NewsItem> fetchNewsByKeyword(String titleKeyword, String bodyKeyword, int pageIndex, int showNum) throws SQLException {
        List<NewsItem> newsList = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT news_id, title, cover_image_url, publish_datetime, author, views_count, favorites_count, shares_count FROM Content WHERE title LIKE ? OR body LIKE ? ORDER BY publish_datetime DESC LIMIT ?, ?")) {
            pstmt.setString(1, titleKeyword + "%");
            pstmt.setString(2, bodyKeyword + "%");
            pstmt.setInt(3, (pageIndex - 1) * showNum);
            pstmt.setInt(4, showNum);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                NewsItem newsItem = new NewsItem();
                newsItem.setNewsId(rs.getInt("news_id"));
                newsItem.setTitle(rs.getString("title"));
                newsItem.setCoverImageUrl(rs.getString("cover_image_url"));
                newsItem.setPublishDate(rs.getDate("publish_datetime"));
                newsItem.setAuthor(rs.getString("author"));
                newsItem.setViewsCount(rs.getInt("views_count"));
                newsItem.setFavoritesCount(rs.getInt("favorites_count"));
                newsItem.setSharesCount(rs.getInt("shares_count"));
                newsList.add(newsItem);
            }
            rs.close(); // 释放资源
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newsList;
    }

    //-- ·用户访问新闻，并且加载新闻下对应的评论
    //-- Example:
    //    SELECT
    //            news_id,
    //            title,
    //            cover_image_url,
    //            publish_datetime,
    //            author,
    //            views_count,
    //            favorites_count,
    //            shares_count,
    //            body
    //    FROM `Content`
    //    WHERE news_id = 1;
    //-- 获取新闻
    //    SELECT * FROM `Comment` WHERE news_id = 1;
    //-- 获取评论
    public NewsItem fetchNewsById(int newsId) throws SQLException {
        NewsItem newsItem = new NewsItem();

        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT news_id, title, cover_image_url, publish_datetime, author, views_count, favorites_count, shares_count, body FROM Content WHERE news_id = ?")) {
            pstmt.setInt(1, newsId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                newsItem.setNewsId(rs.getInt("news_id"));
                newsItem.setTitle(rs.getString("title"));
                newsItem.setCoverImageUrl(rs.getString("cover_image_url"));
                newsItem.setPublishDate(rs.getDate("publish_datetime"));
                newsItem.setAuthor(rs.getString("author"));
                newsItem.setViewsCount(rs.getInt("views_count"));
                newsItem.setFavoritesCount(rs.getInt("favorites_count"));
                newsItem.setSharesCount(rs.getInt("shares_count"));
                newsItem.setBody(rs.getString("body"));
            }
            rs.close(); // 释放资源
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newsItem;
    }

    public Comment fetchCommentByNewsId(int newsId) throws SQLException {
        Comment comment = new Comment();

        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT * FROM Comment WHERE news_id = ?")) {
            pstmt.setInt(1, newsId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                comment.setCommentId(rs.getInt("comment_id"));
                comment.setNewsId(rs.getInt("news_id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setContent(rs.getString("content"));
                comment.setRepliedCommentId(rs.getInt("replied_comment_id"));
                comment.setUpvotes(rs.getInt("upvotes"));
                comment.setDownvotes(rs.getInt("downvotes"));
                comment.setCommentTime(rs.getDate("comment_time"));
            }
            rs.close(); // 释放资源
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comment;
    }

    //-- ·用户给新闻进行收藏、分享
    //-- Example:
    //    UPDATE USER
    //    SET
    //            favorites = JSON_ARRAY_APPEND(favorites, '$', 1)
    //    WHERE uid = 1;
    //-- 用户收藏新闻
    //    UPDATE `Content`
    //    SET
    //            favorites_count = favorites_count + 1
    //    WHERE news_id = 1;
    //-- 新闻收藏数+1
    //    UPDATE `Content`
    //    SET
    //            shares_count = shares_count + 1
    //    WHERE news_id = 1;
    //-- 新闻转发数+1
    public void favoriteNews(int uid, int newsId) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE USER SET favorites = JSON_ARRAY_APPEND(favorites, '$', ?) WHERE uid = ?")) {
            pstmt.setInt(1, newsId);
            pstmt.setInt(2, uid);
            pstmt.executeUpdate();
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE `Content` SET favorites_count = favorites_count + 1 WHERE news_id = ?")) {
            pstmt.setInt(1, newsId);
            pstmt.executeUpdate();
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void shareNews(int newsId) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE `Content` SET shares_count = shares_count + 1 WHERE news_id = ?")) {
            pstmt.setInt(1, newsId);
            pstmt.executeUpdate();
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //-- ·用户对新闻评论
    //-- Example:
    //    INSERT INTO
    //    `Comment` (
    //    user_id,
    //    news_id,
    //    content,
    //    )
    //    VALUES (1, 1, '评论内容');
    public void commentNews(Comment comment) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO `Comment` (user_id, news_id, content) VALUES (?, ?, ?)")) {
            pstmt.setInt(1, comment.getUserId());
            pstmt.setInt(2, comment.getNewsId());
            pstmt.setString(3, comment.getContent());
            pstmt.executeUpdate();
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //-- ·用户对评论进行点赞/点踩
    //-- Example:
    //    UPDATE `Comment` SET upvotes = upvotes + 1 WHERE comment_id = 1;
    //-- 点赞
    //    UPDATE `Comment` SET downvotes = downvotes + 1 WHERE comment_id = 1;
    //-- 点踩
    public void upvoteComment(int commentId) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE `Comment` SET upvotes = upvotes + 1 WHERE comment_id = ?")) {
            pstmt.setInt(1, commentId);
            pstmt.executeUpdate();
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void downvoteComment(int commentId) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE `Comment` SET downvotes = downvotes + 1 WHERE comment_id = ?")) {
            pstmt.setInt(1, commentId);
            pstmt.executeUpdate();
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //-- ·用户删除自己的评论
    //-- Example:
    //    DELETE FROM `Comment` WHERE comment_id = 1 AND user_id = 1;
    public void deleteComment(int commentId, int uid) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM `Comment` WHERE comment_id = ? AND user_id = ?")) {
            pstmt.setInt(1, commentId);
            pstmt.setInt(2, uid);
            pstmt.executeUpdate();
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //-- ·用户在个人页中观看自己的评论历史
    //-- Example:
    //    SELECT
    //            content,
    //            comment_time,
    //            upvotes,
    //            downvotes
    //    FROM `Comment`
    //    JOIN `CommentHistory` USING (comment_id)
    //    WHERE uid = 1;
    public List<Comment> fetchCommentHistory(int uid) throws SQLException {
        List<Comment> commentList = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT content, comment_time, upvotes, downvotes FROM `Comment` JOIN `CommentHistory` USING (comment_id) WHERE uid = ?")) {
            pstmt.setInt(1, uid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setContent(rs.getString("content"));
                comment.setCommentTime(rs.getDate("comment_time"));
                comment.setUpvotes(rs.getInt("upvotes"));
                comment.setDownvotes(rs.getInt("downvotes"));
                commentList.add(comment);
            }
            rs.close(); // 释放资源
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commentList;
    }

    //-- ·用户在个人页中观看自己的浏览历史
    //-- Example:
    //    SELECT
    //            title,
    //            cover_image_url,
    //            publish_datetime,
    //            author,
    //            views_count,
    //            favorites_count,
    //            shares_count,
    //            body
    //    FROM `Content`
    //    JOIN `BrowsingHistory` USING (news_id)
    //    WHERE uid = 1;
    public List<NewsItem> fetchBrowsingHistory(int uid) throws SQLException {
        List<NewsItem> newsList = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT title, cover_image_url, publish_datetime, author, views_count, favorites_count, shares_count, body FROM `Content` JOIN `BrowsingHistory` USING (news_id) WHERE uid = ?")) {
            pstmt.setInt(1, uid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                NewsItem newsItem = new NewsItem();
                newsItem.setTitle(rs.getString("title"));
                newsItem.setCoverImageUrl(rs.getString("cover_image_url"));
                newsItem.setPublishDate(rs.getDate("publish_datetime"));
                newsItem.setAuthor(rs.getString("author"));
                newsItem.setViewsCount(rs.getInt("views_count"));
                newsItem.setFavoritesCount(rs.getInt("favorites_count"));
                newsItem.setSharesCount(rs.getInt("shares_count"));
                newsItem.setBody(rs.getString("body"));
                newsList.add(newsItem);
            }
            rs.close(); // 释放资源
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newsList;
    }

    //-- ·（模拟）投放新闻
    //-- Example:
    //    INSERT INTO
    //    `Content` (
    //    title,
    //    cover_image_url,
    //    publish_datetime,
    //    category_id,
    //    author,
    //    body,
    //    sponsored
    //    )
    //    VALUES (
    //        '新闻标题',
    //                'http://www.example.com/cover_image.jpeg',
    //        NOW(),
    //        1,
    //                '新闻作者',
    //                '新闻正文',
    //                0
    //                );
    public void insertNews(NewsItem newsItem) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO `Content` (title, cover_image_url, publish_datetime, category_id, author, body, sponsored) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            pstmt.setString(1, newsItem.getTitle());
            pstmt.setString(2, newsItem.getCoverImageUrl());
            pstmt.setDate(3, newsItem.getPublishDate());
            pstmt.setInt(4, newsItem.getCategoryId());
            pstmt.setString(5, newsItem.getAuthor());
            pstmt.setString(6, newsItem.getBody());
            pstmt.setBoolean(7, newsItem.isSponsored());
            pstmt.executeUpdate();
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //-- ·按照内容类型获取广告
    //-- Example:
    //    SELECT *
    //    FROM `Advertisements`
    //    WHERE placement = '首页列表内嵌广告'
    //    ORDER BY priority DESC
    //    LIMIT 0, 10;
    public List<Advertisement> fetchAdvertisementsByPlacement(String placement, int pageIndex, int showNum) throws SQLException {
        List<Advertisement> advertisementList = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(
                "SELECT * FROM `Advertisement` WHERE placement = ? ORDER BY priority DESC LIMIT ?, ?")) {
            pstmt.setString(1, placement);
            pstmt.setInt(2, (pageIndex - 1) * showNum);
            pstmt.setInt(3, showNum);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Advertisement advertisement = new Advertisement();
                advertisement.setAdId(rs.getInt("ad_id"));
                advertisement.setTitle(rs.getString("title"));
                advertisement.setTextContent(rs.getString("text_content"));
                advertisement.setImageUrl(rs.getString("image_url"));
                advertisement.setPlacement(rs.getString("placement"));
                advertisement.setAdvertiser(rs.getString("advertiser"));
                advertisement.setValidityPeriodStart(rs.getDate("validity_period_start"));
                advertisement.setValidityPeriodEnd(rs.getDate("validity_period_end"));
                String region_limit = rs.getString("region_limit");
                if (region_limit != null) {
                    advertisement.setRegionLimit(region_limit);
                }else {
                    advertisement.setRegionLimit(null);
                }
                String device_limit = rs.getString("device_limit");
                if (device_limit != null) {
                    advertisement.setDeviceLimit(device_limit);
                }else {
                    advertisement.setDeviceLimit(null);
                }
                advertisement.setClicks(rs.getInt("clicks"));
                advertisement.setPriority(rs.getInt("priority"));
                advertisementList.add(advertisement);
            }
            rs.close(); // 释放资源
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return advertisementList;
    }

    public void close() throws SQLException {
        // 释放资源
        conn.close();
    }

    //-- ·投放广告
    //-- Example:
    //    INSERT INTO
    //    `Advertisements` (
    //    title,
    //    text_content,
    //    image_url,
    //    placement,
    //    advertiser,
    //    validity_period_start,
    //    validity_period_end,
    //    priority
    //    )
    //    VALUES (
    //        '广告标题',
    //                '广告文字内容',
    //                'http://www.example.com/image.jpeg',
    //                '首页列表内嵌广告',
    //                '广告主',
    //        NOW(),
    //    NOW() + INTERVAL 1 MONTH,
    //            1
    //            );
    public void insertAdvertisement(Advertisement advertisement) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO `Advertisement` (title, text_content, image_url, placement, advertiser, validity_period_start, validity_period_end, priority) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            pstmt.setString(1, advertisement.getTitle());
            pstmt.setString(2, advertisement.getTextContent());
            pstmt.setString(3, advertisement.getImageUrl());
            pstmt.setString(4, advertisement.getPlacement());
            pstmt.setString(5, advertisement.getAdvertiser());
            pstmt.setDate(6, advertisement.getValidityPeriodStart());
            pstmt.setDate(7, advertisement.getValidityPeriodEnd());
            pstmt.setInt(8, advertisement.getPriority());
            pstmt.executeUpdate();
            pstmt.close(); // 释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}