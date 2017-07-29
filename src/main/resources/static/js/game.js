/**
 * Created by invenit on 25.07.17.
 */
app.controller('gameListController', ['$scope', '$http', '$q', 'constants', function($scope, $http, $q, constants) {
  $scope.constants = constants;

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

app.controller('gameCardController', ['$scope', '$http', '$q', '$routeParams', 'constants', function($scope, $http, $q, $routeParams, constants) {

  $http.get('/api/game/' + $routeParams.id).then(function (response) {
    $scope.item = response.data;
    $scope.nextTurnSymbol = getNextTurnSymbol();

    $scope.isLastTurn = function(x, y) {
      var lastTurn = $scope.item.lastTurn;
      if (lastTurn) {
        return x == lastTurn.x && y == lastTurn.y;
      } else {
        return false;
      }
    };

    $scope.onClick = function (x, y) {
      var item = $scope.item;
      if (item.state.code == constants.STATES.IN_PROGRESS) {
        var cell = item.snapshot[y][x];

        if (constants.BOARD.CELL.EMPTY == cell) {
          var body = {x: x, y: y};
          $http.post('/api/game/' + item.id + '/make-turn', body)
            .then(function (response) {
              item.lastTurn = body;
              item.snapshot[y][x] = $scope.nextTurnSymbol;
              $scope.nextTurnSymbol = getNextTurnSymbol();

              var newState = response.data;
              $scope.item.state = newState;
              if (newState.code != constants.STATES.IN_PROGRESS) {
                alert('Game finished. ' + newState.title);
              }
            })
          ;
        }
      }
    };
  });

  // TODO: return from BE
  function getNextTurnSymbol () {
    var result = constants.BOARD.CELL.X;
    if (getLastTurnSymbol() == constants.BOARD.CELL.X) {
      result = constants.BOARD.CELL.O;
    }
    return result;
  }

  function getLastTurnSymbol() {
    var item = $scope.item;
    var result = null;
    if (item.lastTurn) {
      result = item.snapshot[item.lastTurn.y][item.lastTurn.x]
    }
    return result;
  }
}]);