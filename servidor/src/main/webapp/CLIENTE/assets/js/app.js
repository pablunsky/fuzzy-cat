var app = angular.module("UrbanEDD", []);

const base = 'http://localhost:8080/UrbanEDD/webresources';

app.controller('ControlMapa', function($scope,$http,$log) {

    //PETICION PARA OBTENER EL MAPA GENERAL (DE SER NECESARIO)

});


app.controller('ControlRutas',function($scope,$http,$log){
    $scope.rutasD = [];
    $http.get(base+'/rutas/clientes').then(function(response){
            $scope.rutasD = response.data;
         });
    }
);