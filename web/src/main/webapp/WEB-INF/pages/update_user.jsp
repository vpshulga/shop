<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="commons/header.jsp"/>
    <title>Create user</title>
</head>
<body>
<form:errors path="*" cssClass="error" />
<form:form action="${pageContext.request.contextPath}/users/${user.id}" modelAttribute="user" method="post">
    <div class="form-group">
        <form:label path="name">First name:</form:label>
        <form:input path="name" class="form-control" placeholder="First name" />
    </div>
    <div class="form-group">
        <form:label path="surname">Last name:</form:label>
        <form:input path="surname" class="form-control" placeholder="Last name" />
    </div>
    <div class="form-group">
        <form:label type="hidden" path="email">Email:</form:label>
        <form:input type="hidden" path="email" class="form-control" placeholder="Email" />
    </div>
    <div class="form-group">
        <form:label path="password">Password:</form:label>
        <form:input path="password" class="form-control" placeholder="Password" />
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
</form:form>
</body>
</html>

