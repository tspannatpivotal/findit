angular.module('scopeExample', [])
.controller('MyController',function($scope, $http) {
//  $scope.username = 'Black';
  $scope.greeting = ' ';
  
  $scope.sayHello = function() {
      $http.get('http://localhost:8888/retrieve/' + $scope.username ).
      success(function(data) {
          $scope.greeting = $scope.username + " works at " + data.value;
      });
  };
});