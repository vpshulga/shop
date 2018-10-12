<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Create item</title>
</head>
<body>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4 shadow-lg bg-white rounded">
        <h1>Create item form</h1>
        <form:form action="${pageContext.request.contextPath}/web/items/create" modelAttribute="item" method="post">
            <form:errors path="*" cssClass="error" element="div"/>
            <div class="form-group">
                <form:label path="name">Name:</form:label>
                <form:input path="name" class="form-control" placeholder="Item name"/>
            </div>
            <div class="form-group">
                <form:label path="description">Description:</form:label>
                <form:input path="description" class="form-control" placeholder="Description"/>
            </div>
            <div class="form-group">
                <form:input path="uniqueNumber" type="hidden" class="form-control" placeholder="Unique Number"/>
            </div>
            <div class="form-group">
                <form:label path="price">Price:</form:label>
                <form:input path="price" type="number" step="0.01" class="form-control" placeholder="Price"/>
            </div>
            <button type="submit" class="btn btn-primary">Create</button>
        </form:form>
    </div>
    <div class="col-md-4"></div>
</div>
</body>
</html>

