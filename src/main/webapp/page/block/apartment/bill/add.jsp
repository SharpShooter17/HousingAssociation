<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><spring:message code="header.bill"/></title>
</head>
<body>

<div class="row">
    <div class="col s3"></div>
    <div class="col s6">
        <h2><spring:message code="header.add-bill"/></h2>
        <form:form method="post"
                   action="/page/block/details/${blockId}/apartment/details/${apartmentId}/bill/add"
                   modelAttribute="bill">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="row">
                <div class="input-field col s12">
                    <form:select items="${availableTypes}" path="type"/>
                    <label><spring:message code="label.type"/></label>
                    <form:errors path="type"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="date"><spring:message code="label.date"/></form:label>
                    <form:input path="date" class="datepicker"/>
                    <form:errors path="date"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="amount"><spring:message code="label.amount"/></form:label>
                    <form:input path="amount" type="number" step="0.01"/>
                    <form:errors path="amount"/>
                </div>
            </div>

            <div class="row">
                <button class="btn waves-effect waves-light" type="submit" id="save-bill"><spring:message code="button.add-bill"/>
                    <i class="material-icons right">send</i>
                </button>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>

