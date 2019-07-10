<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>NEWS HUNT</title>
  
  
  <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.16/angular.min.js"></script>

      <link rel="stylesheet" href="style.css">
      <script>
              var m = angular.module("newshunt",[]);
              m.controller("userInfo",function($scope , $http, $window)
            	{
            	  
            		$scope.saveInfo = function()
            		{
	            		console.log($scope.email);
	            		data = {name:$scope.name , email:$scope.email , password:$scope.password , confirmPassword:$scope.cpassword ,phone:$scope.phno};
	            	    $http.post("signup",data).then(function(response)
	            	    {
	            	    	
	            	    	  alert("record inserted");
	            	    		
	            	    	
	            	    });	
            		}
            	$scope.login=function()
            	{
            		rec={email:$scope.login_username,password : $scope.login_password}
            	
            		$http({
            			url:"login",
            			method : "post",
            			data : rec
            			
            		}).then(function(res)
            				{
            					//alert(res.data);
            					if(res.data == 0)
            						{
            							alert("invalid username or password");
            						}
            					else if(res.data==1)
            						{
            			
            							$window.location.href="home";
            						}
            					else if(res.data==2)
            						{
            							$window.location.href="adminHome";
            							
            						}
            				
            					
        
            			
            				});
            	}
              });
        </script>
        
        
        <style>
        	body{
    margin:0;
    color:#6a6f8c;
    background:#c8c8c8;
    font:600 16px/18px 'Open Sans',sans-serif;
  }
  *,:after,:before{box-sizing:border-box}
  .clearfix:after,.clearfix:before{content:'';display:table}
  .clearfix:after{clear:both;display:block}
  a{color:inherit;text-decoration:none}
  
  .login-wrap{
    width:100%;
    margin:auto;
    margin-top: 30px;
    max-width:525px;
    min-height:570px;
    position:relative;
    background:url(http://www.campdenfb.com/sites/campdendrupal.modezero.net/files/Newspapers%20iStock_000025924670_Full.jpg) no-repeat center;
    box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
  }
  .login-html{
    width:100%;
    height:100%;
    position:absolute;
    padding:90px 70px 50px 70px;
    background:rgba(40,57,101,.9);
  }
  .login-html .sign-in-htm,
  .login-html .sign-up-htm{
    top:0;
    left:0;
    right:0;
    bottom:0;
    position:absolute;
    -webkit-transform:rotateY(180deg);
            transform:rotateY(180deg);
    -webkit-backface-visibility:hidden;
            backface-visibility:hidden;
    transition:all .4s linear;
  }
  .login-html .sign-in,
  .login-html .sign-up,
  .login-form .group .check{
    display:none;
  }
  
  .login-html .tab,
  .login-form .group .label,
  .login-form .group .button{
    text-transform:uppercase;
  }
  .login-html .tab{
    font-size:22px;
    margin-right:15px;
    padding-bottom:5px;
    margin:0 15px 10px 0;
    display:inline-block;
    border-bottom:2px solid transparent;
  }
  .login-html .sign-in:checked + .tab,
  .login-html .sign-up:checked + .tab{
    color:#fff;
    border-color:#1161ee;
  }
  .login-form{
    min-height:345px;
    position:relative;
    -webkit-perspective:1000px;
            perspective:1000px;
    -webkit-transform-style:preserve-3d;
            transform-style:preserve-3d;
  }
  .login-form .group{
    margin-bottom:15px;
  }
  .login-form .group .label,
  .login-form .group .input,
  .login-form .group .button{
    width:100%;
    color:#fff;
    display:block;
  }
  .login-form .group .input,
  .login-form .group .button{
    border:none;
    padding:15px 20px;
    border-radius:25px;
    background:rgba(255,255,255,.1);
  }
  .login-form .group input[data-type="password"]{
    text-security:circle;
    -webkit-text-security:circle;
  }
  .login-form .group .label{
    color:#aaa;
    font-size:12px;
  }
  .login-form .group .button{
    background:#1161ee;
  }
  .login-form .group label .icon{
    width:15px;
    height:15px;
    border-radius:2px;
    position:relative;
    display:inline-block;
    background:rgba(255,255,255,.1);
  }
  .login-form .group label .icon:before,
  .login-form .group label .icon:after{
    content:'';
    width:10px;
    height:2px;
    background:#fff;
    position:absolute;
    transition:all .2s ease-in-out 0s;
  }
  .login-form .group label .icon:before{
    left:3px;
    width:5px;
    bottom:6px;
    -webkit-transform:scale(0) rotate(0);
            transform:scale(0) rotate(0);
  }
  .login-form .group label .icon:after{
    top:6px;
    right:0;
    -webkit-transform:scale(0) rotate(0);
            transform:scale(0) rotate(0);
  }
  .login-form .group .check:checked + label{
    color:#fff;
  }
  .login-form .group .check:checked + label .icon{
    background:#1161ee;
  }
  .login-form .group .check:checked + label .icon:before{
    -webkit-transform:scale(1) rotate(45deg);
            transform:scale(1) rotate(45deg);
  }
  .login-form .group .check:checked + label .icon:after{
    -webkit-transform:scale(1) rotate(-45deg);
            transform:scale(1) rotate(-45deg);
  }
  .login-html .sign-in:checked + .tab + .sign-up + .tab + .login-form .sign-in-htm{
    -webkit-transform:rotate(0);
            transform:rotate(0);
  }
  .login-html .sign-up:checked + .tab + .login-form .sign-up-htm{
    -webkit-transform:rotate(0);
            transform:rotate(0);
  }
  
  .hr{
    height:2px;
    margin:60px 0 50px 0;
    background:rgba(255,255,255,.2);
  }
  .foot-lnk{
    text-align:center;
  }
        </style>      
  
</head>

	<body ng-app="newshunt">
	  <div class="login-wrap" ng-controller="userInfo">
	  	<div class="login-html">
	    	<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
	    	<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
	    	<div class="login-form">
	      		<form class="sign-in-htm" >
	        		<div class="group">
	          			<label for="user" class="label">Username</label>
	          			<input id="username" ng-model="login_username" type="text" class="input">
	        		</div>
	        
	        		<div class="group">
	          			<label for="pass" class="label">Password</label>
	          			<input id="password" ng-model="login_password" type="password" class="input" data-type="password">
	        		</div>
	        
	        		<div class="group">
	          			<input id="check" type="checkbox" class="check" checked>
	          			<label for="check"><span class="icon"></span> Keep me Signed in</label>
	        		</div>
	        
	        		<div class="group">
	          			<input type="button" class="button" value="Sign In" ng-click="login()">
	        		</div>
	        
	        		<div class="hr"></div>
	        			
	        		<div class="foot-lnk">
	          			<a href="#forgot">Forgot Password?</a>
	        		</div>
	     		 </form>
	      
	      		 <form class="sign-up-htm">
	          			<div class="group">
	              			<label for="name" class="label">Name</label>
	              			<input id="user_name" ng-model="name" type="text" class="input" >
	            		</div>
	        
	        			<div class="group">
	          				<label for="user" class="label">Username</label>
	          				<input id="username" ng-model="email" type="email" class="input">
	        			</div>
	        
	        			<div class="group">
	          				<label for="pass" class="label">Password</label>
	          				<input id="password" ng-model="password" type="password" class="input" data-type="password">
	        			</div>
	        
	        			<div class="group">
	          				<label for="pass" class="label">Confirm Password</label>
	          				<input id="pass" ng-model="cpassword" type="password" class="input" data-type="password">
	        			</div>
	        
	        			<div class="group">
	            			<label for="phone" class="label">Phone Number</label>
	            			<input id="phoneno" ng-model="phno" type="number" class="input">
	          			</div>
	        			
	        			<div class="group">
	          				<input type="button" class="button" value="Sign Up" ng-click="saveInfo()">
	        			</div>
	        
	        			<div class="hr"></div>
	        			
	        			<div class="foot-lnk">
	          				<label for="tab-1">Already Member?</a>
	       	 			</div>
	      			</form>
	    		</div>
	  		</div>
		</div>
	</body>
</html>