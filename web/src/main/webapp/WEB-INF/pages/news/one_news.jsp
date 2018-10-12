<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>News</title>
    <style type="text/css">
        DIV {
            word-wrap: break-word;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="../util/logo.jsp"/>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6"><h3>${news.title}</h3></div>
        <div class="col-md-3"></div>
    </div>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">${news.content}</div>
        <div class="col-md-3"></div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <security:authorize access="hasAuthority('CREATE_COMMENT')">
            <div class="col-md-6">
                <form:errors path="*" cssClass="error"/>
                <form:form action="${pageContext.request.contextPath}/web/news/${news.id}" modelAttribute="comment"
                           method="post">
                    <div class="form-group">
                        <form:label path="content">Comment:</form:label>
                        <form:textarea path="content" class="form-control" placeholder="Comment"/>
                    </div>
                    <button type="submit" class="btn btn-primary">comment</button>
                </form:form>
            </div>
        </security:authorize>
        <div class="col-md-3"></div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <h3>Comments</h3>
            <c:forEach items="${comments}" var="oneComment">
                <form action="${pageContext.request.contextPath}/web/news/comment/delete" method="post">
                    created:${oneComment.created} |
                    creator:${oneComment.creator}<br>
                        ${oneComment.content}<br><br>
                    <input type="hidden" name="commentId" value="${oneComment.id}">
                    <input type="hidden" name="newsId" value="${news.id}">
                    <security:authorize access="hasAuthority('DELETE_COMMENTS')">
                        <input type="submit" value="delete" class="link"/><br>
                    </security:authorize>
                    _____________________________________________<br><br>
                </form>
            </c:forEach>
            <ul class="pagination">
                <c:forEach var="page" begin="1" end="${pages}">
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/web/news/${news.id}?page=${page}">${page}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

<jsp:include page="../util/js.jsp"/>
</body>
</html>