const app = angular.module('hastarApp',[]);

app.controller('MainCtrl',function($scope,$http){
	$scope.boardData={};
	$scope.storageFile={};
	$scope.isYours=false;
	$scope.link=document.location.href.split("/");
	$scope.viewN = document.location.href.split("/")[$scope.link.length-1];
	
	$scope.selectOnePost = function(){
		$http({
			url:"/view/"+$scope.viewN,
			method:"POST"
		}).then(function(data){
			console.log("data : ",data.data);
			$scope.boardData=data.data;
			console.log("this.data = ",$scope.boardData);
			$scope.getUserInfo();
		}).catch(function(err){
			console.log("ERR! : ",err);
		});
	}
	
	$scope.getStorageData= function(){
		$http({
			url:"/getFileList/"+$scope.viewN,
			method:"POST"
		}).then(function(data){
			console.log("datas : ",data);
			$scope.storageFile=data.data;

		}).catch(function(err){
			console.log("ERR! : ",err);
		});
	}
	
	$scope.goToFile = function(idex){
		location.href="/download/"+idex;
	}
	
	$scope.goToUpdate=function(){
		console.log("클릭 된것 같은데");
		location.href="/update/"+$scope.viewN;
	}
	
	$scope.getUserInfo = function(){
		$http({
			url:"/getUserInfo",
			method:"POST"
		}).then(function(data){
			console.log("dataE : ",data);
			$scope.isYours = $scope.boardData.name==data.data.result ? true : false
		}).catch(function(err){
			console.log("ERR! : ",err);
		});
	}
	
	$scope.selectOnePost();
	$scope.getStorageData();
	
});