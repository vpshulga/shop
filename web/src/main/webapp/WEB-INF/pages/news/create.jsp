<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Create news</title>
</head>
<body>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4 shadow-lg bg-white rounded">
        <h1>Create news form</h1>
        <form:errors path="*" cssClass="error"/>
        <form:form action="${pageContext.request.contextPath}/web/news" modelAttribute="news" method="post">
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
    </div>
    <div class="col-md-4"></div>
</div>
</body>
</html>