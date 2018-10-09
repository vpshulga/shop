<%@ page import="com.gmail.vpshulgaa.dao.enums.Status" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Update order</title>
</head>
<body>


<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
        <ul class="list-group">
            <li class="list-group-item">Created: ${order.created}</li>
            <li class="list-group-item">Item: ${order.item.name}</li>
            <li class="list-group-item">Price: ${order.item.price}</li>
            <li class="list-group-item">Quantity: ${order.quantity}</li>
            <li class="list-group-item">Total: ${order.total}</li>
        </ul>
    </div>
</div>

<form:form action="${pageContext.request.contextPath}/web/orders/${order.id}/update" modelAttribute="order" method="post">
    <form:errors path="*" cssClass="error" />
    <div class="form-group">
        <form:input path="created" type="hidden" class="form-control"/>
    </div>
    <div class="form-group">
        <form:input path="quantity" type="hidden" class="form-control"/>
    </div>
    <div class="form-group">
        <form:input type="hidden" path="item.id" class="form-control"/>
    </div>
    <div class="form-group">
        <form:input path="user.id" type="hidden"  class="form-control" />
    </div>
    <div class="form-group">
        <form:input path="total" type="hidden"  class="form-control" />
    </div>
    <div class="form-group">
        <form:label path="status">Status:</form:label>
        <form:select path="status">
            <c:forEach items="${statuses}" var="status">
                <form:option value="${status}">${status}</form:option>
            </c:forEach>
        </form:select>
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
</form:form>
</body>
</html>

