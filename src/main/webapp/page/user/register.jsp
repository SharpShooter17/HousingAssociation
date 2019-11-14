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
        <h2>Register</h2>
        <form:form method="post" action="/page/user/register" modelAttribute="newUser">
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="user.firstName">First Name</form:label>
                    <form:input path="user.firstName"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="user.lastName">Last Name</form:label>
                    <form:input path="user.lastName"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="user.email">Email</form:label>
                    <form:input path="user.email"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="user.telephone">Telephone</form:label>
                    <form:input path="user.telephone"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    Roles:
                    <form:checkboxes path="roles" items="${availableRoles}"/>
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
</body>
</html>

