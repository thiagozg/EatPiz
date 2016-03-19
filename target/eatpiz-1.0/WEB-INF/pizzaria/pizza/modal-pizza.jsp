<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal fade" id="modal-pizza" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<form id="form-pizza" class="desativarSubmitForm" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"><spring:message code="views.pizza.modal.titulo" /></h4>
				</div>
				<div class="modal-body">
					<label for="nome"><spring:message code="views.pizza.nome" /></label>
					<input id="nome" name="nome" class="form-control">
					
					<label for="preco"><spring:message code="views.pizza.preco" /></label>
					<input id="preco" name="preco" class="form-control">
					
					<label for="categoria"><spring:message code="views.pizza.categoria" /></label>
					<select id="categoria" name="categoria" class="form-control">
						<c:forEach items="${categorias}" var="categoria">
						 <option value="${categoria}">${categoria}</option>
						</c:forEach>
					</select> 
					
					<label for="ingredientes"><spring:message code="views.pizza.ingrediente" /></label>
					<select id="ingredientes" name="ingredientes" class="form-control" multiple="multiple">
						<c:forEach items="${ingredientes}" var="ingrediente">
						 <option value="${ingrediente.id}">${ingrediente.nome}</option>
						</c:forEach>
					</select> 
					
					<input id="id" name="id" type="hidden">
					<input id="csrf" name="_csrf" type="hidden" value="${_csrf.token}">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="views.login.cancelar" /></button>
					<button id="btn-salvar" type="button" class="btn btn-primary"><spring:message code="views.pizza.modal.botao" /></button>
				</div>
			</form>
		</div>
	</div>
</div>