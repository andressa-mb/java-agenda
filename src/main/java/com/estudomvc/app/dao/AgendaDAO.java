package com.estudomvc.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.estudomvc.app.modelo.agenda.JavaBeans;

public class AgendaDAO {

    public void salvarContato(JavaBeans contato) {
        //Pede conexao ao FabricaDAO
        try (Connection conn = FabricaDAO.conectar()) {
            //Query para o postgres
            String salvar = "INSERT INTO contatos (nome, fone, email) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(salvar);
            //Numeros 1, 2 e 3 correspondem as interrogacoes do query, em ordem iniciando em 1
            //Adiciona os dados salvos em JavaBeans na query que vai para o postgres
            pstmt.setString(1, contato.getNome());
            pstmt.setString(2, contato.getFone());
            pstmt.setString(3, contato.getEmail());
            //Executa a query
            pstmt.executeUpdate();
            //Encerra conexao
            FabricaDAO.encerrarConexao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<JavaBeans> listarContatos() {
        ArrayList<JavaBeans> contatos = new ArrayList<>();
        try (Connection conn = FabricaDAO.conectar()) {
            String listar = "SELECT * FROM contatos ORDER BY nome";
            PreparedStatement pstmt = conn.prepareStatement(listar);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String idcon = rs.getString("idcon");
                String nome = rs.getString("nome");
                String fone = rs.getString("fone");
                String email = rs.getString("email");
                JavaBeans contato = new JavaBeans(idcon, nome, fone, email);
                contatos.add(contato);
            }
            //Encerra conexao
            FabricaDAO.encerrarConexao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contatos;
    }

    public JavaBeans selecionarContato(JavaBeans contato) {
        JavaBeans contatoSelecionado = null;
        try (Connection conn = FabricaDAO.conectar()) {
            String selecionar = "SELECT * FROM contatos WHERE idcon = ?";
            try {
                PreparedStatement pstmt = conn.prepareStatement(selecionar);
                pstmt.setInt(1, Integer.parseInt(contato.getIdcon()));
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    String idcon = rs.getString("idcon");
                    String nome = rs.getString("nome");
                    String fone = rs.getString("fone");
                    String email = rs.getString("email");
                    contatoSelecionado = new JavaBeans(idcon, nome, fone, email);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            FabricaDAO.encerrarConexao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contatoSelecionado;
    }

    public void alterarContato(JavaBeans contato) {
        try (Connection conn = FabricaDAO.conectar()) {
            String alterar = "UPDATE contatos SET nome=?,fone=?,email=? WHERE idcon=?";
            PreparedStatement pstmt = conn.prepareStatement(alterar);
            pstmt.setString(1, contato.getNome());
            pstmt.setString(2, contato.getFone());
            pstmt.setString(3, contato.getEmail());
            pstmt.setInt(4, Integer.parseInt(contato.getIdcon()));
            pstmt.executeUpdate();
            FabricaDAO.encerrarConexao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarContato(JavaBeans contato) {
        try (Connection conn = FabricaDAO.conectar()) {
            String deletar = "DELETE FROM contatos WHERE idcon=?";
            PreparedStatement pstmt = conn.prepareStatement(deletar);
            pstmt.setInt(1, Integer.parseInt(contato.getIdcon()));
            pstmt.executeUpdate();
            FabricaDAO.encerrarConexao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
