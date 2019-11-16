<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><spring:message code="header.edit"/></title>
</head>
<body>
<div class="row">
    <div class="col s3"></div>
    <div class="col s6">
        <h2><spring:message code="header.edit"/></h2>
        <form:form method="post" action="/page/user/edit/${user.id}" modelAttribute="userModel">
            <form:hidden path="user.id"/>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="user.firstName"><spring:message code="label.name"/></form:label>
                    <form:input path="user.firstName"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="user.lastName"><spring:message code="label.lastname"/></form:label>
                    <form:input path="user.lastName"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="user.email"><spring:message code="label.email"/></form:label>
                    <form:input path="user.email"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="user.telephone"><spring:message code="label.telephone"/></form:label>
                    <form:input path="user.telephone"/>
                </div>
            </div>
            <sec:authorize access="hasAuthority('MODERATOR')">
                <div class="row">
                    <div class="input-field col s12">
                        <spring:message code="label.roles"/>
                        <form:checkboxes path="roles" items="${availableRoles}"/>
                    </div>
                </div>
            </sec:authorize>
            <div class="row">
                <button class="btn waves-effect waves-light" type="submit">
                    <spring:message code="button.edit"/>
                    <i class="material-icons right">send</i>
                </button>
            </div>
        </form:form>
    </div>
    <div class="col s3"></div>
</div>
</body>
</html>

