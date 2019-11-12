<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Users</title>
</head>
<body>
<div class="row">
    <div class="col s3"></div>
    <div class="col s6">
        <h1>Register</h1>
        <form:form method="post" action="/admin/addUser.html" modelAttribute="newUser">
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="firstName">First Name</form:label>
                    <form:input path="firstName"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="lastName">Last Name</form:label>
                    <form:input path="lastName"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="email">Email</form:label>
                    <form:input path="email"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="telephone">Telephone</form:label>
                    <form:input path="telephone"/>
                </div>
            </div>
            <div class="row">
                <button class="btn waves-effect waves-light" type="submit">Register new user
                    <i class="material-icons right">send</i>
                </button>
            </div>
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
                <td colspan="4">No Users Available</td>
            </c:if>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td><a href="/user/${user.id}">${user.email}</a></td>
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

