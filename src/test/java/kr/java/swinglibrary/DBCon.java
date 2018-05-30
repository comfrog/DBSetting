package kr.java.swinglibrary;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBCon {
	private Connection connection;
	
	private static final DBCon instance = new DBCon();
	private String dbName;

	private DBCon() {}
	
	public static DBCon getInstance() {
		return instance;
	}

	
	
	public Connection getConnection() {
		return connection;
	}

	public String getDbName() {
		return dbName;
	}

	public Connection getConnection(String propPath) {
        Properties properties = new Properties();
        try (InputStream is = ClassLoader.getSystemResourceAsStream(propPath)) {
            properties.load(is);
            dbName = properties.getProperty("dbname");
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.printf("%s - %s%n", e.getErrorCode(), e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

}

	
	
	public static void setInstance(DBCon instance) {
		DBCon.instance = instance;
	}

	private DBCon() {}

	public void connectionClose() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	
}
