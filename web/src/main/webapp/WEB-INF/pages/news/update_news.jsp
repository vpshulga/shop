<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Create user</title>
</head>
<body>
<form:errors path="*" cssClass="error" />
<form:form action="${pageContext.request.contextPath}/web/news/${news.id}/update" modelAttribute="news" method="post">
    <div class="form-group">
        <form:label path="title">Title:</form:label>
        <form:input path="title" class="form-control" placeholder="Title" />
    </div>
    <div class="form-group">
        <form:label path="content">Content:</form:label>
        <form:input path="content" class="form-control" placeholder="Content" />
    </div>
    <div class="form-group">
        <form:input type="hidden" path="created" class="form-control" />
    </div>
    <div class="form-group">
        <form:input type="hidden" path="user.id" class="form-control" />
    </div>


    <button type="submit" class="btn btn-primary">Update</button>
</form:form>
</body>
</html>