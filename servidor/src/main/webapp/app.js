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
    
    $scope.redireccionReembolsos = function(){
            $window.location.href="Reembolsos.html";
    };
    
    $scope.redireccionHome = function(){
        $window.location.href="index.html";
    };
    
    $scope.redireccionRutas = function(){
        $window.location.href="Rutas.html";
    };

    $scope.redireccionEstaciones = function(){
        $window.location.href="Estaciones.html";
    };  
        
    }
);

app.controller('ControlReembolsos',function($scope,$http,$log){
    $scope.reembolsos = [];
    $http.get(base+'/Reembolsos').then(function(response){
            $scope.reembolsos = response.data;
         });
    }
);


app.controller('ControlEstaciones', function($scope,$http,$log) {
    $scope.estacionesD = [];
    $http.get(base+'/estaciones').then(function(response)
    {
        $scope.estacionesD = response.data;
        alert(response.data);
    },function error(response){
        alert(response.statusText);
    });
/*
    $scope.estacionesD = [
        {
            nombre: 'Est1',
            codigo: 'E12S',
            latitud: '120',
            longitud: '1200'
        },
        {
            nombre: 'Terminal',
            codigo: '10',
            latitud: '100',
            longitud: '100'
        }
    ];*/


    $scope.registrarEstacion = (codigo,nombre,latitud,longitud) => {
        $log.info("Codigo: "+codigo+"\nNombre: "+nombre+"\nLatitud: "+latitud+"\nLongitud: "+longitud);
        $http.post(
            base + '/estaciones', 
            { nombre:codigo }
        ).then(
            (data) => $alert("Estacion Registrada!"), 
            (error) => $log.error(error)
        );
    }   
    }
);

app.controller('ControlRutas', function($scope,$http,$log) {

    $scope.rutasD = [
        {
            nombre: 'Ruta 1',
            codigo: 'R11'
        },
        { 
            nombre: 'Ruta 2',
            codigo: 'R12'
        }
    ];

    $scope.verRuta = (codigo) => {
        $log.info("Codigo: "+codigo);
        $http.post(
            base + '/rutas', 
            { nombre:codigo }
        ).then(
            (data) => $alert("Ok"), 
            (error) => $log.error(error)
        );
    }


    $scope.agregarRuta = (codigo,nombre,color) => {
        $log.info("Codigo: "+codigo+"\nNombre: "+nombre+"\nColor: "+color);
        $http.post(
            base + '/rutas', 
            { nombre:codigo }
        ).then(
            (data) => $alert("Ruta Agregada!"), 
            (error) => $log.error(error)
        );
    }

    $scope.agregarRecorrido = (codOrigen,codDestino,distancia,trafico) => {
        $log.info("CodigoOrigen: "+codOrigen+"\nCodigoDestino: "+codDestino+"\nDistancia: "+distancia+"\nTrafico: "+trafico);
        $http.post(
            base + '/recorrido', 
            { nombre:codOrigen }
        ).then(
            (data) => alert("Recorrido Agregado"), 
            (error) => $log.error(error)
        );
    }


    }
);

