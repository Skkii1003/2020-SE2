### 项目说明

这是 courselearning 后端项目，使用了MySQL数据库。

本项目默认使用的 MySQL 用户为 `root` 密码为 `123456`
> 相关配置可参考文件 /src/main/resouces/application.yml `spring.datasource` 字段
> 请根据实际情况进行调整

项目部署前：
> 请使用 /sql/courselearning.sql 内的脚本初始化数据库和表
（可以直接打开MySQL命令行界面，在登录用户后，将 courselearning.sql 文件拷贝到命令行后按下回车）
>
> application.yml 文件中的 `web.file-upload-path` 字段为自定义的用于上传文件的目录路径，请根据实际情况进行调整
（建议部署前先创建目录专门用于存放上传的文件）

本地热部署：可以使用 IntelliJ IDEA 打开本项目，然后点击 Run 'CourseLearningApplication' 。

服务器部署：首先配置好服务器环境（JDK1.8 + MySQL），使用 maven 打包命令生成 jar 包，通过 shell 命令运行。

#### 部分数据结构说明

项目中使用了 com.github.pagehelper 分页插件，其中 PageInfo 重要的属性如下所示：
```java
public class PageInfo<T> {
    // 当前页码（无论请求中传入的页码数有多大，在返回结果中，pageNum 都是不超过总页数的）
    private int pageNum;
    // 每页包含的最大记录数
    private int pageSize;
    // 当前页包含的记录数
    private int size;
    // 总记录数
    private long total;
    // 总页数
    private int pages;
    // 结果集
    private List<T> list;
    ...
}
```


#### 文件说明

SpringBoot 的配置文件可参考 /src/main/resouces/application.yml 以及 代码部分 /src/main/java/cn.seecoder.courselearning/config/*，请根据实际情况调整。
> 目前所有 controller 暴露的 API 都需要添加 /api 前缀才能正常访问，可参考 cn.seecoder.courselearning/config/WebResourceConfig 中 `configurePathMatch` 方法

/src/main/resouces/static 目录下存储了部分课程的图片文件，建议可以将static目录内的图片文件全部拷贝至服务器上传文件的目录（在application.yml定义的）。

/sql/courselearning.sql 存储了项目初始化需要的sql文件（包含了用于展示的部分数据），初次使用时，可将该文件内的所有sql语句在mysql命令行内执行后初始化数据库。