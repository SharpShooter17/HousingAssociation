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
    <div class="col s6">
        <h2>Details</h2>
        <span>${apartment.displayName()}</span>
        <br/>
        <span>${apartment.block.address.city}</span>
    </div>
</div>

</body>
</html>

