var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($locationProvider, $routeProvider) {

  $locationProvider.hashPrefix('');

  $routeProvider
    .when('/games', {
      templateUrl: '/views/game-list.html',
      controller: 'gameListController'
    })
    .when('/game/:id', {
      templateUrl: '/views/game-card.html',
      controller: 'gameCardController'
    })
    .otherwise(
      { redirectTo: '/'}
    );
});