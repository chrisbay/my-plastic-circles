'use strict';

discsApp.controller('DiscController',
    ['$scope', 'DiscService', function ($scope, DiscService) {
        const self = $scope;
        $scope.discs = [];

        fetchAllDiscs();

        function fetchAllDiscs() {
            DiscService.fetchAllDiscs()
                .then(function(data) {
                      $scope.discs = data;
                }, function (err) {
                    console.error("Error while fetching discs");
                });
        }

        $scope.toggleFavorite = function (disc) {
            disc.favorite = !disc.favorite;
            DiscService.updateDisc(disc)
                .then(function(data) {
                    // do nothing
                }, function (err) {
                    disc.favorite = !disc.favorite;
                    alert("Error updating disc");
                });
        };
    }]
);