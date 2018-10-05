<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../util/head.jsp"/>


<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Shop</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/web/items">Items</a></li>
            <li><a href="${pageContext.request.contextPath}/web/users">Users</a></li>
            <li><a href="${pageContext.request.contextPath}/web/news">News</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="${pageContext.request.contextPath}/web/users/create"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="${pageContext.request.contextPath}/web/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
    </div>
</nav>

<jsp:include page="../util/js.jsp"/>
