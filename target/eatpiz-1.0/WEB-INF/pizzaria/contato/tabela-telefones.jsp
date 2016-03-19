<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<fmt:setLocale value="pt_BR" />
<table class="table table-hover table-condensed table-striped table-bordered">
	<thead>
		<tr>
			<td style="width: 20%;">#</td>
			<td style="width: 50%;"><spring:message code="views.telefone.numero" /></td>
			<td colspan="3" style="width: 30%;"><spring:message code="views.telefone.opcoes" /></td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${telefones}" var="telefone" varStatus="i">
			<tr data-id="${telefone.id}">
				<%-- <td>${(i.index)+1}</td> --%>
				<td>${telefone.id}</td>
				<td>${telefone.numero}</td>
				<td><button type="button" class="btn btn-warning btn-editar-telefone"><spring:message code="views.menu.editar.botao" /></button></td>
				<td><button type="button" class="btn btn-danger btn-deletar-telefone"><spring:message code="views.pizza.deletar" /></button></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="4">
				<button type="button" class="btn btn-primary pull-right" data-toggle="modal"
					data-target="#modal-telefone"><spring:message code="views.telefone.botao" /></button>
			</td>
		</tr>
	</tfoot>
</table>