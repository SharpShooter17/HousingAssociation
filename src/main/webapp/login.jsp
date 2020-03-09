<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="header.login"/></title>
</head>
<body onload="document.login_form.email.focus()">
<div class="row">
    <div class="col s2"></div>
    <div class="col s8">
        <h1><spring:message code="header.login"/></h1>
        <c:if test="${not empty param.error}">
            <div class="row">
                <div class="card-panel red lighten-1 col s12">
                    <spring:message code="error.invalid.credentials"/>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty param.logout}">
            <div class="row">
                <div class="card-panel blue lighten-4 col s12">
                    <spring:message code="success.logout"/>
                </div>
            </div>
        </c:if>
        <form id="login_form"
              method="POST"
              action="perform_login">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            <div class="row">
                <div class="input-field col s12">
                    <input id="email" name="email" type="email" class="validate"/>
                    <label for="email"><spring:message code="label.email"/></label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <input id="password" name="password" type="password" class="validate"/>
                    <label for="password"><spring:message code="label.password"/></label>
                </div>
            </div>
            <button class="btn waves-effect waves-light" type="submit" id="login-submit">
                <spring:message code="button.login"/>
                <i class="material-icons right">send</i>
            </button>
        </form>
    </div>
</div>
</body>
</html>

