<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>App user page</title>
</head>
<body>
<div class="row">
    <div class="col s3"></div>
    <form:form method="post"
               action="login.html"
               modelAttribute="credentials"
               cssClass="col s6">
        <h1>Login</h1>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="email" id="email" type="email" cssClass="validate"/>
                <form:label path="email" for="email">Email</form:label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s12">
                <form:input path="password" id="password" type="password" cssClass="validate"/>
                <form:label path="password" for="password">Password</form:label>
            </div>
        </div>
        <button class="btn waves-effect waves-light" type="submit" name="action">Login
            <i class="material-icons right">send</i>
        </button>
    </form:form>
    <div class="col s3"></div>
</div>
</body>
</html>

