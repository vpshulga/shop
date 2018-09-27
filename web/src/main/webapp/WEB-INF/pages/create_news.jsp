<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="commons/header.jsp"/>
    <title>Create news</title>
</head>
<body>
<form:errors path="*" cssClass="error"/>
<form:form action="${pageContext.request.contextPath}/news" modelAttribute="news" method="post">
    <div class="form-group">
        <form:label path="title">Title:</form:label>
        <form:input path="title" class="form-control" placeholder="Title"/>
    </div>
    <div class="form-group">
        <form:label path="content">Content:</form:label>
        <form:textarea path="content" class="form-control" placeholder="Content"/>
    </div>
    <button type="submit" class="btn btn-primary">Create</button>
</form:form>
</body>
</html>