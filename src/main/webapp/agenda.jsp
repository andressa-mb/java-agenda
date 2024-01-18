<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.estudomvc.app.modelo.agenda.JavaBeans"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda de contatos</title>
        <link rel="icon" href="imagens/favicon.png">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h1>Agenda de contatos</h1>
        <a href="index.html" class="BotaoPadrao">Voltar</a>
        <a href="novo.html" class="BotaoPadrao">Novo</a>
        <table id="tabela">
            <thead>
                <tr>
                    <th></th>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Fone</th>
                    <th>E-mail</th>
                    <th style="text-align: center">Opções</th>
                </tr>
            </thead>
            <tbody>
                <%for (int i = 0; i < lista.size(); i++) {%>
                <tr>
                    <td><%=i+1%></td>
                    <td><%=lista.get(i).getIdcon()%></td>
                    <td><%=lista.get(i).getNome()%></td>
                    <td><%=lista.get(i).getFone()%></td>
                    <td><%=lista.get(i).getEmail()%></td>
                    <td>
                        <a href="select?idcon=<%=lista.get(i).getIdcon()%>" class="BotaoEditar">Editar</a>
                        <a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)" class="BotaoExcluir">Excluir</a>
                    </td>
                </tr>
                <% }%>
            </tbody>
        </table>
        <script src="js/confirmador.js"></script>
    </body>
</html>
