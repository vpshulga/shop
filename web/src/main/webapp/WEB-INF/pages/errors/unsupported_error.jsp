<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Unsupported operation</title>
</head>
<body>
<div class="row">
    <div class="col-md-12"><img class="logo" src="${pageContext.request.contextPath}/resources/img/error.png"
                                alt="unsupported error image"></div>
</div>
<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6"><h3>${exception.message}</h3></div>
    <div class="col-md-3"></div>
</div>

<jsp:include page="../util/js.jsp"/>
</body>
</html>
