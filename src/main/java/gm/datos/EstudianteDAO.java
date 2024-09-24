package gm.datos;

import gm.conexion.Conexion;
import gm.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//DAO - DATA Access Object
public class EstudianteDAO {

    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion= Conexion.getConexion();
        String sql= "SELECT * FROM estudiante ORDER BY id_estudiante";

        try {
        ps= conexion.prepareStatement(sql);
        rs= ps.executeQuery();

        while(rs.next()) {
            Estudiante estudiante = new Estudiante();
            estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
            estudiante.setNombre(rs.getString("nombre"));
            estudiante.setApellido(rs.getString("apellido"));
            estudiante.setEmail(rs.getString("email"));
            estudiante.setTelefono(rs.getString("telefono"));
            estudiantes.add(estudiante);
        }


        } catch ( Exception e ) {
            System.out.println("Ocurrio un erro al seleccionar datos: " + e.getMessage());
        }

        finally {
            try {
                conexion.close();;
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar conexion" +e.getMessage());
            }
        }

        return estudiantes;

    }

    public boolean buscarEstudiantePorId(Estudiante estudiante) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion= Conexion.getConexion();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante=?";
        try {
         ps= conexion.prepareStatement(sql);
         ps.setInt(1,estudiante.getIdEstudiante());
         rs= ps.executeQuery();
         if(rs.next()){
             estudiante.setNombre(rs.getString("nombre"));
             estudiante.setApellido(rs.getString("apellido"));
             estudiante.setTelefono(rs.getString("telefono"));
             estudiante.setEmail(rs.getString("email"));
             return true;
         }
        } catch (SQLException e) {
            System.out.println("Ocurrio un error : "+e.getMessage());
        }

        finally {
            try{
            conexion.close();
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar conexion" +e.getMessage());
            }

        }

        return false;
    }

    public boolean agregarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection conexion= Conexion.getConexion();
        String sql = "INSERT INTO estudiante(nombre,apellido,telefono,email)" +
                "VALUES(?,?,?,?)";
        try {
            ps= conexion.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar un estudiante : "+e.getMessage());
        }

        finally {
            try{
            conexion.close();
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar conexion" +e.getMessage());
            }
        }

        return false;
    }

    public boolean modificarEstudiante(Estudiante estudiante) {
        PreparedStatement ps;
        Connection conexion= Conexion.getConexion();
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, "+
                "email=? WHERE id_estudiante=?";
        try {
        ps= conexion.prepareStatement(sql);
        ps.setString(1,estudiante.getNombre());
        ps.setString(2,estudiante.getApellido());
        ps.setString(3,estudiante.getTelefono());
        ps.setString(4,estudiante.getEmail());
        ps.setInt(5, estudiante.getIdEstudiante());
        ps.execute();
        return true;
        } catch (SQLException e) {
            System.out.println("Error al modificar estudiante : " + e.getMessage());
        }

        finally {
            try{
            conexion.close();
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar conexion" +e.getMessage());
            }
        }

        return false;

    }

    public boolean eliminarEstudiante(Estudiante estudiante) {
        PreparedStatement  ps;
        Connection conexion = Conexion.getConexion();
        String sql= "DELETE FROM estudiante WHERE id_estudiante=?";

        try {
        ps=conexion.prepareStatement(sql);
        ps.setInt(1,estudiante.getIdEstudiante());
        ps.execute();
        return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar al estudiante: "+ e.getMessage());
        }

        finally {
            try{
            conexion.close();
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar conexion" +e.getMessage());
            }
        }

        return false;

    }

}
