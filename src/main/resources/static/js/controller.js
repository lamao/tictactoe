app.controller('gameCardController', ['$scope', '$routeParams', function($scope, $routeParams) {
  $scope.headingTitle = "Game card #" + $routeParams.id;
}]);