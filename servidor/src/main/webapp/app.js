/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module("UrbanEDD", []);

const base = 'http://localhost:8080/servidor-1.0-SNAPSHOT/webresources';

app.controller('ControlPrincipal', function($scope,$window,$http) {
        $scope.countstd = 0;
        $scope.countprm = 0;
        $scope.countfll = 0;
        $scope.countnls = 0;
        
        $http.get(base + '/ticket').then(function successCallback(response) {
                $scope.countstd = response.data.countstd;
                $scope.countprm = response.data.countprm;
                $scope.countfll = response.data.countfll;
                $scope.countnls = response.data.countnls;
                
            }, function errorCallback(response) {
                alert(response.status);
        });
        
  	$scope.redireccionTickets = function(){
            $window.location.href="Tickets.html";
	};
        
        $scope.redireccionHome = function(){
            $window.location.href="index.html";
	};
        
        
        
    }
);
