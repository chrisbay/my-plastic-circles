<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" ng-app="discsApp">
    <head>
        <%@ include file="/WEB-INF/views/shared/head.jsp" %>
    </head>
    <body>

        <%@ include file="/WEB-INF/views/shared/navbar.jsp" %>

        <main class="container-fluid">

            <h1>My Collection</h1>

            <a class="btn btn-primary" href="/discs/new">Add Disc</a>

            <discs></discs>

        </main>

        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        <script src="/static/js/app.module.js"></script>
        <script src="/static/js/discs/discs.service.js"></script>
        <script src="/static/js/discs/discs.component.js"></script>

    </body>
</html>