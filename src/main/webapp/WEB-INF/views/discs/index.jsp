<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@ include file="/WEB-INF/views/shared/head.jsp" %>
    </head>
    <body>

        <%@ include file="/WEB-INF/views/shared/navbar.jsp" %>

        <main class="container-fluid">

            <a class="btn btn-primary" href="/discs/new">Add Disc</a>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th></th>
                        <th>Manufacturer</th>
                        <th>Model</th>
                        <th>Flight Numbers</th>
                        <th>Notes</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="disc" items="${discs}">
                        <tr>
                            <td>
                            <c:choose>
                                <c:when test="${disc.favorite}">
                                    <i class='fa-solid fa-star'></i>
                                </c:when>
                                <c:otherwise>
                                    <i class='fa-regular fa-star'></i>
                                </c:otherwise>
                            </c:choose>
                            </td>
                            <td><c:out value="${disc.manufacturer.name}" /></td>
                            <td><c:out value="${disc.model}" /></td>
                            <td><c:out value="${disc.speed}" /> / <c:out value="${disc.glide}" /> / <c:out value="${disc.turn}" /> / <c:out value="${disc.fade}" /></td>
                            <td><c:out value="${disc.notes}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </main>

    </body>
</html>