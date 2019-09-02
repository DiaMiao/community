### 社区项目

## 资料
[参照网页（需求）](https://elasticsearch.cn/explore)  
[Springboot官方教程](https://spring.io/guides)  
[Springboot 基本web组件](https://spring.io/guides/gs/serving-web-content/)  
[bootstrap 文档](https://getbootstrap.com/docs/4.3/getting-started/introduction/)  
[github OAuth 文档](https://developer.github.com/apps/building-github-apps/)  
[MySQL基本语法](http://www.runoob.com/mysql/mysql-tutorial.html)  
[Maven仓库依赖查询](https://mvnrepository.com/)  
[springboot 文档](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html)  

## 工具
[visual-paradigm画图工具](https://online.visual-paradigm.com)  
[OkHttp处理jason数据请求工具](https://square.github.io/okhttp/)  
[Mybatis Springboot 文档](http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)  
[flyway 处理不同数据库版本](https://flywaydb.org/getstarted/why)：  
    每次的修改都创建新的migration,之后执行mvn flyway:migrate  
[Icon使用](https://fontawesome.com/icons)直接复制html引用即可使用
## 常用命令
1. git 常用命令
    
    git status 查看未提交文件状态      
    git add . 添加当前文件目录所有文件到暂存中      
    git commit -m "xxxx" 提交并备注      
    git commit --amend --no-edit 追加之前的文件（相当于编辑修改之前的提交）    
    git push 推送新代码  
    
    
2. Intellij 快捷键整理
    1. 格式化代码：ctrl+alt+L
    2. 编码窗口最大化/恢复： ctrl+shift+F12
    3. 全局文件搜索：ctrl+shift+N 或 按两次shift
    4. 提示参数：ctrl+P
    5. 自动创建setter和getter ：alt+insert
    6. 自动引入包/弹出提示：Alt+Enter
    7. 快速创建变量：ctrl+alt+V
    8. 自动换行并把光标移至最前：shift+Enter
    9. 自动输出System.out.println():sout+enter/tab
    10. 快速将变量放入原文中 ctrl+alt+N
    11. 切换窗口：最近打开过的窗口ctrl+E， 已经打开的窗口ctrl+Tab
    12. 选中：ctrl+w 持续按w范围从小 到大,按ctrl+shift+w，持续按w范围变小
    13. 复制并粘贴选中内容：ctrl+D
    14. 重命名变量：shift+F6
    15. 自动移除无用import: ctrl + alt + o
    16. 对于数组对象，可以用.for方式来生成for each循环
    
3. Github readme.md 文档书写
    1.[Markdown Cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet) 
    2. 换行：两个空格+回车  
    3. 标题：井号
    4. 链接：方括号内文本，圆括号内网址
    5. 添加代码：\``` xxx （换行）代码正文\``` 以某中语言格式显示
    
## 基本概念
1. Bootstrap 最简单的前端 UI 框架

    1. 字体图标：常用图标均为字体格式，相比图片加快了加载速度
    2. 已包含基本的网页常用组件，便于快速开发
    3. 响应式设计：基于栅格系统，可根据屏幕尺寸定义不同的css样式

2. session 和 cookie  
    session 银行账户， cookie 银行卡(记住状态)， 服务器(domain)--银行    
    
    网页cookie存储信息  
    f12-application-cookie查看。每个cookie都有过期时间。  
    f12-network 渲染网页时，每条请求服务器的记录都会出现在这里。每次请求时，会在cookie中拿到相关信息，从而记住数据而不用每次都输入。
    session 通过传入HttpServletRequest类型的对象来操作它（get,set等。  
    web页面中，通过session可以获取页面登录状态
    
3. database
    1. 此处使用h2轻量数据库，用pom文件配置依赖后，可以在intelliJ直接操作。
    2. 时间戳gmt_xxx 可以记录操作的格林威治时间（毫秒），bigint类型。
    3. MyBatis  
    一个Java持久化框架，它通过XML描述符或注解把对象与存储过程或SQL语句关联起来。 


## 脚本
1.bootstrap栅格  
屏幕尺寸为lg时，占9格，小于md尺寸时占12格（即占满）
```html
<div class="col-lg-9 col-md-12"></div>
```  
