<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<table class="table table-hover table-condensed table-striped table-bordered">
	<c:if test="${page == 'listagem'}">
		<thead>
			<tr>
				<td style="width: 10%;">#</td>
				<td style="width: 50%;"><spring:message code="views.pizza.nome" /></td>
				<td style="width: 20%;"><spring:message code="views.pizza.categoria" /></td>
				<td style="width: 10%;"><spring:message code="views.menu.editar.botao" /></td>
				<td style="width: 10%;"><spring:message code="views.pizza.deletar" /></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ingredientes}" var="ingrediente">
				<tr data-id="${ingrediente.id}">
					<td>${ingrediente.id}</td>
					<td>${ingrediente.nome}</td>
					<td>${ingrediente.categoria}</td>
					<td><button type="button" class="btn btn-warning btn-editar"><spring:message code="views.menu.editar.botao" /></button></td>
					<td><button type="button" class="btn btn-danger btn-deletar"><spring:message code="views.pizza.deletar" /></button></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5"><spring:message code="views.ingrediente.modal.total" />
					<span id="quantidade-ingredientes">${ingredientes.size()}</span>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<button type="button" class="btn btn-primary pull-right" data-toggle="modal"
						data-target="#modal-ingrediente"><spring:message code="views.ingrediente.botao" /></button>
				</td>
			</tr>
		</tfoot>	
	</c:if> 
	<c:if test="${page == 'home'}">
		<thead>
			<tr>
				<td style="width: 15%;">#</td>
				<td style="width: 55%;"><spring:message code="views.pizza.nome" /></td>
				<td style="width: 30%;"><spring:message code="views.pizza.categoria" /></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ingredientes}" var="ingrediente">
				<tr data-id="${ingrediente.id}">
					<td>${ingrediente.id}</td>
					<td>${ingrediente.nome}</td>
					<td>${ingrediente.categoria}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3">
					<button type="button" class="btn btn-primary pull-right" onClick="location='ingredientes'">
						<spring:message code="views.pizza.gerenciar" />
					</button>
				</td>
			</tr>
		</tfoot>	
	</c:if> 	
</table>