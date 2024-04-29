package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBCon {

	private static Connection conn = null;

	// ABRINDO CONEXÃO COM BANDO DE DADOS
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = readProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

		return conn;
	}

	// FECHANDO CONEXÃO COM BANCO DE DADOS
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

	}

// METADO PARA ABRIR O ARQUIVO db.Properties E GERAR A CONEXÃO COM BANCO DE DADOS
	public static Properties readProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();

			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	// FECHAR O ST E RS QUE SÃO COMANDOS DO SQL E NÃO DA JVM
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}
	}
	// fechando classe
}
