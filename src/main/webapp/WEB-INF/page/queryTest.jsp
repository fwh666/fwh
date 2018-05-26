<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fwh
  Date: 2018/5/26
  Time: 下午6:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
姓名：${peopleTest.name}<br/>
年龄：${peopleTest.age}<br/>
<br>
<table bgcolor="#f0f8ff" border="1">
    <tr>
        <td>姓名</td>
        <td>年龄</td>
    </tr>
    <tr>
        <td>${peopleTest.name}</td>
        <td>${peopleTest.age}</td>
    </tr>
</table>
<br>
<table bgcolor="9999dd" border="1">
    <tr>
        <td>第一行第一列</td>
        <td>第一行第二列</td>
    </tr>
    <tr>
        <td>第二行第一列</td>
        <td>第二行第二列</td>
    </tr>
</table>
<a href="/index.jsp">返回</a>
</body>
</html>
