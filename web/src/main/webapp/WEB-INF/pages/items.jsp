<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="commons/header.jsp"/>
    <title>Items page</title>
</head>
<body>
<div class="container">
    <jsp:include page="util/logo.jsp"/>
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price</th>
                    <th scope="col">Unique number</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${items}" var="item">
                    <tr>
                        <td>#</td>
                        <td>${item.name}</td>
                        <td>${item.description}</td>
                        <td>${item.price}</td>
                        <td>${item.uniqueNumber}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <ul class="pagination">
        <c:forEach var="page" items="${pages}" varStatus="status">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/items?page=${page}">${page}</a></li>
        </c:forEach>
    </ul>
</div>

<jsp:include page="util/js.jsp"/>
<jsp:include page="commons/footer.jsp"/>
</body>
</html>