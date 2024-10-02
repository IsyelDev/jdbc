package org.isyel;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        Connection conn = getConnection();
        desconectarDataBase(conn);
    }

    // URL de conexión a la base de datos
    public static String url = "jdbc:mysql://localhost:3306/vivero"; // Cambia "mi_base_de_datos" por el nombre de tu base de datos
    public static String usuario = "xxxx"; // Cambia "tu_usuario" por tu nombre de usuario
    public static String contrasena = "xxxxx"; // Cambia "tu_contrasena" por tu contraseña

    public static Connection conexion = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet resultSet = null;


    public static Connection getConnection() {
        try {
            // Paso 1: Establecer la conexión
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión establecida con éxito!");

            // Paso 2: Crear un PreparedStatement
            String sql = "SELECT * FROM cliente WHERE ciudad like ?"; // Consulta SQL con un parámetro
            preparedStatement = conexion.prepareStatement(sql);

            String ciudad = "Madrid";
            preparedStatement.setString(1, "%" + ciudad + "%"); // Establece el valor del parámetro (edad > 18)

            // Paso 3: Ejecutar la consulta
            resultSet = preparedStatement.executeQuery();

            // Paso 4: Procesar los resultados
            while (resultSet.next()) {
                int id_cliente = resultSet.getInt("id_cliente");
                String nombre = resultSet.getString("nombre_cliente");
                String ciudades = resultSet.getString("ciudad");
                System.out.println("ID: " + id_cliente + ", Nombre: " + nombre + ", Ciudades: " + ciudad);
            }
        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return null; // Devuelve null si no se puede establecer la conexión
    }

    public static void desconectarDataBase(Connection conn) {

        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (conexion != null) conexion.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}




