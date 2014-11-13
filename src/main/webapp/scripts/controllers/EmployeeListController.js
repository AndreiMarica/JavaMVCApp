hrApp.controller('EmployeeListController', ['$scope', '$http', '$location', function($scope, $http, $location) {
    $scope.employees = [];
    $http({url: 'http://localhost:8080/app/mvc/employee/all', method: 'GET'}).
        success(function(data, status, headers, config) {
            $scope.employees = data;
        });
    $scope.viewEmployee = function(id) {
        $location.url('/employeeview/'+id);
    };
    $scope.editEmployee = function(id) {
        $location.url('/employeeedit/'+id);
    };
    $scope.deleteEmployee = function(id) {
        $location.url('/employeedelete/'+id);
    };
}]);