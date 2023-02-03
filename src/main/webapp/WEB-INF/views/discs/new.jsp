<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@ include file="/WEB-INF/views/shared/head.jsp" %>
    </head>
    <body>

        <%@ include file="/WEB-INF/views/shared/navbar.jsp" %>

        <main class="container-fluid">

            <div class="row justify-content-center">
                <div class="col-6">

                    <h1>New Disc</h1>

                    <sf:form method="POST" modelAttribute="disc">

                        <div class="mb-3">
                            <label class="form-label" for="name-input">Model</label>
                            <sf:input path="model" id="name-input" class="form-control" />
                            <sf:errors path="model" cssClass="form-error" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label" for="name-input">Manufacturer</label>
                            <sf:input path="manufacturer" id="name-input" class="form-control" />
                            <sf:errors path="manufacturer" cssClass="form-error" />
                        </div>

                        <div class="mb-3">
                            <div class="row">
                                <div class="col-3">
                                    <label class="form-label" for="name-input">Speed</label>
                                    <sf:input path="speed" type="number" id="name-input" class="form-control" />
                                    <sf:errors path="speed" cssClass="form-error" />
                                </div>
                                <div class="col-3">
                                    <label class="form-label" for="name-input">Glide</label>
                                    <sf:input path="glide" type="number" id="name-input" class="form-control" />
                                    <sf:errors path="glide" cssClass="form-error" />
                                </div>
                                <div class="col-3">
                                    <label class="form-label" for="name-input">Turn</label>
                                    <sf:input path="turn" type="number" id="name-input" class="form-control" />
                                    <sf:errors path="turn" cssClass="form-error" />
                                </div>
                                <div class="col-3">
                                    <label class="form-label" for="name-input">Fade</label>
                                    <sf:input path="fade" type="number" id="name-input" class="form-control" />
                                    <sf:errors path="fade" cssClass="form-error" />
                                </div>
                            </div>
                        </div>

                        <button type="submit" class="btn btn-success">Create</button>
                    </sf:form>

                </div>
            </div>

        </main>

    </body>
</html>