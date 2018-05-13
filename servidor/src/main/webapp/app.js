/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module("UrbanEDD", []);

app.controller('CompraTickets', function CompraTickets($scope,$window) {
  $scope.tickets = [
		{
			nombre: 'Standard',
			codigo: 'STD'
		},
		{ 
			nombre: 'Premium',
			codigo: 'PRM'
		},
		{ 
			nombre: 'Full',
			codigo: 'FLL'
		},
		{ 
			nombre: 'Endless',
			codigo: 'NSS'
		}
  	];

	$scope.redireccionTickets = () => {
            $window.location.href = '/Tickets.html';
	};
    }
);
