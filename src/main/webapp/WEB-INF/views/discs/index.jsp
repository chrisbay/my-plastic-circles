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

            <a class="btn btn-primary" href="/discs/new">Add Disc</a>

            <div ng-controller="DiscController as collection">
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
                        <tr ng-repeat="disc in this.discs">
                            <td>
                                <i ng-click="collection.toggleFavoriteStatus(disc)"
                                   ng-class="disc.favorite ? 'fa-solid fa-star' : 'fa-regular fa-star'"></i>
                            </td>
                            <td>{{disc.manufacturer.name}}</td>
                            <td>{{disc.model}}</td>
                            <td>{{disc.speed}} / {{disc.glide}} / {{disc.turn}} / {{disc.fade}}</td>
                            <td>{{disc.notes}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>

        </main>

        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        <script src="/static/js/app.js"></script>
        <script src="/static/js/disc/disc.service.js"></script>
        <script src="/static/js/disc/disc.controller.js"></script>

    </body>
</html>