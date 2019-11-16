<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><spring:message code="header.apartment"/></title>
</head>
<body>

<div class="row">
    <div class="col s12">
        <h2><spring:message code="header.details"/></h2>
        <span>${apartment.displayName()}</span>
        <br/>
        <span>${apartment.block.address.city}</span>
    </div>
</div>

<div class="row">
    <div class="col s6">
        <h3>
            <spring:message code="header.bills"/>
            <a href="/page/block/details/${apartment.block.id}/apartment/details/${apartment.id}/bill/add"
               class="btn-floating btn-small waves-effect waves-light red">
                <i class="material-icons">add</i>
            </a>
        </h3>
        <table class="striped centered responsive-table">
            <thead>
            <tr>
                <th><spring:message code="label.type"/></th>
                <th><spring:message code="label.date"/></th>
                <th><spring:message code="label.amount"/></th>
                <th><spring:message code="label.download"/></th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${apartment.bills.isEmpty()}">
                <td colspan="4"><spring:message code="label.no-bills-available"/></td>
            </c:if>
            <c:forEach items="${apartment.bills}" var="bill">
                <tr>
                    <td>${bill.type}</td>
                    <td>${bill.date}</td>
                    <td>${bill.amount}</td>
                    <td>
                        <a href="/page/block/details/${apartment.block.id}/apartment/details/${apartment.id}/bill/download/${bill.id}">
                            <spring:message code="label.download"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col s6">
        <h3>
            Occupants
            <a href="/page/block/details/${apartment.block.id}/apartment/details/${apartment.id}/occupant/edit"
               class="btn-floating btn-small waves-effect waves-light red">
                <i class="material-icons">add</i>
            </a>
        </h3>
        <table class="striped centered responsive-table">
            <thead>
            <tr>
                <th><spring:message code="label.full-name"/></th>
                <th><spring:message code="label.email"/></th>
                <th><spring:message code="label.telephone"/></th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${apartment.occupants.isEmpty()}">
                <td colspan="3"><spring:message code="label.apartment-is-empty"/></td>
            </c:if>
            <c:forEach items="${apartment.occupants}" var="occupant">
                <tr>
                    <td>${occupant.firstName} ${occupant.lastName}</td>
                    <td>${occupant.email}</td>
                    <td>${occupant.telephone}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>

