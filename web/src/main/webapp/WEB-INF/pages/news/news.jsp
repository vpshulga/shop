<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>News page</title>
</head>
<body>
<div class="container">
    <jsp:include page="../util/logo.jsp"/>

    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <form action="${pageContext.request.contextPath}/web/news/delete" method="post">
                <div class="row">
                    <div class="col-md-12">
                        <security:authorize access="hasAuthority('CREATE_NEWS')">
                            <a href="${pageContext.request.contextPath}/web/news/create" class="btn btn-primary"
                               aria-pressed="true" role="button">ADD</a>
                        </security:authorize>
                        <security:authorize access="hasAuthority('DELETE_NEWS')">
                            <button type="submit" class="btn btn-primary">DELETE</button>
                        </security:authorize>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <security:authorize access="hasAuthority('DELETE_NEWS')">
                                    <th scope="col">#</th>
                                </security:authorize>
                                <th scope="col">Title</th>
                                <th scope="col">Created</th>
                                <th scope="col">Creator</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${news}" var="news">
                                <tr>
                                    <security:authorize access="hasAuthority('DELETE_NEWS')">
                                        <th scope="row"><input type="checkbox" name="ids" value="${news.id}"></th>
                                    </security:authorize>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/web/news/${news.id}">${news.title}</a>
                                    </td>
                                    <td>${news.created.dayOfMonth}-${news.created.monthValue}-${news.created.year}
                                            ${news.created.hour}:${news.created.minute}</td>
                                    <td>${news.creator}</td>
                                    <security:authorize access="hasAuthority('UPDATE_NEWS')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/web/news/${news.id}/update"
                                               class="btn btn-primary"
                                               aria-pressed="true"
                                               role="button">UPDATE</a>
                                        </td>
                                    </security:authorize>
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
    <ul class="pagination">
        <c:forEach var="page" begin="1" end="${pages}">
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/news?page=${page}">${page}</a></li>
        </c:forEach>
    </ul>
</div>

<jsp:include page="../util/js.jsp"/>
</body>
</html>
