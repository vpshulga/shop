<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Access denied page</title>
</head>
<body>
<div class="row">
    <div class="col-md-12"><img class="logo" src="${pageContext.request.contextPath}/resources/img/access_denied.jpg"
                                alt="Access denied image"></div>
</div>

<jsp:include page="../util/js.jsp"/>
</body>
</html>