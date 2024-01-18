package com.estudomvc.app.controlador.agenda;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.estudomvc.app.modelo.agenda.JavaBeans;
import com.estudomvc.app.dao.AgendaDAO;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

public class ControladorAgenda extends HttpServlet {
    
    AgendaDAO dao = new AgendaDAO();
    JavaBeans contato = new JavaBeans();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if (action.equals("/acessar-agenda")) {
            contatos(request, response);
        } else if (action.equals("/insert")) {
            novoContato(request, response);
        } else if (action.equals("/select")) {
            listarContato(request, response);
        } else if (action.equals("/update")) {
            editarContato(request, response);
        } else if (action.equals("/delete")) {
            removerContato(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }

    //inicia agenda
    protected void contatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.sendRedirect("agenda.jsp");
        ArrayList<JavaBeans> lista = dao.listarContatos();
        request.setAttribute("contatos", lista);
        RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
        rd.forward(request, response);
    }

    //adiciona novo contato
    protected void novoContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //seta variaveis JavaBeans
        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));
        dao.salvarContato(contato);
        response.sendRedirect("acessar-agenda");
    }
    
    private void listarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recebimento do id do contato que será editado
        String idcon = request.getParameter("idcon");
        contato.setIdcon(idcon);
        JavaBeans contatoSelecionado = dao.selecionarContato(contato);
        contato.setIdcon(contatoSelecionado.getIdcon());
        contato.setNome(contatoSelecionado.getNome());
        contato.setFone(contatoSelecionado.getFone());
        contato.setEmail(contatoSelecionado.getEmail());
        request.setAttribute("idcon", contato.getIdcon());
        request.setAttribute("nome", contato.getNome());
        request.setAttribute("fone", contato.getFone());
        request.setAttribute("email", contato.getEmail());
        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
        rd.forward(request, response);
    }
    
    private void editarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        contato.setIdcon(request.getParameter("idcon"));
        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));
        dao.alterarContato(contato);
        response.sendRedirect("acessar-agenda");
    }
    
    private void removerContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        contato.setIdcon(request.getParameter("idcon"));
        dao.deletarContato(contato);
        response.sendRedirect("acessar-agenda");
    }
}
