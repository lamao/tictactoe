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
  $http.get('/api/game/' + $routeParams.id).then(function(response) {
    $scope.item = response.data;
    _.extend($scope.item, {
      lastTurn: {
        x: 1,
        y: 1
      },
      snapshot: [
        [' ', 'o', 'x'],
        [' ', 'x', ' '],
        ['o', ' ', 'x']
      ]
    });

    $scope.isLastTurn = function(x, y) {
      var lastTurn = $scope.item.lastTurn;
      if (lastTurn) {
        return x == lastTurn.x && y == lastTurn.y;
      } else {
        return false;
      }
    }
  });
}]);