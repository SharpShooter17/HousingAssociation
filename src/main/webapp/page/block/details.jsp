<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><spring:message code="header.block"/></title>
</head>
<body>

<div class="row">
    <div class="col s6">
        <h2><spring:message code="label.details"/></h2>
        <span>${block.displayName()}</span>
        <br/>
        <span>${block.address.city}</span>
        <br/>
        <span><spring:message code="label.apartments"/>: ${block.apartments.size()}</span>
    </div>
</div>

<div class="row">
    <div class="col s12">
        <h3>
            <spring:message code="label.apartments"/>
            <a id="add-apartment" href="/page/block/details/${block.id}/apartment/add"
               class="btn-floating btn-small waves-effect waves-light red">
                <i class="material-icons">add</i>
            </a>
        </h3>
        <table class="striped centered responsive-table">
            <thead>
            <tr>
                <th><spring:message code="label.address"/></th>
                <th><spring:message code="label.occupants"/></th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${block.apartments.isEmpty()}">
                <td colspan="2"><spring:message code="label.no-apartments-available"/></td>
            </c:if>
            <c:forEach items="${block.apartments}" var="apartment">
                <tr>
                    <td>
                        <a href="/page/block/details/${blockId}/apartment/details/${apartment.id}">
                                ${apartment.displayName()}
                        </a>
                    </td>
                    <td>${apartment.occupants.size()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>

