<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<fmt:setLocale value="pt_BR" />
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>EatPiz - Login</title>
		<!-- irá trazer o contexto da aplicação, no caso = /pizzaria -->
		<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
		<style type="text/css">
			@IMPORT url("${path}/static/bootstrap/css/bootstrap.min.css");
			@IMPORT url("${path}/static/bootstrap/css/bootstrap-theme.min.css");
			@IMPORT url("${path}/static/css/style.css");
		</style>
	</head>
	<body>
		<section id="logar" class="panel panel-primary">
			<c:if test="${not empty mensagemInfo}">
				<div>
					<div class="alert alert-success">
						<spring:message code="views.mensagens.pizzaria.cadastro.sucesso" />
					</div>
				</div>
			</c:if>
			<c:if test="${not empty param['semacesso']}">
				<div class="alert alert-warning">
					<spring:message code="views.login.erro" />
				</div>
			</c:if>
			
			<c:if test="${not empty param['saiu']}">
				<div class="alert alert-info">
					<spring:message code="views.login.logout" />
				</div>
			</c:if>
		
			<form action="${path}/autenticar" method="post">
				<div class="panel-heading">
					Pizzaria - Login
				</div>
				
				<div class="panel-body">
					<label for="usuario"><spring:message code="views.login.usuario" /></label>
					<input id="usuario" name="usuario" class="form-control" required>
					
					<label for="senha"><spring:message code="views.login.senha" /></label>
					<input type="password" id="senha" name="senha" class="form-control" required>
				</div>
				
				<div id="btns-login" class="panel-footer">
					<button type="submit" class="btn btn-default" onClick="location='${path}/clientes'"><spring:message code="views.login.cancelar" /></button>
					<button id="btn-entrar" class="btn btn-primary"><spring:message code="views.login.entrar" /></button>
				</div>
				<!-- para evitar o erro de CSRF Token 'null' -->
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</section>
	
		<script type="text/javascript" src="${path}/static/js/jquery-2.2.0.min.js"></script>
		<script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap.js"></script>
	</body>
</html>