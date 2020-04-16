<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><spring:message code="header.users"/></title>
</head>
<body>
<div class="row">
    <div class="col s12">
        <h3><spring:message code="header.users"/></h3>
        <table id="userListTable" class="striped centered responsive-table">
            <thead>
            <tr>
                <th><spring:message code="label.name"/></th>
                <th><spring:message code="label.lastname"/></th>
                <th><spring:message code="label.email"/></th>
                <th><spring:message code="label.telephone"/></th>
                <th><spring:message code="label.roles"/></th>
                <th><spring:message code="label.enabled"/></th>
                <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                    <th></th>
                    <th></th>
                </sec:authorize>
            </tr>
            </thead>

            <tbody>
            <c:if test="${users.isEmpty()}">
                <td colspan="6"><spring:message code="label.users-not-available"/></td>
            </c:if>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td><a href="/user/${user.id}">${user.email}</a></td>
                    <td>${user.telephone}</td>
                    <td>
                        <c:forEach items="${user.roles}" var="role">
                            <spring:message code="role.name.${role.name.name()}"/>,
                        </c:forEach>
                    </td>
                    <td><spring:message code="label.enabled-code.${user.isEnabled()}"/></td>
                    <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                        <td><a href="/page/user/edit/${user.id}"><spring:message code="label.edit"/> </a></td>
                        <td><a href="/page/user/remove/${user.id}"><spring:message code="label.remove"/> </a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>

