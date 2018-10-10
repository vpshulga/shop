<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Orders page</title>
</head>
<body>
<div class="container">
    <jsp:include page="../util/logo.jsp"/>
    <security:authorize access="hasAuthority('CHANGE_ORDER_STATUS')">
        <div class="row">
            <div class="col-md-12">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Created</th>
                        <th scope="col">Item</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Price</th>
                        <th scope="col">Total</th>
                        <th scope="col">User</th>
                        <th scope="col">Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td>${order.created}</td>
                            <td>${order.itemName}</td>
                            <td>${order.quantity}</td>
                            <td>${order.itemPrice}</td>
                            <td>${order.total}</td>
                            <td>${order.creator}</td>
                            <td>${order.status}(<a class="page-link"
                                                   href="${pageContext.request.contextPath}/web/orders/${order.id}">change</a>)</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <ul class="pagination">
            <c:forEach var="page" begin="1" end="${pages}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/web/orders?page=${page}">${page}</a>
                </li>
            </c:forEach>
        </ul>
    </security:authorize>

    <security:authorize access="!hasAuthority('CHANGE_ORDER_STATUS')">
        <div class="row">
            <div class="col-md-12">

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Created</th>
                        <th scope="col">Item</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Price</th>
                        <th scope="col">Total</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userOrders}" var="order">
                        <tr>
                            <td>${order.created}</td>
                            <td>${order.itemName}</td>
                            <td>${order.quantity}</td>
                            <td>${order.itemPrice}</td>
                            <td>${order.total}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <ul class="pagination">
            <c:forEach var="page" begin="1" end="${pagesForUser}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/web/orders?page=${page}">${page}</a>
                </li>
            </c:forEach>
        </ul>
    </security:authorize>
</div>

<jsp:include page="../util/js.jsp"/>
</body>
</html>