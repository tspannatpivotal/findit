angular.module('scopeExample', [])
.controller('MyController',function($scope, $http) {
  $scope.greeting = ' ';
  
  $scope.sayHello = function() {
      $http.get('/retrieve/' + $scope.username ).
      success(function(data) {
          $scope.greeting = $scope.username + " works at " + data.value;
      });
  };
});