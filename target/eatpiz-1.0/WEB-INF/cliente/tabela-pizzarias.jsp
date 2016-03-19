<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<table class="table table-hover table-condensed table-striped table-bordered">
	<thead>
		<tr>
			<td style="width: 20%;"><spring:message code="views.menu.editar.nome" /></td>
			<td style="width: 26%;"><spring:message code="views.menu.editar.endereco" /></td>
			<td style="width: 18%;"><spring:message code="views.menu.editar.data" /></td>
			<td style="width: 18%;"><spring:message code="views.telefone.telefone" /> (s):</td>
			<td style="width: 18%;"><spring:message code="views.email.email" /> (s):</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pizzarias}" var="pizzaria">
			<tr data-id="${pizzaria.id}">
				<td>${pizzaria.nome}</td>
				<td>${pizzaria.endereco}</td>
				<%-- <td><span data-format="dd/MM/yyyy hh:mm:ss">${pizzaria.dataCadastro}</span></td> --%>
				<td><fmt:formatDate value="${pizzaria.getDataCadastroCalendario().time}" pattern="dd/MM/yyyy" /></td>
				<td>
					<c:forEach items="${pizzaria.telefones}" var="telefone">
						${telefone.numero} <br />
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${pizzaria.emails}" var="email">
						${email.endereco} <br />
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
