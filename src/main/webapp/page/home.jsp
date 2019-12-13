<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><spring:message code="title.homepage"/></title>
</head>
<body>
<div class="row">
    <div class="col s12 m6">
        <div class="card blue-grey darken-1">
            <div class="card-content white-text">
                <span class="card-title"><spring:message code="header.user-details"/></span>
                <p><spring:message code="label.name"/>: <sec:authentication property="principal.firstName"/>
                    <sec:authentication property="principal.lastName"/></p>
                <p><spring:message code="label.telephone"/>: <sec:authentication property="principal.telephone"/></p>
                <p><spring:message code="label.email"/>: <sec:authentication property="principal.email"/></p>
            </div>
            <div class="card-action">
                <a href="/page/user/edit/${userId}">Edit</a>
            </div>
        </div>
    </div>
</div>
<sec:authorize access="hasAuthority('USER')">
    <div class="row">
        <div class="col s6">
            <h3><spring:message code="header.yours-apartments"/></h3>
            <table class="striped centered responsive-table">
                <thead>
                <tr>
                    <th><spring:message code="label.address"/></th>
                    <th><spring:message code="label.occupants"/></th>
                </tr>
                </thead>

                <tbody>
                <c:if test="${apartments.isEmpty()}">
                    <td colspan="2"><spring:message code="label.no-apartments-available"/></td>
                </c:if>
                <c:forEach items="${apartments}" var="apartment">
                    <tr>
                        <td>${apartment.displayName()}</td>
                        <td>${apartment.occupants.size()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col s6">
            <h3><spring:message code="header.yours-bills"/></h3>
            <table class="striped centered responsive-table">
                <thead>
                <tr>
                    <th><spring:message code="label.address"/></th>
                    <th><spring:message code="label.type"/></th>
                    <th><spring:message code="label.date"/></th>
                    <th><spring:message code="label.amount"/></th>
                    <th><spring:message code="label.download"/></th>
                </tr>
                </thead>

                <tbody>
                <c:if test="${bills.isEmpty()}">
                    <td colspan="5"><spring:message code="label.no-bills-available"/></td>
                </c:if>
                <c:forEach items="${bills}" var="bill">
                    <tr>
                        <td>${bill.displayName()}</td>
                        <td><spring:message code="billing.type.${bill.type}"/></td>
                        <td>${bill.date}</td>
                        <td>${bill.amount}</td>
                        <td>
                            <a href="/page/block/details/${bill.apartment.block.id}/apartment/details/${bill.apartment.id}/bill/download/${bill.id}">
                                <spring:message code="label.download"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</sec:authorize>

</body>
</html>

