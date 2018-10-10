<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Change Password</title>
</head>
<body>

<form:form action="${pageContext.request.contextPath}/web/users/${user.id}/update/password" modelAttribute="changePassword" method="post">
    <form:errors path="*" cssClass="error" element="div" />
    <div class="form-group">
        <form:label path="oldPassword">Old:</form:label>
        <form:input path="oldPassword" class="form-control"  />
    </div>
    <div class="form-group">
        <form:label path="newPassword">New:</form:label>
        <form:input path="newPassword" class="form-control"  />
    </div>
    <div class="form-group">
        <form:label path="confirmPassword">Confirm:</form:label>
        <form:input path="confirmPassword" class="form-control" />
    </div>
    <button type="submit" class="btn btn-primary">Create</button>
</form:form>
</body>
</html>

