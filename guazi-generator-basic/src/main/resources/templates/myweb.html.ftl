<!DOCTYPE html>
<html>
<head>
    <title>瓜子官网</title>
</head>
<body>
<h1>欢迎来到瓜子官网！</h1>
<ul>
    <#--循环渲染导航条 -->
    <#list menuItems as item>
        <li><a href="${item.url}">${item.label}</a> </li>
    </#list>
</ul>
<#--页脚 -->
<footer>
    ${currentYear} 瓜子官网. All rights reserved.
</footer>
</body>
</html>