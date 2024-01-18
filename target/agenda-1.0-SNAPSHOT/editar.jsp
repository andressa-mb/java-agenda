<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda de contatos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="imagens/favicon.png">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h1>Editar contato</h1>
        <form name="frmContato" action="update">
            <table>
                <tr>
                    <td><input type="text" name="idcon" id="caixaIdcon" class="CaixaIdcon" readonly
                               value="<%out.print(request.getAttribute("idcon"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="nome" class="CaixaNome"
                               value="<%out.print(request.getAttribute("nome"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="fone" class="CaixaFone"
                               value="<%out.print(request.getAttribute("fone"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="email" class="CaixaEmail"
                               value="<%out.print(request.getAttribute("email"));%>"></td>
                </tr>
            </table>
            <input type="button" value="Salvar" class="BotaoPadrao" onclick="validar()">
        </form>
        <script src="js/validador.js"></script>
    </body>
</html>
