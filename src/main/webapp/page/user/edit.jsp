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
        <form:form method="post"
                   action="/page/user/edit/${userId}"
                   modelAttribute="userModel">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="firstName"><spring:message code="label.name"/></form:label>
                    <form:input path="firstName"/>
                    <form:errors path="firstName"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="lastName"><spring:message code="label.lastname"/></form:label>
                    <form:input path="lastName"/>
                    <form:errors path="lastName"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="email"><spring:message code="label.email"/></form:label>
                    <form:input path="email"/>
                    <form:errors path="email"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="telephone"><spring:message code="label.telephone"/></form:label>
                    <form:input path="telephone"/>
                    <form:errors path="telephone"/>
                </div>
            </div>
            <sec:authorize access="hasAuthority('MODERATOR')">
                <div class="row">
                    <div class="input-field col s12">
                        <spring:message code="label.roles"/>
                        <form:checkboxes path="userRoles" items="${availableRoles}"/>
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

