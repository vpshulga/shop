<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Items page</title>
</head>
<body>
<div class="container">
    <jsp:include page="../util/logo.jsp"/>
    <div class="row">
        <div class="col-md-12">
            <div class="col-md-12">
                <form action="${pageContext.request.contextPath}/web/items" method="post" enctype="multipart/form-data">
                    <security:authorize access="isAuthenticated()">
                        <input type="file" name="xmlFile" accept="text/xml">
                        <button type="submit" class="btn btn-primary">upload</button>
                    </security:authorize>
                </form>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${items}" var="item">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.price}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/web/items/${item.id}" class="btn btn-primary" aria-pressed="true"
                               role="button">Details</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <ul class="pagination">
        <c:forEach var="page" begin="1" end="${pages}">
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/web/items?page=${page}">${page}</a></li>
        </c:forEach>
    </ul>
</div>

<jsp:include page="../util/js.jsp"/>
<jsp:include page="../commons/footer.jsp"/>
</body>
</html>