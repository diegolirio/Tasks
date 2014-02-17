<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login - Tasks</title>
	 <link href="/tasks/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	 <link href="/tasks/static/bootstrap/css/bootstrap.css" rel="stylesheet">	
</head>
<body>

<div class="container">
	
	<h1 class="text-primary">Login</h1>
	
	<div class="pull-right"><a href="#">Criar Nova Conta</a></div>
	
	<br/>
	
	<c:if test="${message != null}">
		<p class="alert alert-info">${message}</p>
	</c:if>
	
	<br/>
	
	<form:errors path="*" cssStyle="color:red"/>	

<form action="" class="well form-horizontal" id="id_form_login" method="post" accept-charset="utf-8">

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-5">
		<div class="input-group">
		  <span class="input-group-addon">@</span>
		  <input type="email" class="form-control" placeholder="Username" name="email">
		</div>
		<form:errors path="user.email" cssClass="text-danger"/>
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-5">
		<div class="input-group">
		  <span class="input-group-addon">#</span>
		  <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="password">
		</div>      
		<form:errors path="user.password" cssClass="text-danger"/>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-2">
      <div class="checkbox">
        <label>
          <input type="checkbox"> Remember me
        </label>
      </div>
    </div>
    <div class="col-sm-2">
    	<a href="#"><span class="text-center">Esqueci a senha</span></a>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-primary">Sign in</button> 
    </div>
  </div>
</form>

</div>

	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="static/bootstrap/js/bootstrap.min.js"></script>
	
	
</body>
</html>