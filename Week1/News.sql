-- Active: 1705468269848@@127.0.0.1@3306@uic
DROP DATABASE IF EXISTS news;

CREATE DATABASE
    news DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE news;

-- create user table
CREATE TABLE
    `User` (
        uid INT AUTO_INCREMENT PRIMARY KEY,
        -- 用户ID
        mobile_number VARCHAR(255),
        -- 绑定的手机号
        email VARCHAR(255) NOT NULL UNIQUE,
        -- 绑定的邮箱号
        password VARCHAR(255) NOT NULL,
        -- 登录密码
        avatar_url VARCHAR(255),
        -- 用户头像URL
        nickname VARCHAR(255) NOT NULL,
        -- 昵称
        favorites JSON,
        -- 收藏列表
        news_preferences JSON,
        -- 新闻偏好
        last_login_time DATETIME -- 最后一次登录时间
    );
-- create news table
CREATE TABLE
    `Content` (
        news_id INT AUTO_INCREMENT PRIMARY KEY,
        -- 新闻ID
        title VARCHAR(255) NOT NULL,
        -- 标题
        cover_image_url VARCHAR(255),
        -- 封面URL
        publish_datetime DATETIME NOT NULL,
        -- 投放日期时间
        category_id INT NOT NULL,
        -- 分区ID
        author VARCHAR(255) NOT NULL,
        -- 作者
        body TEXT NOT NULL,
        -- 新闻正文
        views_count INT DEFAULT 0,
        -- 浏览量
        favorites_count INT DEFAULT 0,
        -- 收藏量
        shares_count INT DEFAULT 0,
        -- 转发量
        sponsored BOOLEAN DEFAULT 0 
        -- 付费推广标记
    );

-- create news category table
CREATE TABLE
    `Category` (
        category_id INT AUTO_INCREMENT PRIMARY KEY,
        -- 分区ID
        name VARCHAR(255) NOT NULL,
        -- 分区名称
        parent_category_id INT,
        -- 父分区ID
        FOREIGN KEY (parent_category_id) REFERENCES `Category`(category_id)
    );
-- create comment table
CREATE TABLE
    `Comment` (
        comment_id INT AUTO_INCREMENT PRIMARY KEY,
        -- 评论ID
        user_id INT NOT NULL,
        -- 评论者ID
        news_id INT NOT NULL,
        -- 新闻ID
        content TEXT NOT NULL,
        -- 评论内容
        comment_time DATETIME DEFAULT NOW(),
        -- 评论时间
        replied_comment_id INT,
        -- 被回复的评论ID
        upvotes INT DEFAULT 0,
        -- 点赞数
        downvotes INT DEFAULT 0,
        -- 点踩数
        FOREIGN KEY (user_id) REFERENCES `User`(uid),
        FOREIGN KEY (news_id) REFERENCES `Content`(news_id),
        FOREIGN KEY (replied_comment_id) REFERENCES `Comment`(comment_id)
    );
-- create ad table
CREATE TABLE
    Advertisement (
        ad_id INT AUTO_INCREMENT PRIMARY KEY,
        -- 广告ID
        title VARCHAR(255) NOT NULL,
        -- 标题
        text_content TEXT NOT NULL,
        -- 文字内容
        image_url VARCHAR(255),
        -- 图片URL
        placement ENUM(
            -- 广告投放位置
            'splash',
            '弹窗',
            '首页列表内嵌广告',
            '评论内嵌广告',
            '新闻文章内嵌广告',
            '新闻文章末尾广告',
            '相关新闻推荐内嵌广告'
        ) NOT NULL,
        advertiser VARCHAR(255) NOT NULL,
        -- 投放方（广告主）
        validity_period_start DATE,
        -- 有效期开始
        validity_period_end DATE,
        -- 有效期结束
        region_limit JSON,
        -- 地区限制
        device_limit JSON,
        -- 设备限制
        clicks INT DEFAULT 0,
        -- 点击量
        impressions INT DEFAULT 0,
        -- 曝光量量
        priority INT DEFAULT 0 -- 投放权重
    );
-- create browsing history table
CREATE TABLE
    BrowsingHistory (
        id INT AUTO_INCREMENT PRIMARY KEY,
        -- 浏览历史ID
        uid INT NOT NULL,
        -- 用户ID
        news_id INT NOT NULL,
        -- 新闻ID
        created_at DATETIME DEFAULT NOW(),
        -- 浏览时间
        FOREIGN KEY (uid) REFERENCES User(uid),
        FOREIGN KEY (news_id) REFERENCES Content(news_id)
    );
-- create comment history table
CREATE TABLE
    CommentHistory (
        id INT AUTO_INCREMENT PRIMARY KEY,
        -- 评论历史ID
        uid INT NOT NULL,
        -- 用户ID
        comment_id INT NOT NULL,
        -- 评论ID
        created_at DATETIME DEFAULT NOW(),
        -- 评论时间
        FOREIGN KEY (uid) REFERENCES User(uid),
        FOREIGN KEY (comment_id) REFERENCES Comment(comment_id)
    );