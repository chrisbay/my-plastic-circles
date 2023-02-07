<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${pageTitle != null}">
        <c:set var="displayTitle" value="My Plastic Circles - ${pageTitle}" />
    </c:when>
    <c:otherwise>
        <c:set var="displayTitle" value="My Plastic Circles" />
    </c:otherwise>
</c:choose>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><c:out value="${displayTitle}" /></title>

<link rel="stylesheet" href="/webjars/bootstrap/5.2.3/css/bootstrap.min.css">
<script src="/webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/webjars/font-awesome/6.2.1/css/all.min.css">

<style>
.form-error,
.required-msg {
    color: red;
    font-size: 0.9rem;
}
.required {
	font-size: .5rem;
	vertical-align: top;
	color: red;
	margin: 4px 2px;
}
.fa-solid.fa-star {
    color: rgb(255,179,59);
}
</style>