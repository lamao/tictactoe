/**
 * Created by invenit on 25.07.17.
 */
app.controller('gameListController', ['$scope', '$http', function($scope, $http) {
  $http.get('/api/game').then(function(response) {
    $scope.items = response.data;
  });

}]);