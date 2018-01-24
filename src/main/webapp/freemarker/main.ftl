<html>
<head>
    <title>新闻首页</title>
</head>
<body>
    <#list newsArray as news>
    <li>
        <a href="/freemarker/news/${news.id}">${news.title}</a>
    </li>
    </#list>
</body>
</html>