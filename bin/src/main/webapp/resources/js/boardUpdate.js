const app = angular.module('hastarApp',[]);

app.controller('MainCtrl',function($scope,$http){
	$scope.boardData={};
	$scope.name="Board New";
	$scope.link=document.location.href.split("/");
	$scope.viewN = document.location.href.split("/")[$scope.link.length-1];
	
	$scope.getBoardData=function(){
		$http({
			url:"/view/"+viewN,
			method:"POST",
			params:$scope.dataInfo
		}).then(function(data){
			console.log("DATA : ",data.data);
		}).catch(function(err){
			console.log("ERR! : ",err);
		});
	}
	$scope.getBoardData();
});