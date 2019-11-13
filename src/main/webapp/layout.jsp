<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="th" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><th:insertAttribute name="title" ignore="true"/></title>
    <%--Materialize setup--%>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/materialize/css/materialize.css"
          media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <style type="text/css">
        [type="checkbox"]:not(:checked), [type="checkbox"]:checked {
            position: relative;
            opacity: 1;
            pointer-events: auto;
        }
    </style>
</head>
<body>
<ul id="administratorMenu" class="dropdown-content">
    <li><a href="/admin/users">Users</a></li>
    <li class="divider"></li>
    <li><a href="/admin/blocks">Blocks</a></li>
</ul>
<ul id="moderatorMenu" class="dropdown-content">
    <li><a href="/moderator/occupants">Occupants</a></li>
    <li><a href="/moderator/bills">Bills</a></li>
</ul>
<ul id="dropdownMenu" class="dropdown-content">
    <li><a href="/page/user/list">Users</a></li>
    <li><a href="/page/user/register">Register user</a></li>
    <li class="divider"></li>
    <li><a href="/page/block/list">Blocks</a></li>
    <li><a href="/page/block/add">Add block</a></li>
</ul>
<nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container">
        <a id="logo-container" href="/homepage" class="brand-logo">HOUSING ASSOCIATION</a>
        <sec:authorize access="isAuthenticated()">

            <ul class="right hide-on-med-and-down">
                <li>
                    <a class="dropdown-trigger" href="#!" data-target="dropdownMenu">
                        Menu<i class="material-icons right">arrow_drop_down</i>
                    </a>
                </li>
                <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                    <li>
                        <a class="dropdown-trigger" href="#!" data-target="administratorMenu">
                            Admin<i class="material-icons right">arrow_drop_down</i>
                        </a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasAuthority('MODERATOR')">
                    <li>
                        <a class="dropdown-trigger" href="#!" data-target="moderatorMenu">
                            Moderator<i class="material-icons right">arrow_drop_down</i>
                        </a>
                    </li>
                </sec:authorize>
                <li>
                    <sec:authentication property="principal.username"/>
                </li>
                <li>
                    <a href="/perform_logout">Logout</a>
                </li>
            </ul>
            <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
        </sec:authorize>
    </div>
</nav>

<div class="container">
    <th:insertAttribute name="content"/>
</div>

<footer class="page-footer orange">
    <th:insertAttribute name="footer"/>
</footer>

<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/materialize/js/materialize.min.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $(".dropdown-trigger").dropdown();
    });
</script>

</body>
</html>

