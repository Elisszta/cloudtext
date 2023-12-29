# 后端部署

## 数据库创建

需要利用mysql在部署终端中新建名为`appdb`的schema,并创建一张名为`user`的数据表,创建语句如下

```mysql
CREATE TABLE user
(
    uid int(10) primary key NOT NULL AUTO_INCREMENT,
    uname varchar(30) NOT NULL,
    password varchar(255) NOT NULL,
    UNIQUE (uname)
);
```

## 项目部署

本项目使用jdk版本为`Oracle OpenJDK 21`,使用Intellij打开项目后点击运行即可,端口为`8080`
