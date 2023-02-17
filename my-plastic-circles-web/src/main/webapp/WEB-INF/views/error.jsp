<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@ include file="/WEB-INF/views/shared/head.jsp" %>
    </head>
    <body>

        <%@ include file="/WEB-INF/views/shared/navbar.jsp" %>

        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="col-6 text-center">
                    <h1><c:out value="${errorMsg}" /></h1>
                </div>
            </div>
        </div>

    </body>
</html>