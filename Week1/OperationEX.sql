-- ·通过邮箱创建用户
-- Example:
INSERT INTO
    `User` (email, password, nickname)
VALUES (
        'chenglancat@163.com',
        '123456',
        'chenglancat'
    );


-- ·用户编辑自己的昵称等账号信息
-- Example:
UPDATE `User` SET nickname = 'chenglancat' WHERE uid = 1;
-- 用户修改昵称
UPDATE `User`
SET
    avatar_url = 'http://www.example.com/avatar.jpeg'
WHERE uid = 1;
-- 用户修改头像
UPDATE `User` SET password = '12345678' WHERE uid = 1;
-- 用户修改密码


-- 用户获取主页新闻列表
-- Example:
SELECT
    news_id,
    title,
    cover_image_url,
    publish_datetime,
    author,
    views_count,
    favorites_count,
    shares_count
FROM `Content`
ORDER BY publish_datetime DESC
LIMIT 0, 10;


-- ·用户按分区浏览新闻列表
-- Example:
SELECT
    news_id,
    title,
    cover_image_url,
    publish_datetime,
    author,
    views_count,
    favorites_count,
    shares_count
FROM `Content`
WHERE category_id = 1
ORDER BY publish_datetime DESC
LIMIT 0, 10;


-- ·用户按标题和内容搜索新闻列表
-- Example:
SELECT
    news_id,
    title,
    cover_image_url,
    publish_date,
    author,
    views_count,
    favorites_count,
    shares_count
FROM `Content`
WHERE
    title LIKE '新闻标题%'
    OR body LIKE '新闻内容%'
ORDER BY publish_datetime DESC
LIMIT 0, 10;


-- ·用户访问新闻，并且加载新闻下对应的评论
-- Example:
SELECT
    news_id,
    title,
    cover_image_url,
    publish_datetime,
    author,
    views_count,
    favorites_count,
    shares_count,
    body
FROM `Content`
WHERE news_id = 1;
-- 获取新闻
SELECT * FROM `Comment` WHERE news_id = 1;
-- 获取评论


-- ·用户给新闻进行收藏、分享
-- Example:
UPDATE USER
SET
    favorites = JSON_ARRAY_APPEND(favorites, '$', 1)
WHERE uid = 1;
-- 用户收藏新闻
UPDATE `Content`
SET
    favorites_count = favorites_count + 1
WHERE news_id = 1;
-- 新闻收藏数+1
UPDATE `Content`
SET
    shares_count = shares_count + 1
WHERE news_id = 1;
-- 新闻转发数+1


-- ·用户对新闻评论
-- Example:
INSERT INTO
    `Comment` (
        user_id,
        news_id,
        content
    )
VALUES (1, 1, '评论内容');


-- ·用户对评论进行点赞/点踩
-- Example:
UPDATE `Comment` SET upvotes = upvotes + 1 WHERE comment_id = 1;
-- 点赞
UPDATE `Comment` SET downvotes = downvotes + 1 WHERE comment_id = 1;
-- 点踩


-- ·用户删除自己的评论
-- Example:
DELETE FROM `Comment` WHERE comment_id = 1 AND user_id = 1;


-- ·用户在个人页中观看自己的评论历史
-- Example:
SELECT
    content,
    comment_time,
    upvotes,
    downvotes
FROM `Comment`
    JOIN `CommentHistory` USING (comment_id)
WHERE uid = 1;


-- ·用户在个人页中观看自己的浏览历史
-- Example:
SELECT
    title,
    cover_image_url,
    publish_datetime,
    author,
    views_count,
    favorites_count,
    shares_count,
    body
FROM `Content`
    JOIN `BrowsingHistory` USING (news_id)
WHERE uid = 1;


-- ·（模拟）投放新闻
-- Example:
INSERT INTO
    `Content` (
        title,
        cover_image_url,
        publish_datetime,
        category_id,
        author,
        body,
        sponsored
    )
VALUES (
        '新闻标题',
        'http://www.example.com/cover_image.jpeg',
        NOW(),
        1,
        '新闻作者',
        '新闻正文',
        0
    );


-- ·按照内容类型获取广告
-- Example:
SELECT *
FROM `Advertisement`
WHERE placement = '首页列表内嵌广告'
ORDER BY priority DESC
LIMIT 0, 10;


-- ·投放广告
-- Example:
INSERT INTO
    `Advertisement` (
        title,
        text_content,
        image_url,
        placement,
        advertiser,
        validity_period_start,
        validity_period_end,
        priority
    )
VALUES (
        '广告标题',
        '广告文字内容',
        'http://www.example.com/image.jpeg',
        '首页列表内嵌广告',
        '广告主',
        NOW(),
        NOW() + INTERVAL 1 MONTH,
        1
    );