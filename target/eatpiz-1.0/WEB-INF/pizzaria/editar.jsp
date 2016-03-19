<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
	<fmt:setLocale value="pt_BR" />
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="views.menu.pizzaria.editar" /></title>
		<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
		<style type="text/css">
			@IMPORT url("${path}/static/bootstrap/css/bootstrap.min.css");
			@IMPORT url("${path}/static/bootstrap/css/bootstrap-theme.min.css");
			@IMPORT url("${path}/static/css/style.css");
			@IMPORT url("${path}/static/bootstrap/css/bootstrap-combined.min.css");
			@IMPORT url("${path}/static/bootstrap/css/bootstrap-datetimepicker.min.css");
		</style>
	</head>
	<body>
		<div class="panel panel-default">
			<div class="panel-body">
			   <div class="container">
					<c:if test="${not empty mensagemErro}">
						<div>
							<div class="alert alert-success">
								<spring:message code="views.mensagens.pizzaria.editar.erro" />
							</div>
						</div>
					</c:if>
					<c:if test="${not empty mensagemSenha}">
						<div>
							<div class="alert alert-danger">
								<spring:message code="views.mensagens.pizzaria.cadastro.senha" />
							</div>
						</div>
					</c:if>
					<c:if test="${not empty mensagemInfo}">
						<div>
							<div class="alert alert-info">
								<spring:message code="views.mensagens.pizzaria.editar.info" />
							</div>
						</div>
					</c:if>
					<section id="editar-cadastro-pizzarias">
						<div class="well">
							<form id="form-pizzaria" method="post" th:action="@{/pizzarias}" th:object="${pizzaria}" action="">
								<div class="modal-header">
									<h4 class="modal-title"><spring:message code="views.menu.pizzaria.editar" /></h4>
								</div>
								<div class="modal-body">
							  		<label for="nome"><spring:message code="views.menu.editar.nome" /></label>
									<input id="nome" name="nome" class="form-control" value="${pizzaria.nome}" required />
									
									<label for="usuario.login">Login: </label>
									<input id="usuario.login" name="usuario.login" class="form-control" value="${pizzaria.usuario.login}" required />
									
									<label for="usuario.senha"><spring:message code="views.menu.editar.senha" /></label>
									<input id="usuario.senha" name="usuario.senha" class="form-control" type="password" required />
									
									<label for="usuario.confirmacaoSenha"><spring:message code="views.menu.editar.csenha" /></label>
									<input id="usuario.confirmacaoSenha" name="usuario.confirmacaoSenha" class="form-control" type="password" required />
									
									<label for="endereco"><spring:message code="views.menu.editar.endereco" /></label>
									<input id="endereco" name="endereco" class="form-control" value="${pizzaria.endereco}" required />
									
									<label for="dataCadastro" class="inp-data"><spring:message code="views.menu.editar.data" /></label>
							  		<div id="datetimepicker" class="input-append date">
								    	<input data-format="dd/MM/yyyy hh:mm:ss" type="text" id="dataCadastro" name="dataCadastro" style="height: auto;" value="${pizzaria.dataCadastro}" required></input>
									    <span class="add-on" style="height: 30px;">
									    	<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
									    </span>
								  	</div>
								  	
								  	<input id="id" name="id" value="${pizzaria.id}" type="hidden">
									<input id="csrf" name="_csrf" type="hidden" value="${_csrf.token}" />
								</div>
								
								<div class="modal-footer">
									<button type="button" class="btn btn-default" onClick="location='home'"><spring:message code="views.login.cancelar" /></button>
									<button id="btn-editar" type="submit" class="btn btn-primary"><spring:message code="views.menu.editar.botao2" /></button>
								</div>
							</form>
						</div>
					</section>
				</div>
			</div>
		 	<jsp:include page="../footer.jsp"></jsp:include>
		</div>
		
		<script type="text/javascript" src="${path}/static/js/jquery-2.2.0.min.js"></script>
		<script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap-datetimepicker.pt"></script>
		<script type="text/javascript">
			$('#datetimepicker').datetimepicker({
			  format: 'dd/MM/yyyy hh:mm:ss',
			  language: 'pt-BR'
			});
	    </script>
	</body>
</html>