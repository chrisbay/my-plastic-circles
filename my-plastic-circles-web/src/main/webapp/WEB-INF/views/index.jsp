<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@ include file="/WEB-INF/views/shared/head.jsp" %>
    </head>
    <body>

        <%@ include file="/WEB-INF/views/shared/navbar.jsp" %>

        <main class="container-fluid">
            <div class="p-5 mb-4 bg-light rounded-3">
                <div class="container-fluid py-5">
                    <h1 class="display-5 fw-bold">Your discs, organized</h1>
                    <p class="col-md-8 fs-4">Keep track of all your discs in one place</p>
                    <a id="get-started" class="btn btn-success btn-lg" href="/discs" role="button">Get Started</a>
                </div>
            </div>
        </main>

    </body>
</html>