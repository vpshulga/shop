
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="commons/header.jsp"/>
    <title>News page</title>
</head>
<body>
<div class="container">
    <jsp:include page="util/logo.jsp"/>

    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <form action="${pageContext.request.contextPath}/news" method="post">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/news/create" class="btn btn-primary" aria-pressed="true" role="button">ADD</a>
                        <button type="submit" class="btn btn-primary">DELETE</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Title</th>
                                <th scope="col">Created</th>
                                <th scope="col">Creator</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${news}" var="news">
                                <tr>
                                    <th scope="row"><input type="checkbox" name="ids" value="${news.id}"></th>
                                    <td><a href="${pageContext.request.contextPath}/news/${news.id}">${news.title}</a></td>
                                    <td>${news.created}</td>
                                    <td>${news.user.name}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/users" class="btn btn-primary" aria-pressed="true"
                                           role="button">UPDATE</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>

<jsp:include page="util/js.jsp"/>
</body>
</html>
