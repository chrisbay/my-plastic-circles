'use strict';

discsApp.factory('DiscService', ['$http', '$q',

    function ($http, $q) {

        const REST_SERVICES_URL = 'http://localhost:8080/api';

        const factory = {};

        factory.fetchAllDiscs = function() {
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

        factory.updateDisc = function(disc) {
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

        return factory;
    }

]);