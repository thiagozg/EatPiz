<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- as bibliotecas utilizadas acim ja foram baixadas pelo Maven (verificar pom) -->
<!-- 
	c = jstl comum
	fmt = jstl de formatação (dinheiro, data...)
	spring = spring
	form = tag de formulario para spring
 -->
<!DOCTYPE html>
<fmt:setLocale value="pt_BR" />
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="views.ingrediente.titulo" /></title>
		<!-- irá trazer o contexto da aplicação, no caso = /pizzaria -->
		<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
		<!-- 
		ANTES DO PATH:
		@IMPORT url("/pizzaria/static/bootstrap/css/bootstrap.min.css");
		@IMPORT url("/pizzaria/static/bootstrap/css/bootstrap-theme.min.css")
		-->
		<style type="text/css">
			@IMPORT url("${path}/static/bootstrap/css/bootstrap.min.css");
			@IMPORT url("${path}/static/bootstrap/css/bootstrap-theme.min.css")
			@IMPORT url("${path}/static/css/style.css");
		</style>
	</head>
	<body>
		<div class="container">
			<jsp:include page="../menu-pizzaria.jsp"></jsp:include>
			<c:if test="${not empty mensagemErro}">
				<div>
					<div class="alert alert-danger">${mensagemErro}</div>
				</div>
			</c:if>
			
			<c:if test="${not empty mensagemInfo}">
				<div>
					<div class="alert alert-info">${mensagemInfo}</div>
				</div>
			</c:if>
			<section id="secao-ingredientes">
					<jsp:include page="tabela-ingredientes.jsp" />		
			</section>	
			
			<jsp:include page="modal-ingrediente.jsp" />
		</div>
		
		<jsp:include page="../../footer.jsp" />
		
		<script type="text/javascript" src="${path}/static/js/jquery-2.2.0.min.js"></script>
		<script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${path}/static/js/ingredientes.js"></script>
	</body>
</html>