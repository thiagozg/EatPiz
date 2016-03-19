<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<table class="table table-hover table-condensed table-striped table-bordered">
	<c:if test="${page == 'listagem'}">
		<thead>
			<tr>
				<td style="width: 10%;">#</td>
				<td style="width: 30%;"><spring:message code="views.pizza.nome" /></td>
				<td style="width: 20%;"><spring:message code="views.pizza.preco" /></td>
				<td style="width: 10%;"><spring:message code="views.pizza.categoria" /></td>
				<td style="width: 10%;"><spring:message code="views.pizza.ingrediente" /></td>
				<td style="width: 10%;"><spring:message code="views.menu.editar.botao" /></td>
				<td style="width: 10%;"><spring:message code="views.pizza.deletar" /></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pizzas}" var="pizza">
				<tr data-id="${pizza.id}">
					<td>${pizza.id}</td>
					<td>${pizza.nome}</td>
					<td>
						<fmt:formatNumber value="${pizza.preco}" type="currency"/>
					</td>
					<td>${pizza.categoria}</td>
					<td>
						<c:forEach items="${pizza.ingredientes}" var="ingrediente">
							${ingrediente.nome} <br />
						</c:forEach>
					</td>
					<td><button type="button" class="btn btn-warning btn-editar"><spring:message code="views.menu.editar.botao" /></button></td>
					<td><button type="button" class="btn btn-danger btn-deletar"><spring:message code="views.pizza.deletar" /></button></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7"><spring:message code="views.pizza.total" /> 
					<span id="quantidade-pizzas">${pizzas.size()}</span>
				</td>
			</tr>
			<tr>
				<td colspan="7">
					<button type="button" class="btn btn-primary pull-right" data-toggle="modal"
						data-target="#modal-pizza"><spring:message code="views.pizza.cadastrar" /></button>
				</td>
			</tr>
		</tfoot>
	</c:if>
		<c:if test="${page == 'home'}">
		<thead>
			<tr>
				<td style="width: 10%;">#</td>
				<td style="width: 30%;"><spring:message code="views.pizza.nome" /></td>
				<td style="width: 20%;"><spring:message code="views.pizza.preco" /></td>
				<td style="width: 20%;"><spring:message code="views.pizza.categoria" /></td>
				<td style="width: 20%;"><spring:message code="views.pizza.ingrediente" /></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pizzas}" var="pizza">
				<tr data-id="${pizza.id}">
					<td>${pizza.id}</td>
					<td>${pizza.nome}</td>
					<td>
					
						<fmt:formatNumber value="${pizza.preco}" type="currency"/>
					</td>
					<td>${pizza.categoria}</td>
					<td>
						<c:forEach items="${pizza.ingredientes}" var="ingrediente">
							${ingrediente.nome} <br />
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<button type="button" class="btn btn-primary pull-right" onClick="location='pizzas'">
						<spring:message code="views.pizza.gerenciar" />
					</button>
				</td>
			</tr>
		</tfoot>
	</c:if>
</table>