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
		<title><spring:message code="views.home.menu" /></title>
		<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
		<style type="text/css">
			@IMPORT url("${path}/static/bootstrap/css/bootstrap.min.css");
			@IMPORT url("${path}/static/bootstrap/css/bootstrap-theme.min.css")
			@IMPORT url("${path}/static/css/style.css");
		</style>
	</head>
	<body>
		<div class="container">
			<jsp:include page="menu-pizzaria.jsp"></jsp:include>
			
			<div id="div-home" class="row">
				<h1><span class="label label-default"><spring:message code="views.home.h1" /> ${pizzaria.nome}</span></h1>
				<div class="container-fluid">
					<h3><span class="label label-primary"><spring:message code="views.home.h3" /></span></h3>
					<ul class="nav nav-tabs">
						<li role="presentation" class="active"><a href="#tab-telefone"><spring:message code="views.home.telefone" /></a></li>
						<li role="presentation"><a href="#tab-email"><spring:message code="views.home.email" /></a></li>
					</ul>
					
					<div class="tab-content">
						<div id="tab-telefone" class="panel panel-default tab-pane fade in active">
							<table class="table table-hover table-condensed table-striped table-bordered">
								<c:forEach items="${pizzaria.telefones}" var="telefone" varStatus="i">
									<tr>
										<td><spring:message code="views.home.telefone" /> ${(i.index)+1}: ${telefone.numero}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div id="tab-email" class="panel panel-default tab-pane fade">
							<table class="table table-hover table-condensed table-striped table-bordered">
								<c:forEach items="${pizzaria.emails}" var="email" varStatus="i">
									<tr>
										<td><spring:message code="views.home.email" /> ${(i.index)+1}: ${email.endereco}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-7 panel-primary">
					<div class="panel-heading">
					    <h3 class="panel-title"><spring:message code="views.menu.pizzaria.pizzas" /></h3>
				    </div>
					<section id="secao-pizzas">
						<jsp:include page="pizza/tabela-pizzas.jsp" />
					</section>	
				</div>
				<div class="col-lg-5 panel-primary">
					<div class="panel-heading">
					    <h3 class="panel-title"><spring:message code="views.menu.pizzaria.ingredientes" /></h3>
				    </div>
					<section id="secao-pizzas">
						<jsp:include page="ingrediente/tabela-ingredientes.jsp" />
					</section>	
				</div>
			</div>
		</div>	

		<jsp:include page="../footer.jsp"></jsp:include>	
		
		<script type="text/javascript" src="${path}/static/js/jquery-2.2.0.min.js"></script>
		<script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap.js"></script>
		<script>
			$(document).ready(function(){
			    $(".nav-tabs a").click(function(){
			        $(this).tab('show');
			    });
			});
		</script>
		
	</body>
</html>