<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            <form action="${pageContext.request.contextPath}/web/items" method="post" enctype="multipart/form-data"
                  accept-charset="UTF-8">
                <security:authorize access="hasAuthority('UPLOAD_ITEMS_XML')">
                    <input type="file" name="xmlFile" accept="text/xml">
                    <button type="submit" class="btn btn-primary">UPLOAD XML</button>
                </security:authorize>
            </form>
            <form action="${pageContext.request.contextPath}/web/items/delete" method="post">
                <security:authorize access="hasAuthority('CREATE_ITEMS')">
                    <a href="${pageContext.request.contextPath}/web/items/create" class="btn btn-primary"
                       aria-pressed="true" role="button">ADD</a>
                </security:authorize>
                <security:authorize access="hasAuthority('REMOVE_ITEMS')">
                    <button type="submit" class="btn btn-primary">DELETE</button>
                </security:authorize>

                <table class="table">
                    <thead>
                    <tr>
                        <security:authorize access="hasAuthority('REMOVE_ITEMS')">
                            <th scope="col">#</th>
                        </security:authorize>
                        <th scope="col">Name</th>
                        <th scope="col">Price</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${items}" var="item">
                        <tr>
                            <security:authorize access="hasAuthority('REMOVE_ITEMS')">
                                <th scope="row"><input type="checkbox" name="ids" value="${item.id}"></th>
                            </security:authorize>
                            <td>${item.name}</td>
                            <td>${item.price}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/web/items/${item.id}"
                                   class="btn btn-primary"
                                   aria-pressed="true"
                                   role="button">Details</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
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