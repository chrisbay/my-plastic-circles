(function(){
    'use strict';

    angular
        .module('discsApp')
        .factory('DiscService', DiscService);

    DiscService.$inject = ['$http', '$q'];

    function DiscService($http, $q) {
        const REST_SERVICES_URL = 'http://localhost:8080/api';

        const factory = {
            fetchAllDiscs: fetchAllDiscs,
            toggleFavoriteStatus: toggleFavoriteStatus,
            updateDisc: updateDisc
        };

        function fetchAllDiscs() {
            const deferred = $q.defer();
            $http.get(REST_SERVICES_URL + '/disc')
                .then(function(res) {
                    deferred.resolve(res.data);
                }, function(err) {
                    console.error('Error fetching all discs');
                    deferred.reject(err);
                });
            return deferred.promise;
        };

        function updateDisc(disc) {
            const deferred = $q.defer();
            $http.put(REST_SERVICES_URL + '/disc/' + disc.id, disc)
                .then(function(res) {
                    deferred.resolve(res.data);
                }, function(err) {
                    console.error(err);
                    deferred.reject(err);
                });
            return deferred.promise;
        };

        function toggleFavoriteStatus(disc) {
            disc.favorite = !disc.favorite;
            const promise = updateDisc(disc)
                .then(function(data) {
                    return data;
                }, function (err) {
                    disc.favorite = !disc.favorite;
                    promise.reject(err);
                });
            return promise;
        }

        return factory;
    }
})();