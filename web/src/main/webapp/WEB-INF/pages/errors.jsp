<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="commons/header.jsp"/>
    <title>Errors page</title>
</head>
<body>
<h2>Application has error</h2>
<h3>Debug information</h3>

Requested URL = ${url}<br><br>

Exception = ${exception.message}<br><br>

<div class="container">
        <strong>Exception stack trace</strong><br>
        <c:forEach items="${exception.stackTrace}" var="ste">
            ${ste}
        </c:forEach>
<jsp:include page="util/js.jsp"/>
</body>
</html>