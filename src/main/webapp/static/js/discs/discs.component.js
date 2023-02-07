(function(){

    'use strict';

    angular
        .module('discsApp')
        .component('discs', {
            template: `<table class="table table-striped">
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
                           <tr ng-repeat="disc in discs">
                               <td>
                                   <i ng-click="$ctrl.toggleFavoriteStatus(disc)"
                                      ng-class="disc.favorite ? 'fa-solid fa-star' : 'fa-regular fa-star'"></i>
                               </td>
                               <td>{{disc.manufacturer.name}}</td>
                               <td>{{disc.model}}</td>
                               <td>{{disc.speed}} / {{disc.glide}} / {{disc.turn}} / {{disc.fade}}</td>
                               <td>{{disc.notes}}</td>
                           </tr>
                           </tbody>
                       </table>`,
            controller: DiscController
        });

    DiscController.$inject = ['$scope', 'DiscService'];

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