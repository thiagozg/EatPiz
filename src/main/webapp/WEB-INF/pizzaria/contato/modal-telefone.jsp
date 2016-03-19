<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal fade" id="modal-telefone" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<form id="form-telefone" class="desativarSubmitForm" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"><spring:message code="views.telefone.telefone" /></h4>
				</div>
				<div class="modal-body">
					<label for="numero"><spring:message code="views.telefone.numero" /></label>
					<input id="numero" name="numero" class="form-control valor">
					
					<input id="telefone-id" name="id" type="hidden">
					<input id="csrf" name="_csrf" type="hidden" value="${_csrf.token}">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="views.login.cancelar" /></button>
					<button id="btn-salvar-telefone" type="button" class="btn btn-primary"><spring:message code="views.pizza.modal.botao" /></button>
				</div>
			</form>
		</div>
	</div>
</div>