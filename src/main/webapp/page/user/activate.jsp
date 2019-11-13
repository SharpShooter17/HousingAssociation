<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Activate account</title>
</head>
<body>
<div class="row">
    <div class="col s3"></div>
    <form:form method="post"
               action="/page/user/activate/account.html"
               modelAttribute="model"
               cssClass="col s6">
        <h1>Activate account</h1>

        <form:input path="userId" id="userId" type="hidden" cssClass="validate"/>

        <div class="row">
            <div class="input-field col s12">
                <form:input path="token" id="token" type="text" cssClass="validate"/>
                <form:label path="token" for="token">Token</form:label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s12">
                <form:input path="password" id="password" type="password" cssClass="validate"/>
                <form:label path="password" for="password">Password</form:label>
            </div>
        </div>
        <button class="btn waves-effect waves-light" type="submit" name="action">Activate account
            <i class="material-icons right">send</i>
        </button>
    </form:form>
    <div class="col s3"></div>
</div>
</body>
</html>

