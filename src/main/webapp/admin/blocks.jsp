<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Blocks</title>
</head>
<body>
<div class="row">
    <div class="col s3"></div>
    <div class="col s6">
        <h1>Add Block</h1>
        <form:form method="post" action="/admin/addBlock.html" modelAttribute="address">
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="city">City</form:label>
                    <form:input path="city"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="zipCode">Zip Code</form:label>
                    <form:input path="zipCode"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="street">Street</form:label>
                    <form:input path="street"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <form:label path="number">Number</form:label>
                    <form:input path="number"/>
                </div>
            </div>
            <div class="row">
                <button class="btn waves-effect waves-light" type="submit">Add Block
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
        <h3>Blocks:</h3>
        <table class="striped centered responsive-table">
            <thead>
            <tr>
                <th>City</th>
                <th>Zip Code</th>
                <th>Street</th>
                <th>Number</th>
                <th>Apartments</th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${blocks.isEmpty()}">
                <td colspan="5">No Block Available</td>
            </c:if>
            <c:forEach items="${blocks}" var="block">
                <tr>
                    <td>${block.address.city}</td>
                    <td>${block.address.zipCode}</td>
                    <td>${block.address.street}</td>
                    <td>${block.address.number}</td>
                    <td>${block.apartments.size()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col s2"></div>
</div>

</body>
</html>

