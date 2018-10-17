<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Profile</title>
</head>
<body>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
        <ul class="list-group">
            <security:authentication property="principal.id" var="id"/>
            <li class="list-group-item">Name: ${user.name}</li>
            <li class="list-group-item">Surname: ${user.surname}</li>
            <li class="list-group-item">Email: ${user.email}</li>
            <li class="list-group-item">Address: ${user.address}</li>
            <li class="list-group-item">Telephone: ${user.telephone}</li>
        </ul>
        <a href="${pageContext.request.contextPath}/web/users/${user.id}/update" class="btn btn-primary"
           aria-pressed="true"
           role="button">Update</a>
        <a href="${pageContext.request.contextPath}/web/users/${user.id}/update/password" class="btn btn-primary"
           aria-pressed="true"
           role="button">Change Password</a>
    </div>
</div>
</body>
</html>

