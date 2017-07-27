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
    _.each($scope.items, enrichGame);
  });

  $scope.onShowAddForm = function() {
    $scope.isShowAddForm = true;
    $scope.newItem = {};
  };

  $scope.onSave = function() {
    $http.post('/api/game', $scope.newItem).then(function(response) {
      $scope.isShowAddForm = false;
      $scope.items.push(enrichGame(response.data));
    });
  };

  function enrichGame(item) {
    return _.extend(item, {
      state: _.findWhere($scope.states, {code: item.stateCode})
    });
  }

}]);

app.controller('gameCardController', ['$scope', '$http', '$q', '$routeParams', function($scope, $http, $q, $routeParams) {
  $http.get('/api/game/' + $routeParams.id).then(function(response) {
    $scope.item = response.data;

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