package com.estudomvc.app.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FabricaDAO {

    private static Connection conn = null;

    public static Connection conectar() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                DataSource ds = (DataSource) envContext.lookup("jdbc/dbagenda");
                conn = ds.getConnection();
            } catch (NamingException e) {
                throw new SQLException("Não foi possível obter o DataSource.", e);
            }
        }
        return conn;
    }

    public static void encerrarConexao() throws SQLException {
        if (conn != null || !conn.isClosed()) {
            conn.close();
        }
    }
}