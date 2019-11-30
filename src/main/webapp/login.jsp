<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title><spring:message code="header.login"/></title>
</head>
<body onload="document.login_form.email.focus()">
<div class="row">
    <div class="col s3"></div>
    <form id="login_form"
          method="POST"
          action="perform_login"
          class="col s6">
        <h1><spring:message code="header.login"/></h1>

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
        <button class="btn waves-effect waves-light" type="submit"><spring:message code="button.login"/>
            <i class="material-icons right">send</i>
        </button>
    </form>
    <div class="col s3"></div>
</div>
</body>
</html>

