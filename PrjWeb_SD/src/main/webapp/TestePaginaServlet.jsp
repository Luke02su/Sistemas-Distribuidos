<%-- 
    Document   : TestePaginaServlet
    Created on : 3 de abr. de 2025, 20:20:31
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap JS (opcional, para componentes interativos) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Pets</title>
    </head>
    <body>
        <div class="container mt-3">
            <h2>CADASTRO DE PETS</h2>
            <form action="${pageContext.request.contextPath}/GerenciarDados">
                <div class="mb-3 mt-3">
                    <label for="nome">Nome:</label>
                    <input type="text" class="form-control" id="nome" name="nome" value="${pet.nome}">
                </div>
                <div class="mb-3">
                    <label for="raca">Raça:</label>
                    <input type="text" class="form-control" id="raca" name="raca" value="${pet.raca}">
                </div>
                <div class="mb-3">
                    <label for="porte">Porte:</label>
                    <input type="text" class="form-control" id="porte" name="porte" value="${pet.porte}">
                </div>
                <div class="mb-3>
                    <label for="data_nasc">Data Nasc.</label>
                    <input type="text" class="form-control" id="data_nasc" name="data_nasc" value="${pet.data_nasc}">
                </div>
                <div class="mb-3">
                    <label for="cor">Cor:</label>
                    <input type="text" class="form-control" id="cor" name="cor" value="${pet.cor}">
                </div>
                <button type="submit" class="btn btn-primary" name="acao" value="Salvar">Salvar</button>
                <button type="submit" class="btn btn-primary" name="acao" value="Listar">Listar</button>
                
                <h2>Lista de Pets</h2>
                <table class="table table-dark table-striped">
                    <thread>
                        <tr>
                            <th>Nome</th>
                            <th>Porte</th>
                            <th>Cor</th>
                            <th>Data Nascimento</th>
                            <th>Raça</th>
                            <th>Opção</th>
                        </tr>
                    </thread>
                <tbody> <!-- corpo da tabela -->
                    <c:forEach var="pet" items="${lstPets}" varStatus="contador"><!<!-- forEach importada da tag lá em cima -->
                        <tr>
                            <td><c:out value="${pet.nome}"/></td>
                            <td><c:out value="${pet.porte}"/></td>
                            <td><c:out value="${pet.cor}"/></td>
                            <td><c:out value="${pet.dataNasc}"/></td>
                            <td><c:out value="${pet.raca}"/></td>
                            <td><button type="submit" class="btn btn-primary" name="acao" value="Listar - ${contador.count}">Editar</button></td>
                        </tr>
                    </c:forEach>
                   </tbody>
                </table>
            </div>
        </form>
    </body>
</html>
