<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Create user</title>
</head>
<body>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
        <ul class="list-group">
            <li class="list-group-item">Item: ${item.name}</li>
            <li class="list-group-item">Cost: ${item.price}</li>
            <li class="list-group-item">Quantity: ${quantity}</li>
            <li class="list-group-item">Total: ${quantity * item.price}</li>
        </ul>
    </div>
</div>
<form:errors path="*" cssClass="error"/>
<form:form action="${pageContext.request.contextPath}/web/orders/order/ready" modelAttribute="order" method="post">
    <div class="form-group">
        <form:input path="quantity" type="hidden" class="form-control" value="${quantity}"/>
    </div>
    <div class="form-group">
        <form:input type="hidden" path="item.id" class="form-control" value="${item.id}"/>
    </div>
    <div class="form-group">
        <security:authentication property="principal.id" var="user"/>
        <form:input type="hidden" path="user.id" class="form-control" value="${user}"/>
    </div>
    <button type="submit" class="btn btn-primary">pay</button>
</form:form>
<jsp:include page="../util/js.jsp"/>
<jsp:include page="../commons/footer.jsp"/>
</body>
</html>

