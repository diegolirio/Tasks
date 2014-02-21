<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Tasks - Criar Nova Conta</title>
	<link href="/tasks/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="/tasks/static/bootstrap/css/bootstrap.css" rel="stylesheet">	
</head>
<body>
	<div class="container">
		<h1 class="text-info">Nova Conta</h1>
		
		<form action="" class="well form-horizontal" id="id_form_login" method="post" accept-charset="utf-8">
		
			<c:if test="${message != null}">
				<div class="alert alert-danger">${message}</div>
			</c:if>

		  <div class="form-group">
		    <label for="inputName" class="col-sm-2 control-label">Name</label>
		    <div class="col-sm-5">
				<div class="input-group">
				  <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
				  <input type="text" class="form-control" placeholder="Username" name="name" id="inputName" value="${user.name}">
				</div>
				<form:errors path="user.name" cssClass="text-danger"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputEmail" class="col-sm-2 control-label">Email</label>
		    <div class="col-sm-5">
				<div class="input-group">
				  <span class="input-group-addon">@</span>
				  <input type="email" class="form-control" placeholder="Email" name="email" id="inputEmail" value="${user.email}">
				</div>
				<form:errors path="user.email" cssClass="text-danger"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="inputPassword" class="col-sm-2 control-label">Password</label>
		    <div class="col-sm-5">
				<div class="input-group">
				  <span class="input-group-addon">#</span>
				  <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password">
				</div>      
				<form:errors path="user.password" cssClass="text-danger"/>
		    </div>
		  </div>		 		    
		  <div class="form-group">
		    <label for="inputPasswordConfirm" class="col-sm-2 control-label">Confirm Password</label>
		    <div class="col-sm-5">
				<div class="input-group">
				  <span class="input-group-addon">#</span>
				  <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password" name="confirmPassword">
				</div>      
				<!-- <form:errors path="user.password" cssClass="text-danger"/> -->
				<span class="text-danger">${confirmPasswordMessage}</span>
		    </div>		    
		  </div>	  
		  
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-primary">Save</button> 
		      <a href="/tasks/" class="btn btn-danger">Cancelar</a> 
		    </div>
  		  </div>
</form>		
		
	</div>
</body>
</html>