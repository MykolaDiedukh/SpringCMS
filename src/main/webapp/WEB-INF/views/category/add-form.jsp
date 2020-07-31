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
    <title>Add Category</title>
</head>
<body>
<form:form method="post" modelAttribute="category">
    <div>
        <label for="name">Name</label>
        <form:input id="name" path="name"/>
    </div>
    <div>
        <label for="description">Description</label>
        <form:input id="description" path="description"/>
    </div>
    <div>
        <input type="submit">
    </div>
</form:form>

</body>
</html>
