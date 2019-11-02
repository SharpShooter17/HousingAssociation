<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="row">
    <div class="col s3"></div>
    <form id="login-form"
          method="POST"
          action="perform_login"
          class="col s6">
        <h1>Login</h1>
        <div class="row">
            <div class="input-field col s12">
                <input id="email" name="email" type="email" class="validate"/>
                <label for="email">Email</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s12">
                <input id="password" name="password" type="password" class="validate"/>
                <label for="password">Password</label>
            </div>
        </div>
        <button class="btn waves-effect waves-light" type="submit">Login
            <i class="material-icons right">send</i>
        </button>
    </form>
    <div class="col s3"></div>
</div>
</body>
</html>

