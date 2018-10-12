<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Change Password</title>
</head>
<body>
<div class="col-md-4"></div>
<div class="col-md-4 shadow-lg bg-white rounded">
    <h1>Change password form</h1>
    <form:form action="${pageContext.request.contextPath}/web/users/${user.id}/update/password"
               modelAttribute="changePassword" method="post">
        <form:errors path="*" cssClass="error" element="div"/>
        <div class="form-group">
            <form:label path="newPassword">New:</form:label>
            <form:input path="newPassword" type="password" class="form-control"/>
        </div>
        <div class="form-group">
            <form:label path="confirmPassword">Confirm:</form:label>
            <form:input path="confirmPassword" type="password" class="form-control"/>
        </div>
        <button type="submit" class="btn btn-primary">Change</button>
    </form:form>
</div>
<div class="col-md-4"></div>
</div>
</body>
</html>

