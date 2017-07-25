app.controller('gameListController', function($scope) {
  $scope.headingTitle = "Game list";
});

app.controller('gameCardController', ['$scope', '$routeParams', function($scope, $routeParams) {
  $scope.headingTitle = "Game card #" + $routeParams.id;
}]);