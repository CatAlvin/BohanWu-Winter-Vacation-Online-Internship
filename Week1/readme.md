# 工程文件介绍
## 1. 项目结构
- `News[Path]`: SQL任务2 SQL指令迁移到JDBC的实现项目
- `P2PChatSystem[Path]`: Socket任务 P2P聊天系统的实现项目
- `P2PMultithreadingFramework[Path]`: Socket任务 P2P聊天系统的多线程框架尝试
- `News.sql[File]`: SQL任务 SQL指令创建news数据库及其数据表
- `OperationEX.sql[File]`: SQL任务1 SQL指令实现CRUD基础功能
- `README.md[File]`: 项目介绍文档

## 2. 项目解释
### SQL任务
#### 建表任务
- 借鉴了网络部分项目的数据表设计思路，除了要求的4张表和特征外，对数据库进行了bcnf范式化设计，使得表结构更加合理
- 可直接运行`News.sql`文件，创建数据库及其数据表
#### 任务1
- 对于CRUD基础功能的实现，每种操作进行了SQL语句的举例
#### 任务2
- 对于SQL指令的迁移，使用了JDBC的方式，实现了对数据库的增删改查操作，将数据库操作封装到了类中，以供调用
- jar包相对路径为`./News/out/artifacts/News_jar/News.jar`运行后为示例操作，jar包可以做模块使用，调用`NewsRepository`类中的方法即可实现对应功能
### Socket任务
#### P2P聊天系统
- 项目中的`P2PChatSystem`文件夹下为P2P聊天系统的实现项目，实现了基本的P2P聊天功能，文本互传功能
- jar包相对路径为`./P2PChatSystem/out/artifacts/P2PChatSystem_jar/P2PChatSystem.jar`可直接运行
- 此外，对多线程版本的P2P聊天系统进行了尝试，并实现了基础的多线程框架

# 对于SQL任务进阶要求的思考
- 进阶：评论表的内容适合使用非关系型数据库进行存储，推荐同时使用MongoDB进行配合维护
## 对于进阶要求好处的思考
- MongoDB是非关系型数据库，它的文档模型允许灵活地存储和更新不同的数据结构，非常适合于评论这类结构不固定的数据
- 因为评论表的内容是不固定的，所以使用MongoDB可以更好地存储和更新这类数据，而且MongoDB的查询速度非常快，可以更好地提高查询效率
- 评论表是存在嵌套关系的，而MongoDB的文档模型可以很好地存储嵌套关系的数据，所以使用MongoDB可以更好地存储这类数据
## 对于进阶要求的实现思路
- 评论数据模型：
  - 基本字段：用户ID、评论ID、新闻ID、评论内容、评论时间等
  - 嵌套结构：对于回复评论，可以作为子文档嵌套在原评论文档中
- 结构示例：
```json
{
    "user_id": "",
    "comment_id": "",
    "news_id": "",
    "content": "",
    "time": "",
    "replied_comment": [
        {
            "user_id": "",
            "comment_id": "",
            "news_id": "",
            "content": "",
            "time": ""
        },
        {
            "user_id": "",
            "comment_id": "",
            "news_id": "",
            "content": "",
            "time": ""
        }
    ]
}
```
- 对于后端的实现要求：
  - 要保证评论表的数据与MongoDB中的数据同步，保证一致性和完整性
  - 也可以试着将评论表的格式化数据的部分存储在MySQL中，以便于查询，但对数据一致性要求更高