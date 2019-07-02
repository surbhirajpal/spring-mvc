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
              m.controller("MenuController",function($scope , $http)
            {
              	$scope.menuData=function()
              	{
					$http.post("menuList").then(function(resp)
					{	
        				$scope.menuData = resp.data;
        				
        			});
					
					$http.post("displayFavourities").then(function(req)
					{
							$scope.myFavourities = req.data;
							console.log($scope.Favoutities);
					
					});


              	 }
              	
              	
                            	
             });
              
     </script> 	
    <style>
        .navbar-expand-sm
		{
		  background-color:rgba(40,57,101);
		  position: fixed;
		}
		.nav.navbar-nav.navbar-right li a,
		.navbar-nav.mr-auto li a
		{
		  color: aliceblue;
		  font-size: large
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
		  background:rgba(40,57,101,.8);
		
		
		}
		
		.right
		{
		  
		  height : 100%;
		  width :80%;
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
    color: rgba(40,57,101);
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
    color: rgba(40,57,101);
    padding-top: 5px;
    padding-top: 5px;
    padding-left : 50px

}
.add_channel_click
{
    position:sticky;
    top : 0;
    background-color: white; 
    color:rgba(40,57,101); 
    border: 2px solid rgba(40,57,101);
    font: 'Open Sans',sans-serif;
    font-size: 15px;
    padding:10px;
    
}
.add_channel_click:hover
{
    background-color:rgba(40,57,101);
    color: white;
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
               				<a class="nav-link" href="#">SURBHI</a>
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
                <div class ="module" ng-repeat="x in myFavourities">
                  	<div class="border_to_div">
                        <label class="add_channel_heading">{{x.channelName}}</label><br>
                        <br>
                        <label class="add_channel_descripton">{{x.title}}</label><br><br>
                        {{x.link}}<br>
                        {{x.date}}
                    </div>
                    <br><br><br>
                </div>
             </div>
        </div>
    </div>
    </div>
</body>
</html>