DROP TABLE IF EXISTS ad;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS news;
DROP TABLE IF EXISTS user;

CREATE TABLE news
(
    id BIGINT NOT NULL COMMENT '主键ID',
    title VARCHAR(50) NULL DEFAULT NULL COMMENT '标题',
    type VARCHAR(20) NULL DEFAULT NULL COMMENT '类型',
    content TEXT NULL DEFAULT NULL COMMENT '内容',
    PRIMARY KEY (id)
);

CREATE TABLE comment
(
    id BIGINT NOT NULL COMMENT '主键ID',
    nid BIGINT NULL DEFAULT NULL COMMENT '新闻ID',
    content TEXT NULL DEFAULT NULL COMMENT '内容',
    PRIMARY KEY (id),
    CONSTRAINT fk_comment_news
        FOREIGN KEY (nid) REFERENCES news (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE ad
(
    id BIGINT NOT NULL COMMENT '主键ID',
    type VARCHAR(20) NULL DEFAULT NULL COMMENT '类型',
    url VARCHAR(100) NULL DEFAULT NULL COMMENT '链接',
    content TEXT NULL DEFAULT NULL COMMENT '内容',
    PRIMARY KEY (id)
);

CREATE TABLE user
(
    id BIGINT NOT NULL COMMENT '主键ID',
    username VARCHAR(20) NULL DEFAULT NULL COMMENT '用户名',
    role VARCHAR(20) NULL DEFAULT NULL COMMENT '权限',
    PRIMARY KEY (id)
);