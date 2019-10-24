<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
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
                <th>Name</th>
                <th>Item Name</th>
                <th>Item Price</th>
            </tr>
            </thead>

            <tbody>
            <tr th:if="${users.isEmpty()}">
                <td colspan="4"> No Users Available</td>
            </tr>
            <tr th:each="user : ${users}">
                <td><span th:text="${user.firstName}"></span></td>
                <td><span th:text="${user.lastName}"></span></td>
                <td><span th:text="${user.email}"></span></td>
                <td><span th:text="${user.telephone}"></span></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="col s2"></div>
</div>

</body>
</html>

