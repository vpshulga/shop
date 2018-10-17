<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Business cards page</title>
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
                        <a href="${pageContext.request.contextPath}/web/cards/create" class="btn btn-primary"
                           aria-pressed="true" role="button">ADD</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Title</th>
                                <th scope="col">Full Name</th>
                                <th scope="col">Working Telephone</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${cards}" var="card">
                                <tr>
                                    <td>${card.title}</td>
                                    <td>${card.fullName}</td>
                                    <td>${card.workingTelephone}</td>
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