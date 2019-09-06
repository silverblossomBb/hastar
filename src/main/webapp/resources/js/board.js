const app = angular.module('hastarApp',[]);

app.controller('MainCtrl',function($scope,$http){
	$scope.storage={};
	$scope.boardData={};
	$scope.name="Board CRUD test";
	
	$scope.getAllData = function(){
		$http({
			url:"/selectAllData",
			method:"POST"
		}).then(function(data){
			console.log("data : ",data);
			$scope.storage=data.data;
		}).catch(function(err){
			console.log("ERR! : ",err);
		});
	}
	
	$scope.intoDetail = function(inputNo){
		location.href="/view/"+inputNo;
	}
	
	$scope.createNew= function(){
		location.href="/createBoard";
	}
	$scope.getAllData();
});