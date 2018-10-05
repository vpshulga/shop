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
            <a href="${pageContext.request.contextPath}/orders/create?item=${item.id}" class="btn btn-primary" aria-pressed="true"
               role="button">Create order</a>
        </div>
    </div>
</div>

<jsp:include page="../util/js.jsp"/>
<jsp:include page="../commons/footer.jsp"/>
</body>
</html>