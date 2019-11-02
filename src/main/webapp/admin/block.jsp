<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Block</title>
</head>
<body>

<div class="row">
    <div class="col s3"></div>
    <div class="col s6">
        <h1>Details</h1>
        <span>${block.address.zipCode} City: ${block.address.city}</span>
        <br/>
        <span>Street: ${block.address.street} Number: ${block.address.number}</span>
    </div>
    <div class="col s3"></div>
</div>

<div class="row">
    <div class="col s3"></div>
    <div class="col s6">
        <h1>Add Apartment</h1>
        <form:form method="post" action="/admin/addApartment.html" modelAttribute="apartment">
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="number">Number</form:label>
                    <form:input path="number"/>
                </div>
            </div>

            <div class="row">
                <button class="btn waves-effect waves-light" type="submit">Add Apartment
                    <i class="material-icons right">send</i>
                </button>
            </div>
        </form:form>
    </div>
    <div class="col s3"></div>
</div>

<div class="row">
    <div class="col s2"></div>
    <div class="col s8">
        <h3>Apartments:</h3>
        <table class="striped centered responsive-table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Number</th>
                <th>Occupants</th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${block.apartments.isEmpty()}">
                <td colspan="5">No Apartments Available</td>
            </c:if>
            <c:forEach items="${block.apartments}" var="apartment">
                <tr>
                    <td>${apartment.id}</td>
                    <td>${apartment.number}</td>
                    <td>${apartment.occupants.size()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col s2"></div>
</div>

</body>
</html>

