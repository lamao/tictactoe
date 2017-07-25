/**
 * Created by invenit on 25.07.17.
 */
app.controller('gameListController', ['$scope', '$http', '$q', function($scope, $http, $q) {
  $q.all([
      $http.get('/api/game').then(function(response) {
      $scope.items = response.data;
    }),
    $http.get('/api/state').then(function(response) {
      $scope.states = response.data;
    })
  ]).then(function() {
    _.each($scope.items, function(it) {
      _.extend(it, {
        state: _.findWhere($scope.states, {code: it.stateCode})
      });
    });
  });

}]);

app.controller('gameCardController', ['$scope', '$http', '$q', '$routeParams', function($scope, $http, $q, $routeParams) {
  $q.all([
    $http.get('/api/game/' + $routeParams.id).then(function(response) {
      $scope.item = response.data;
    }),
    $http.get('/api/state').then(function(response) {
      $scope.states = response.data;
    })
  ]).then(function() {
    _.extend($scope.item, {
      state: _.findWhere($scope.states, {code: $scope.item.stateCode})
    });
  });
}]);