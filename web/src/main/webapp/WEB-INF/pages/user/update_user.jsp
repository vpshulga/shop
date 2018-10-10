<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <jsp:include page="../commons/header.jsp"/>
    <title>Update user</title>
</head>
<body>

<form:form action="${pageContext.request.contextPath}/web/users/${user.id}/update" modelAttribute="user" method="post">
    <form:errors path="*" cssClass="error"/>
    <div class="form-group">
        <form:label path="name">First name:</form:label>
        <form:input path="name" class="form-control" placeholder="First name"/>
    </div>
    <div class="form-group">
        <form:label path="surname">Last name:</form:label>
        <form:input path="surname" class="form-control" placeholder="Last name"/>
    </div>
    <security:authorize access="hasAuthority('CHANGE_ROLE')">
        <div class="form-group">
            <form:label path="email">Email:</form:label>
            <form:input path="email" type="email" class="form-control" placeholder="Email"/>
        </div>
    </security:authorize>

    <security:authorize access="!hasAuthority('CHANGE_ROLE')">
        <div class="form-group">
            <form:input path="email" type="hidden" class="form-control" placeholder="Email"/>
        </div>
    </security:authorize>

    <div class="form-group">
        <form:input path="password" type="hidden" class="form-control" value="" />
    </div>

    <div class="form-group">
        <form:label path="address">Address:</form:label>
        <form:input path="address" class="form-control" placeholder="Address"/>
    </div>
    <div class="form-group">
        <form:label path="telephone">Telephone:</form:label>
        <form:input path="telephone" class="form-control" placeholder="Telephone"/>
    </div>
    <security:authorize access="hasAuthority('CHANGE_ROLE')">
        <div class="form-group">
            <form:label path="role.id">Role:</form:label>
            <form:select path="role.id">
                <c:forEach items="${roles}" var="role">
                    <form:option value="${role.id}">${role.name}</form:option>
                </c:forEach>
            </form:select>
        </div>
        <div class="form-group">
            Disable:<form:radiobutton path="disabled" value="true"/>
            Enable:<form:radiobutton path="disabled" value="false"/>
        </div>
    </security:authorize>
    <security:authorize access="!hasAuthority('CHANGE_ROLE')">
        <div class="form-group">
            <form:input type="hidden" path="role.id" class="form-control"/>
        </div>
        <div class="form-group">
            <form:input type="hidden" path="disabled" class="form-control"/>
        </div>
    </security:authorize>
    <div class="form-group">
        <form:input type="hidden" path="deleted" class="form-control"/>
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
</form:form>
</body>
</html>

