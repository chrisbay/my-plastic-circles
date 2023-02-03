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

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th><c:out value="Name" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="manufacturer" items="${manufacturers}">
                        <tr>
                            <td><c:out value="${manufacturer.name}" /></td></tr>
                    </c:forEach>
                </tbody>
            </table>

        </main>

    </body>
</html>