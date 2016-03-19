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
			<td style="width: 50%;"><spring:message code="views.email.endereco" /></td>
			<td colspan="3" style="width: 30%;"><spring:message code="views.email.opcoes" /></td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${emails}" var="email" varStatus="i">
			<tr data-id="${email.id}">
				<%-- <td>${(i.index)+1}</td> --%>
				<td>${email.id}</td>
				<td>${email.endereco}</td>
				<td><button type="button" class="btn btn-warning btn-editar-email"><spring:message code="views.menu.editar.botao" /></button></td>
				<td><button type="button" class="btn btn-danger btn-deletar-email"><spring:message code="views.pizza.deletar" /></button></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="4">
				<button type="button" class="btn btn-primary pull-right" data-toggle="modal"
					data-target="#modal-email"><spring:message code="views.email.botao" /></button>
			</td>
		</tr>
	</tfoot>
</table>