/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module("UrbanEDD", []);

app.controller('CompraTickets', function($scope,$window) {
  	$scope.redireccionTickets = function(){
            $window.location.href="Tickets.html";
	};
    }
);
