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
    <div class="col s6">
        <h2>Details</h2>
        <span>${block.displayName()}</span>
        <br/>
        <span>${block.address.city}</span>
        <br />
        <span>Apartments: ${block.apartments.size()}</span>
    </div>
</div>

<div class="row">
    <div class="col s5">
        <h3>Apartments:</h3>
        <table class="striped centered responsive-table">
            <thead>
            <tr>
                <th>Address</th>
                <th>Occupants</th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${block.apartments.isEmpty()}">
                <td colspan="5">No Apartments Available</td>
            </c:if>
            <c:forEach items="${block.apartments}" var="apartment">
                <tr>
                    <td>${apartment.displayName()}</td>
                    <td>${apartment.occupants.size()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col s1"></div>
    <div class="col s5">
        <h3>Add Apartment</h3>
        <form:form method="post"
                   action="/page/block/details/${block.id}/addApartment.html"
                   modelAttribute="apartment">
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
</div>

</body>
</html>

