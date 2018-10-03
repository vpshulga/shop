<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <div class="col-md-6">
            <form:errors path="*" cssClass="error"/>
            <form:form action="${pageContext.request.contextPath}/news/${news.id}" modelAttribute="comment"
                       method="post">
                <div class="form-group">
                    <form:label path="content">Comment:</form:label>
                    <form:textarea path="content" class="form-control" placeholder="Comment"/>
                </div>
                <button type="submit" class="btn btn-primary">comment</button>
            </form:form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <h3>Comments</h3>
            <c:forEach items="${comments}" var="oneComment">
                created:${oneComment.created} |
                creator:${oneComment.user.name}<br>
                ${oneComment.content}<br><br>
            </c:forEach></div>
        <div class="col-md-3"></div>
    </div>
</div>

<jsp:include page="../util/js.jsp"/>
</body>
</html>