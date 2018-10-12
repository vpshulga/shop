<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Item</title>
</head>
<body>
<div class="container">
    <jsp:include page="../util/logo.jsp"/>
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
    <div class="row">
        <div class="col-md-12">
            <security:authorize access="hasAuthority('CREATE_ORDER')">
                <a href="${pageContext.request.contextPath}/web/orders/order?item=${item.id}" class="btn btn-primary"
                   aria-pressed="true"
                   role="button">Create order</a>
            </security:authorize>
            <security:authorize access="hasAuthority('COPY_ITEMS')">
                <form:form action="${pageContext.request.contextPath}/web/items/copy" modelAttribute="item"
                           method="post">
                    <form:errors path="*" cssClass="error" element="div"/>
                    <div class="form-group">
                        <form:input path="name" type="hidden" class="form-control" placeholder="Item name"/>
                    </div>
                    <div class="form-group">
                        <form:input path="description" type="hidden" class="form-control" placeholder="Description"/>
                    </div>
                    <div class="form-group">
                        <form:input path="price" type="hidden" step="0.01" class="form-control" placeholder="Price"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Copy item</button>
                </form:form>
            </security:authorize>
        </div>
    </div>
</div>

<jsp:include page="../util/js.jsp"/>
<jsp:include page="../commons/footer.jsp"/>
</body>
</html>