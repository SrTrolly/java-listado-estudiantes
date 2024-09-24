package gm.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection getConexion() {
        Connection conexion = null;
        String baseDatos= "estudiantes_db";
        String url= "jdbc:mysql://localhost:3306/" + baseDatos;
        String usuario= "root";
        String password = "admin";
        //Cargamos la clase del driver de mysql en memoria

        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion= DriverManager.getConnection(url, usuario,password);
        }catch (ClassNotFoundException| SQLException e){
            System.out.println("Ocurrio un error en la conexion: " + e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) {
        var conexion = getConexion();
        if (conexion != null) {
            System.out.println("Conexion exitosa: " + conexion);
        } else {
            System.out.println("Errol al conectarse");
        }

    }

}
