<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="commons/header.jsp"/>
    <title>Login page</title>
</head>
<body>
<div class="container">
    <jsp:include page="util/logo.jsp"/>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4 shadow-lg bg-white rounded">

            <form action="${pageContext.request.contextPath}/web/login" method="post">
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" name="email" class="form-control" id="email"
                           placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" class="form-control" id="password"
                           placeholder="Password">
                </div>
                <c:if test="${param.error == 'true'}">
                    <c:choose>
                        <c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message eq 'User is disabled'}">
                            <font color="red">
                                <c:out value="User is disabled"/>
                            </font><br><br>
                        </c:when>
                        <c:otherwise>
                            <font color="red">
                                <c:out value="Invalid login or password"/>
                            </font><br><br>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <button type="submit" class="btn btn-primary">Login</button>

            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>

<jsp:include page="util/js.jsp"/>
</body>
</html>