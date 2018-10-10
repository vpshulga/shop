<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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

            <security:authorize access="isAuthenticated()">
                <li>
                    <a href="${pageContext.request.contextPath}/web/users/<security:authentication property="principal.id" />">Profile</a>
                </li>
            </security:authorize>
        </ul>
        <security:authorize access="isAnonymous()">

            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/web/users/create"><span
                        class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a href="${pageContext.request.contextPath}/web/login"><span
                        class="glyphicon glyphicon-log-in"></span>
                    Login</a></li>
            </ul>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/web/logout"><span
                        class="glyphicon glyphicon-log-out"></span>
                    Logout</a></li>
            </ul>
        </security:authorize>

    </div>
</nav>

<jsp:include page="../util/js.jsp"/>
