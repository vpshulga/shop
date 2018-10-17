<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Create business card</title>
</head>
<body>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4 shadow-lg bg-white rounded">
        <h1>Registration form</h1>
        <form:form action="${pageContext.request.contextPath}/web/cards/create" modelAttribute="businessCard"
                   method="post">
            <form:errors path="*" cssClass="error" element="div"/>
            <div class="form-group">
                <form:label path="title">Title</form:label>
                <form:input path="title" class="form-control" placeholder="Title"/>
            </div>
            <div class="form-group">
                <form:label path="fullName">Full name</form:label>
                <form:input path="fullName" class="form-control" placeholder="Full name"/>
            </div>
            <div class="form-group">
                <form:label path="workingTelephone">Working telephone</form:label>
                <form:input path="workingTelephone" type="number" class="form-control" placeholder="Working telephone"/>
            </div>
            <button type="submit" class="btn btn-primary">Create</button>
        </form:form>
    </div>
    <div class="col-md-4"></div>
</div>
</body>
</html>

