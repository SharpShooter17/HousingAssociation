<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Apartment</title>
</head>
<body>

<div class="row">
    <div class="col s12">
        <h2>Details</h2>
        <span>${apartment.displayName()}</span>
        <br/>
        <span>${apartment.block.address.city}</span>
    </div>
</div>

<div class="row">
    <div class="col s6">
        <h2>Bills</h2>
        <table class="striped centered responsive-table">
            <thead>
            <tr>
                <th>Type</th>
                <th>Date</th>
                <th>Amount</th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${apartment.bills.isEmpty()}">
                <td colspan="3">No bills</td>
            </c:if>
            <c:forEach items="${apartment.bills}" var="bill">
                <tr>
                    <td>${bill.type}</td>
                    <td>${bill.date}</td>
                    <td>${bill.amount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col s6">
        <h2>Occupants</h2>
        <table class="striped centered responsive-table">
            <thead>
            <tr>
                <th>Full name</th>
                <th>Email</th>
                <th>Telephone</th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${apartment.occupants.isEmpty()}">
                <td colspan="3">Apartment is empty</td>
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

