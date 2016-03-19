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
		<title><spring:message code="views.contato.titulo" /></title>
		<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
		<style type="text/css">
			@IMPORT url("${path}/static/bootstrap/css/bootstrap.min.css");
			@IMPORT url("${path}/static/bootstrap/css/bootstrap-theme.min.css")
			@IMPORT url("${path}/static/css/style.css");
		</style>
	</head>
	<body>
		<div class="container">
			<jsp:include page="../menu-pizzaria.jsp" />
			
			<div class="row">
				<div class="col-lg-5 panel-info">
					<div class="panel-heading">
					    <h3 class="panel-title"><spring:message code="views.telefone.telefones" /></h3>
				    </div>
					<section id="secao-telefones">
						<jsp:include page="tabela-telefones.jsp" />
					</section>	
				</div>
				<div class="col-lg-7 panel-info">
					<div class="panel-heading">
					    <h3 class="panel-title"><spring:message code="views.email.emails" /></h3>
				    </div>
					<section id="secao-emails">
						<jsp:include page="tabela-emails.jsp" />
					</section>	
				</div>
			</div>
		</div>	
		
		<jsp:include page="modal-email.jsp" />
		<jsp:include page="modal-telefone.jsp" />
		
		<jsp:include page="../../footer.jsp" />	
		
		<script type="text/javascript" src="${path}/static/js/jquery-2.2.0.min.js"></script>
		<script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="${path}/static/js/contatos.js"></script>
	</body>
</html>