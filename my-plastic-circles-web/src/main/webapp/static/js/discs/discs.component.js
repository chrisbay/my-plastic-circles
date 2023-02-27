(function(){

    'use strict';

    angular
        .module('discsApp')
        .component('discs', {
            templateUrl: '/static/js/discs/discs.template.html',
            controller: DiscController
        });

    function DiscController ($scope, DiscService) {
        const self = this;
        self.discs = [];

        self.toggleFavoriteStatus = toggleFavoriteStatus;

        fetchAllDiscs();

        function fetchAllDiscs() {
            DiscService.fetchAllDiscs()
                .then(function(data) {
                      $scope.discs = data;
                      return $scope.discs;
                }, function (err) {
                    console.error("Error while fetching discs");
                });
        }

        function toggleFavoriteStatus(disc) {
            DiscService.toggleFavoriteStatus(disc)
                .then(function(data) {
                    return data;
                }, function (err) {
                    alert("Error updating disc");
                });
        };
    }

})();