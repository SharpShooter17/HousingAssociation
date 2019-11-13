<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Apartment</title>
</head>
<body>

<div class="row">
    <div class="col s3"></div>
    <div class="col s6">
        <h3>Add Apartment</h3>
        <form:form method="post"
                   action="/page/block/details/${blockId}/apartment/add"
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

