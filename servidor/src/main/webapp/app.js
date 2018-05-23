/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module("UrbanEDD", []);

const base = 'http://localhost:8080/UrbanEDD/webresources';

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
    
    $scope.redireccionAbordajes = function(){
        $window.location.href="Abordajes.html";
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

app.controller('ControlAbordajes',function($scope,$http,$log){
    $scope.abordajes = [];
    $http.get(base+'/ticket/abordajes').then(function(response){
            $scope.abordajes = response.data;
         });
    }
);


app.controller('ControlEstaciones', function($scope,$http,$log) {
    $scope.estacionesD = [];
    $scope.path = "";
    $scope.getEstaciones = () => {
        $http.get(base+'/estaciones').then(function(response)
        {
            $scope.estacionesD = response.data;
        }
    )};

    $scope.registrarEstacion = (codigo,nombre,latitud,longitud) => {
        $log.info("Codigo: "+codigo+"\nNombre: "+nombre+"\nLatitud: "+latitud+"\nLongitud: "+longitud);
        $http.post(
            base + '/estaciones',
            {
                codEstacion: codigo,
                nomEstacion: nombre,
                latitud: latitud,
                longitud: longitud
            }
        ).then(
            (response) => 
            {
                $scope.getEstaciones();
                alert(response.data);
            },
            (error) => alert(error)
        )
    };

    }
);


app.controller('ControlRutas', function($scope,$http,$log) {

    $scope.rutasD = [];

    $scope.getRutas = () => {
        $http.get(base+'/rutas').then(function(response)
        {
            $scope.rutasD = response.data;
            $log.info(response.data);
        }
    )};
        
    $scope.verRuta = (codigo) => {
        $log.info("Codigo: " + codigo);
        $http.post(
            base + '/rutas/generarGrafo', 
            {
                codigoRuta: codigo,
                nombreRuta: "nombre",
                colorRuta: "color",
                valorRuta: 0.0,
                grafo: null
            }
        ).then(
            (response) => console.log(response.R), 
            (error) => $log.error(error)
        );
    }


    $scope.agregarRuta = (codigo,nombre,color,valor) => {
        $log.info("Codigo: "+codigo+"\nNombre: "+nombre+"\nColor: "+color);
        $http.post(
            base + '/rutas', 
            { 
                codigoRuta: codigo,
                nombreRuta: nombre,
                colorRuta: color,
                valorRuta: valor,
                grafo: null
            }
        ).then(
            (response) => 
            {
                $scope.getRutas();
                alert(response.data);
            }, 
            (error) => alert(error)
        )
    };

    $scope.agregarRecorrido = (ruta,codOrigen,codDestino,distancia,trafico) => {
        $log.info("CodigoRuta:"+ruta+"\nCodigoOrigen: "+codOrigen+"\nCodigoDestino: "+codDestino+"\nDistancia: "+distancia+"\nTrafico: "+trafico);
        $http.post(
            base + '/rutas/recorrido', 
            { 
                codRuta: ruta,
                codOrigen: codOrigen,
                codDestino: codDestino,
                distancia: distancia,
                trafico: trafico 
            }
        ).then(
            (response) => 
            {
                alert(response.data);
            }, 
            (error) => $log.error(error)
        );
    }


    }
);
