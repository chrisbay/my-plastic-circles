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

                    <h1>New Manufacturer</h1>

                    <p class="required-msg"><i class="fa-solid fa-asterisk required" title="Required"></i>Required</p>

                    <sf:form method="POST" modelAttribute="discManufacturer">

                        <div class="mb-3">
                            <label class="form-label" for="name-input">Name<i class="fa-solid fa-asterisk required" title="Required"></i></label>
                            <sf:input path="name" id="name-input" class="form-control" />
                            <sf:errors path="name" cssClass="form-error" />
                        </div>

                        <button type="submit" class="btn btn-success"><i class="fa-solid fa-plus pe-2"></i>Create</button>
                    </sf:form>

                </div>
            </div>

        </main>

    </body>
</html>