<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: soul
  Date: 7/6/20
  Time: 6:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Article</title>
</head>
<body>
<form:form method="post" modelAttribute="article">
    <div>
        <label for="title">Title</label>
        <form:input id="title" path="title"/>
    </div>
    <div>
        <label for="content">Content</label>
        <form:textarea  id="content" path="content"/>
    </div>
    <div>
        <label for="author">Author</label>
        <form:select id="author" path="author" items="${authors}" itemLabel="fullName" itemValue="id"/>
    </div>
    <div>
        <label for="category">Category</label>
        <form:select id="category" path="categories" items="${categories}" itemLabel="name" itemValue="id"/>
    </div>
    <div>
        <input type="submit">
    </div>
</form:form>

</body>
</html>
