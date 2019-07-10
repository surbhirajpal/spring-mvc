<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>NEWS HUNT</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--link rel="stylesheet" type="text/css" media="screen" href="front_page.css" /-->
     <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.16/angular.min.js"></script>
    <script>
              var m = angular.module("newshunt",[]);
              m.controller("MenuController",function($scope , $http, $window){
              	$scope.menuData=function()
              	{
					$http.post("AdminMenuList").then(function(resp)
					{	
        				$scope.menuData = resp.data;
        				
        			});
					
					$http.post("fetchMyInfo").then(function(resp)
					{
						$scope.userData=resp.data;						
						
					});
					$http.post("FetchUserList").then(function(resp)
					{
						$scope.userList=resp.data;
					});
					
              	 }
              	
              	$scope.del=function(x)
              	{
              		$http.post("deleteUser",x).then(function(resp)
              				{
              					alert(" User Record Deleted!!")
              				});
              		
              	}
              	$scope.update=function(selectRecord)
              	{
              		$scope.rec=selectRecord;
              		$('#myModal').modal('show');
              	}
              	
              	
              	$scope.saveUpdateInfo=function(rec)
              	{
              		$http.post("updateUserInfo",rec).then(function(resp)
       				{
       							alert("User Record Changed!!!")
       						
       				});
              	}
              	
              	
              });
              
     </script> 	
    <style>
        .navbar-expand-sm
		{
		  background-color:rgb(128,0,0);
		  position: fixed;
		}
		.nav.navbar-nav.navbar-right li a,
		.navbar-nav.mr-auto li a
		{
		  color: aliceblue;
		  font-size: large
		}
		.nav-link
		{
			text-transform : uppercase;
		}
		
		body
		{
		  font: 'Open Sans',sans-serif;
		 font-size: medium;
		
		
		
		}
		
		
		
		.split {
		
		
		    height: 100%;
		    width: 20%;
		    position: fixed;
		    z-index: 1;
		    top: 0;
		    overflow-x: hidden;
		    padding-top: 20px;
		    background:url(http://www.campdenfb.com/sites/campdendrupal.modezero.net/files/Newspapers%20iStock_000025924670_Full.jpg) no-repeat center;
		    left:0;
		
		  }
		
		.left
		{
		  
		  height : 100%;
		  width :20%;
		  position: fixed;
		    z-index: 1;
		    top: 0;
		    overflow-x: hidden;
		    padding-top: 20px;
		  background:rgb(128,0,0,.8);
		
		
		}
		
		.right
		{
		  
		  height : 100%;
		  width :90%;
		  position: fixed;
		    z-index: 1;
		    top: 0;
		    overflow-x: hidden;
		    padding-top: 20px;
		  
		  right:0;
		  color : black;
		
		}
		
		
		.module-name
		{
		  color: aliceblue;
		  padding-bottom: 10px
		
		
		}
		
		.centered {
		    position: absolute;
		    top: 50%;
		    left: 50%;
		    transform: translate(-50%, -50%);
		    text-align: center;
		    
		    
		
		  }
		  
		  
.centered2 
{
    position: absolute;
    left: 20%;
    text-align: center;
}		  
		  
.add_channel_descripton
{
    font: 'Open Sans',sans-serif;
    font-size: 20px;
    text-align: center;
    color: rgb(128,0,0);
    padding-top: 5px;
    padding-top: 5px;
    padding-left : 50px
}
.border_to_div
{
    border: 1px solid black;
    width: 600px;
    padding: 40px;
}
.add_channel_heading
{
    font: 'Open Sans',sans-serif;
    font-size: 40px;
    text-align: center;
    color: rgb(128,0,0);
    padding-top: 5px;
    padding-top: 5px;
    padding-left : 50px

}
.add_channel_click
{
    position:sticky;
    top : 0;
    background-color: white; 
    color:rgb(128,0,0); 
    border: 2px solid rgb(128,0,0);
    font: 'Open Sans',sans-serif;
    font-size: 10px;
    padding:5px;
    
}
.add_channel_click:hover
{
    background-color:rgb(128,0,0);
    color: white;
}
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}


		  
		  
		  
		</style>
	</head>
	<body ng-app="newshunt">
	<div ng-controller="MenuController" ng-init="menuData()">
    	<div class="nav" >
    		<nav class="navbar navbar-expand-sm fixed-top">
        		<ul class="navbar-nav mr-auto">
            		<li class="nav-item">
                		<a class="nav-link" href="#">NEWSHUNT</a>
            		</li>
        		</ul>
        
        		<ul class="nav navbar-nav navbar-right ">
            		<li class="nav-item">
               				<a class="nav-link" href="#">{{userData.name}}</a>
            		</li>
        		</ul>
    		</nav>
    	</div>
    
    	<div class="split ">
        	<div class="left">
        		<div class ="centered">
            		<div class =" modules" ng-repeat = "x in menuData">
                     	<a class = "module-name" href="http://localhost:8080/newshunt{{x.url}}">{{x.name}}</a><br><br>
                     
                     	
                	</div>
        		</div>
        	</div>
        <div class="right">
            <div class="centered2">
            <br><br><br>
                <div class ="module" style="overflow-x:auto;">
                  	<table class="table table-hover">
					    <thead>
					      <tr>
					        <th>EMAIL</th>
					        <th>NAME</th>
					        <th>PASSWORD</th>
					        <!--  
					        <th>PHONE NUMBER</th>
					        -->
					        <th>SUBSCRIBED CHANNEL</th>
					        <th>FAVOURITIES</th>
					        <th>UPDATE</th>
					        <th>DELETE</th>
					      </tr>
					    </thead>
					    <tbody ng-repeat="x in userList">
					      <tr>
					        <td>{{x.email}}</td>
					        <td>{{x.name}}</td>
					        <td>{{x.password}}</td>
					        <!--  
					        <td>{{x.phone}}</td>
					        -->
					        <td>{{x.mychannel}}</td>
					        <td>{{x.favourities}}</td>
					        
					        <td><button align="right" class="btn btn-default btn-info btn-lg add_channel_click" 
					         data-toggle="modal" data-target="#myModal" ng-click="update(x)">UPDATE
					        				        </button></td>
					        
					        <td><button align="right" class="btn btn-default add_channel_click" ng-click="del(x)">DELETE</button></td>
					      </tr>
					      
					    </tbody>
					  </table>     
                    
                </div>
            </div>
        </div>
    </div>
    
    
        <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">UPDATE USER RECORD</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          EMAIL<input type="text" ng-model="rec.email"/><br><br>
          NAME<input type="text" ng-model="rec.name"/><br><br>
          PASSWORD<input type="text" ng-model="rec.password"/><br><br>
          SUBSCRIBED CHANNEL<input type="text" ng-model="rec.mychannel"/><br><br>
          FAVOURITIES<input type="text" ng-model="rec.favourities"/><br><br>
          
        </div>
        <div class="modal-footer">
        	<button type="button" class="btn btn-default add_channel_click" ng-click="saveUpdateInfo(rec)">UPDATE</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
    
    
    
    
    </div>
</body>
</html>