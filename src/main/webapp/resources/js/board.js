const app = angular.module('hastarApp',[]);

app.controller('MainCtrl',function($scope,$http){
	$scope.boardData={};
	$scope.name="Board CRUD test";
	
	$scope.intoDetail = function(inputNo){
		location.href="/view/"+inputNo;
	}
	
	$scope.createNew= function(){
		location.href="/createBoard";
	}
	
});