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

                    <p class="required-msg"><i class="fa-solid fa-asterisk required" title="Required"></i>Required</p>

                    <sf:form method="POST" modelAttribute="disc">

                        <div class="mb-3">
                            <label class="form-label" for="model-input">Model<i class="fa-solid fa-asterisk required" title="Required"></i></label>
                            <sf:input path="model" id="model-input" class="form-control" />
                            <sf:errors path="model" cssClass="form-error" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label" for="manufacturer-input">Manufacturer<i class="fa-solid fa-asterisk required" title="Required"></i></label>
                            <select name="manufacturerId" id="manufacturer-input" class="form-control">
                                <c:forEach var="manufacturer" items="${manufacturers}">
                                    <option value="${manufacturer.id}"><c:out value="${manufacturer.name}" /></option>
                                </c:forEach>
                            </select>
                            <sf:errors path="manufacturer" cssClass="form-error" />
                        </div>

                        <div class="mb-3">
                            <div class="row">
                                <div class="col-3">
                                    <label class="form-label" for="speed-input">Speed<i class="fa-solid fa-asterisk required" title="Required"></i></label>
                                    <sf:input path="speed" type="number" id="speed-input" class="form-control" />
                                    <sf:errors path="speed" cssClass="form-error" />
                                </div>
                                <div class="col-3">
                                    <label class="form-label" for="glide-input">Glide<i class="fa-solid fa-asterisk required" title="Required"></i></label>
                                    <sf:input path="glide" type="number" id="glide-input" class="form-control" />
                                    <sf:errors path="glide" cssClass="form-error" />
                                </div>
                                <div class="col-3">
                                    <label class="form-label" for="turn-input">Turn<i class="fa-solid fa-asterisk required" title="Required"></i></label>
                                    <sf:input path="turn" type="number" id="turn-input" class="form-control" />
                                    <sf:errors path="turn" cssClass="form-error" />
                                </div>
                                <div class="col-3">
                                    <label class="form-label" for="fade-input">Fade<i class="fa-solid fa-asterisk required" title="Required"></i></label>
                                    <sf:input path="fade" type="number" id="fade-input" class="form-control" />
                                    <sf:errors path="fade" cssClass="form-error" />
                                </div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <div class="mb-3">
                                <label class="form-label" for="notes-input">Notes</label>
                                <sf:textarea path="notes" id="notes-input" class="form-control" />
                                <sf:errors path="notes" cssClass="form-error" />
                            </div>
                        </div>

                        <button type="submit" class="btn btn-success"><i class="fa-solid fa-plus pe-2"></i>Create</button>
                    </sf:form>

                </div>
            </div>

        </main>

    </body>
</html>