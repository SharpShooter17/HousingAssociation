<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><spring:message code="header.occupants"/></title>
</head>
<body>

<div class="row">
    <div class="col s3"></div>
    <div class="col s6">
        <h2><spring:message code="header.edit-occupants"/></h2>
        <form:form method="post"
                   modelAttribute="occupantModel"
                   action="/page/block/details/${blockId}/apartment/details/${apartmentId}/occupant/edit">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="row">
                <div class="input-field col s12">
                    <form:checkboxes cssStyle="display: block" items="${availableOccupants}" path="occupantEmails"/>
                </div>
            </div>
            <div class="row">
                <button class="btn waves-effect waves-light" type="submit"><spring:message code="button.save"/>
                    <i class="material-icons right">send</i>
                </button>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>

