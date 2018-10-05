<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>order page</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Price</th>
                <th scope="col">Unique number</th>
            </tr>
            </thead>
            <tbody>

            <tr>
                <td>${item.name}</td>
                <td>${item.description}</td>
                <td>${item.price}</td>
                <td>${item.uniqueNumber}</td>
            </tr>

            </tbody>
        </table>
    </div>
</div>
<form:errors path="*" cssClass="error"/>
<form:form action="${pageContext.request.contextPath}/web/orders/create" modelAttribute="order" method="post">
    <div class="form-group">
        <form:label path="quantity">Quantity</form:label>
        <form:input path="quantity" class="form-control" placeholder="Quantity"/>
    </div>
    <div class="form-group">
        <form:input type="hidden" path="item.id" class="form-control" value="${item.id}" />
    </div>
    <button type="submit" class="btn btn-primary">Create</button>
</form:form>
<jsp:include page="../util/js.jsp"/>
<jsp:include page="../commons/footer.jsp"/>
</body>
</html>
