<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<fmt:setLocale value="pt_BR" />
<html>
	<head>
		<title><spring:message code="views.cliente.titulo" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
		<style type="text/css">
			@IMPORT url("${path}/static/bootstrap/css/bootstrap.min.css");
			@IMPORT url("${path}/static/bootstrap/css/bootstrap-theme.min.css");
			@IMPORT url("${path}/static/css/style.css");
		</style>
	</head>
	<body>
		<div class="panel panel-default">
			<div class="panel-body">
			   <div class="container">
					<jsp:include page="menu-cliente.jsp" />
					<section>
						<div id="consulta-pizzarias" class="well">
							<div class="row">
								<div class="col-sm-4">
									<label for="pizza-pesquisa"><spring:message code="views.cliente.deseja" /></label>
									<select id="pizza-pesquisa" class="form-control">
										<c:forEach items="${nomesPizzas}" var="nomePizza">
											<option value="${nomePizza}">${nomePizza}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-sm-4">
									<button id="btn-buscar" class="btn btn-primary"><spring:message code="views.cliente.buscar" /></button>
								</div>
							</div>
						</div>
						
						<div id="secao-pizzarias"></div>
					</section>
				</div>
			</div>
			<jsp:include page="../footer.jsp"></jsp:include>
		</div>
		
		<script type="text/javascript" src="${path}/static/js/jquery-2.2.0.min.js"></script>
		<script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${path}/static/js/clientes.js"></script>
	</body>
</html>