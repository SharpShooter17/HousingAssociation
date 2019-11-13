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
    <div class="col s12">
        <h3>Blocks:</h3>
        <table class="striped centered responsive-table">
            <thead>
            <tr>
                <th>City</th>
                <th>Address</th>
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

