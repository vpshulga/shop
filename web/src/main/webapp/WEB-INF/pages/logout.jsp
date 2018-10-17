<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="commons/header.jsp"/>
    <title>Logout page</title>
</head>
<body>
<div class="container">
    <jsp:include page="util/logo.jsp"/>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 shadow-lg bg-white rounded">
            <form action="${pageContext.request.contextPath}/web/logout" method="post">
                <button type="submit" class="btn btn-primary">Logout</button>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>

<jsp:include page="util/js.jsp"/>
</body>
</html>