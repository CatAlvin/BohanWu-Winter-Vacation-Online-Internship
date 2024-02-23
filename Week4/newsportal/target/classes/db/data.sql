DELETE FROM ad;
DELETE FROM comment;
DELETE FROM news;

INSERT INTO news (id, title, type, content) VALUES
(1, '新闻标题1', 'national', '这是新闻1的内容'),
(2, '新闻标题2', 'international', '这是新闻2的内容'),
(3, '新闻标题3', 'national', '这是新闻3的内容'),
(4, '新闻标题4', 'international', '这是新闻4的内容'),
(5, '新闻标题5', 'national', '这是新闻5的内容');

INSERT INTO comment (id, nid, content) VALUES
(1, 1, '这是新闻1的评论1'),
(2, 1, '这是新闻1的评论2'),
(3, 2, '这是新闻2的评论1'),
(4, 2, '这是新闻2的评论2'),
(5, 3, '这是新闻3的评论1'),
(6, 3, '这是新闻3的评论2'),
(7, 4, '这是新闻4的评论1'),
(8, 4, '这是新闻4的评论2'),
(9, 5, '这是新闻5的评论1'),
(10, 5, '这是新闻5的评论2');

INSERT INTO ad (id, type, url, content) VALUES
(1, 'pop', 'http://www.baidu.com', '这是一个弹窗广告'),
(2, 'banner', 'http://www.taobao.com', '这是一个banner广告'),
(3, 'pop', 'http://www.jd.com', '这是一个弹窗广告'),
(4, 'banner', 'http://www.tmall.com', '这是一个banner广告'),
(5, 'pop', 'http://www.163.com', '这是一个弹窗广告');
