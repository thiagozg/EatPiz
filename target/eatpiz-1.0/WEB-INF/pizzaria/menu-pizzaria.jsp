<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
	@IMPORT url("${path}/static/css/style.css");
</style>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${path}/pizzarias/home"><spring:message code="views.menu.pizzaria.empresa" /></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="pizzas"><spring:message code="views.menu.pizzaria.pizzas" /></a></li>
				<li><a href="ingredientes"><spring:message code="views.menu.pizzaria.ingredientes" /></a></li>
				<li><a href="contatos"><spring:message code="views.menu.pizzaria.contatos" /></a></li>
				<li><a href="editar"><spring:message code="views.menu.pizzaria.editar" /></a></li>
				<li>
					<a href="?lang=pt_BR">
						<img alt="Português" src="${path}/static/img/br.png" class="bandeira">					
					</a>
				</li>
				<li>
					<a href="?lang=en_US">
						<img alt="English" src="${path}/static/img/us.png" class="bandeira">					
					</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<form action="${path}/sair" method="post">
						<input type="hidden" name="_csrf" value="${_csrf.token}">
						<button id="btn-sair" type="submit" class="btn btn_default"><spring:message code="views.menu.pizzaria.sair" /></button>
					</form>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>