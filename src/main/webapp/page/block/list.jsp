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
    <div class="col s12">
        <h3><spring:message code="header.blocks"/></h3>
        <table class="striped centered responsive-table">
            <thead>
            <tr>
                <th><spring:message code="label.city"/></th>
                <th><spring:message code="label.address"/></th>
                <th><spring:message code="label.apartments"/></th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${blocks.isEmpty()}">
                <td colspan="5"><spring:message code="label.no-blocks-available"/></td>
            </c:if>
            <c:forEach items="${blocks}" var="block">
                <tr>
                    <td>${block.address.city}</td>
                    <td><a href="/page/block/details/${block.id}">${block.displayName()}</a></td>
                    <td>${block.apartments.size()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

