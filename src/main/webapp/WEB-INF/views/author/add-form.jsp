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
<form:form method="post" modelAttribute="author">
    <div>
        <label for="firstName">First name</label>
        <form:input id="firstName" path="firstName"/>
    </div>
    <div>
        <label for="lastName">Last name</label>
        <form:input id="lastName" path="lastName"/>
    </div>
    <div>
        <input type="submit">
    </div>
</form:form>

</body>
</html>
