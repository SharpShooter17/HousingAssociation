<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Homepage</title>
</head>
<body>
<div class="row">
    <div class="col s12 m6">
        <div class="card blue-grey darken-1">
            <div class="card-content white-text">
                <span class="card-title">User details</span>
                <p>Name: <sec:authentication property="principal.firstName"/> <sec:authentication property="principal.lastName"/></p>
                <p>Telephone: <sec:authentication property="principal.telephone"/></p>
                <p>Email: <sec:authentication property="principal.email"/></p>
            </div>
        </div>
    </div>
</div>
<sec:authorize access="hasAuthority('USER')">
    <div class="row">
        <div class="col s6">
            <h3>Yours Apartments</h3>
            <table class="striped centered responsive-table">
                <thead>
                <tr>
                    <th>Address</th>
                    <th>Occupants</th>
                </tr>
                </thead>

                <tbody>
                <c:if test="${apartments.isEmpty()}">
                    <td colspan="2">No Apartments Available</td>
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
            <h3>Yours Bills</h3>
            <table class="striped centered responsive-table">
                <thead>
                <tr>
                    <th>Address</th>
                    <th>Type</th>
                    <th>Date</th>
                    <th>Amount</th>
                    <th>Download</th>
                </tr>
                </thead>

                <tbody>
                <c:if test="${bills.isEmpty()}">
                    <td colspan="4">No Bills Available</td>
                </c:if>
                <c:forEach items="${bills}" var="bill">
                    <tr>
                        <td>${bill.displayName()}</td>
                        <td>${bill.type}</td>
                        <td>${bill.date}</td>
                        <td>${bill.amount}</td>
                        <td><a href="/page/block/details/${bill.apartment.block.id}/apartment/details/${bill.apartment.id}/bill/download/${bill.id}">link</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</sec:authorize>

</body>
</html>

