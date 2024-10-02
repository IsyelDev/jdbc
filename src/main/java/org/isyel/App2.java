package org.isyel;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class App2 {
// Video de Egg
    public static void main(String[] args) {
        Connection conn = getConnection();
            desconectarDataBase(conn);
    
    }

    private static final String URL = "jdbc:mysql://localhost:3306/vivero"; // Cambia "mi_base_de_datos" por el nombre de tu base de datos
    private static final String USER = "tu_usuario"; // Cambia "tu_usuario" por tu nombre de usuario
    private static final String PASSWORD = "tu_contrasena"; // Cambia "tu_contrasena" por tu contraseña

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Cambia el driver si es necesario

    protected static Connection conexion = null;
    protected static ResultSet resultSet = null;
    protected static Statement statement = null;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
            return conexion; // Retorna la conexión
        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Driver no encontrado: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return null; // Devuelve null si no se puede establecer la conexión
    }


    public static void desconectarDataBase(Connection conexion) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error cerrando la conexión: " + e.getMessage());
        }
    }

}


