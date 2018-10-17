<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Order</title>
</head>
<body>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <ul class="list-group">
            <li class="list-group-item">Item: ${item.name}</li>
            <li class="list-group-item">Cost: ${item.price}</li>
            <li class="list-group-item">Quantity: ${quantity}</li>
            <li class="list-group-item">Total: ${quantity * item.price}</li>
        </ul>
        <form:errors path="*" cssClass="error"/>
        <form:form action="${pageContext.request.contextPath}/web/orders/order/ready" modelAttribute="order"
                   method="post">
            <div class="form-group">
                <form:input path="quantity" type="hidden" class="form-control" value="${quantity}"/>
            </div>
            <input type="hidden" name="item" value="${item.id}"/>
            <button type="submit" class="btn btn-primary">Pay</button>
        </form:form>
    </div>
</div>

<jsp:include page="../util/js.jsp"/>
<jsp:include page="../commons/footer.jsp"/>
</body>
</html>

