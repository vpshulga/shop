<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="commons/header.jsp"/>
    <title>News</title>
    <style type="text/css">
        DIV {
            word-wrap: break-word;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="util/logo.jsp"/>
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

<jsp:include page="util/js.jsp"/>
</body>
</html>