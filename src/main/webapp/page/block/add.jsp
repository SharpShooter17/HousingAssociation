<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><spring:message code="header.blocks"/></title>
</head>
<body>
<div class="row">
    <div class="col s3"></div>
    <div class="col s6">
        <h2><spring:message code="label.add-block"/></h2>
        <form:form method="post" action="/page/block/add" modelAttribute="address">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="city"><spring:message code="label.city"/></form:label>
                    <form:input path="city" id="input-city"/>
                    <form:errors path="city"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="zipCode"><spring:message code="label.postal-code"/></form:label>
                    <form:input path="zipCode" id="input-zip-code"/>
                    <form:errors path="zipCode"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="street"><spring:message code="label.street"/></form:label>
                    <form:input path="street" id="input-street"/>
                    <form:errors path="street"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="number"><spring:message code="label.number"/></form:label>
                    <form:input path="number" id="input-number"/>
                    <form:errors path="number"/>
                </div>
            </div>
            <div class="row">
                <button class="btn waves-effect waves-light" type="submit" id="submit-add-block">
                    <spring:message code="button.add-block"/>
                    <i class="material-icons right">send</i>
                </button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>

