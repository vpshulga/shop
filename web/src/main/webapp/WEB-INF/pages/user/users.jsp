<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Users page</title>
</head>
<body>
<div class="container">
    <jsp:include page="../util/logo.jsp"/>

    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <form action="${pageContext.request.contextPath}/web/users/delete" method="post">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/web/users/create" class="btn btn-primary" aria-pressed="true" role="button">ADD</a>
                        <button type="submit" class="btn btn-primary">DELETE</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Email</th>
                                <th scope="col">FirstName</th>
                                <th scope="col">LastName</th>
                                <th scope="col">Role</th>
                                <th scope="col">Disabled</th>
                                <th scope="col">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <th scope="row"><input type="checkbox" name="ids" value="${user.id}"></th>
                                    <td>${user.email}</td>
                                    <td>${user.name}</td>
                                    <td>${user.surname}</td>
                                    <td>${user.role.name}</td>
                                    <td>${user.disabled}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/web/users/${user.id}" class="btn btn-primary" aria-pressed="true"
                                           role="button">Info</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>

<jsp:include page="../util/js.jsp"/>
</body>
</html>