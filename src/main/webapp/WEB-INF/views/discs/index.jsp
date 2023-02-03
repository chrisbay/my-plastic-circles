<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>My Plastic Circles</title>

        <link rel="stylesheet" href="/webjars/bootstrap/5.2.3/css/bootstrap.min.css">
        <script src="/webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="/webjars/font-awesome/6.2.1/css/fontawesome.min.css">

        <link rel="stylesheet" href="/static/css/site.css">
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-secondary shadow-sm mb-4">
            <div class="container-fluid">
                <a class="navbar-brand" href="/">My Plastic Circles</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav  me-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link" href="/discs">My Discs</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Third Page</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <main class="container-fluid">

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th><c:out value="Model" /></th>
                        <th><c:out value="Manufacturer" /></th>
                        <th><c:out value="Flight Numbers" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="disc" items="${discs}">
                        <tr>
                            <td><c:out value="${disc.manufacturer}" /></td>
                            <td><c:out value="${disc.model}" /></td>
                            <td><c:out value="${disc.speed}" /> / <c:out value="${disc.glide}" /> / <c:out value="${disc.turn}" /> / <c:out value="${disc.fade}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </main>

    </body>
</html>