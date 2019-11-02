<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>App user page</title>
</head>
<body>
<div class="row">
    <div class="col s3"></div>
    <div class="col s6">
        <h1>Register new user</h1>
        <form:form method="post" action="addAppUser.html" modelAttribute="newUser">
            <table>
                <tr>
                    <td><form:label path="firstName">First Name</form:label></td>
                    <td><form:input path="firstName"/></td>
                </tr>
                <tr>
                    <td><form:label path="lastName">Last Name</form:label></td>
                    <td><form:input path="lastName"/></td>
                </tr>
                <tr>
                    <td><form:label path="email">Email</form:label></td>
                    <td><form:input path="email"/></td>
                </tr>
                <tr>
                    <td><form:label path="telephone">Telephone</form:label></td>
                    <td><form:input path="telephone"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Add AppUser"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
    <div class="col s3"></div>
</div>

<div class="row">
    <div class="col s2"></div>
    <div class="col s8">
        <h3>Users:</h3>
        <table class="striped centered responsive-table">
            <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Telephone</th>
                <th>Is enabled</th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${users.isEmpty()}">
                <td colspan="4"> No Users Available</td>
            </c:if>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.telephone}</td>
                    <td>${user.isEnabled()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col s2"></div>
</div>

</body>
</html>

