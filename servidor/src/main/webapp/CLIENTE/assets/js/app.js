var app = angular.module("UrbanEDD", []);
        
const base = 'http://localhost:8080/UrbanEDD/webresources';

app.controller('ControlMapa', function($scope,$http,$log) {
    $scope.generarRutaMinima = (codigoOrigen,codigoDestino) => {
        $http.post(
            base + '/rutas/critico',
            {
                codOrigen: codigoOrigen,
                codDestino: codigoDestino
            }
        ).then(
            (response) => 
            {
            },
            (error) => alert(error)
        )
    };

});


app.controller('ControlRutas',function($scope,$http){
    $scope.rutasD = [];

    $http.get(base+'/rutas/clientes').then(function(response){
            $scope.rutasD = response.data;
         });
         
    $scope.currentName;
    $scope.currentPrice;
    $scope.currentCode;
    
    $scope.mostrarInfo = (precio,nombre,codigo) =>{
        $scope.currentName = ""+nombre;
        $scope.currentPrice = ""+precio;
        $scope.currentCode = ""+codigo;
        
    };
    
}
            

    
);