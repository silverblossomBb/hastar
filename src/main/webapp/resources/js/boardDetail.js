const app = angular.module('hastarApp',[]);

app.controller('MainCtrl',function($scope,$http){
	$scope.boardData={};
	$scope.name="Board Detail-";
	
	$scope.selectOnePost = function(){
		let link = document.location.href.split("/");
		let viewN = document.location.href.split("/")[link.length-1];
		$http({
			url:"/view/"+viewN,
			method:"POST"
		}).then(function(data){
			console.log("data : ",data);
			$scope.boardData=data.data;
		}).catch(function(err){
			console.log("ERR! : ",err);
		});
	}
	
	$scope.selectOnePost();
});