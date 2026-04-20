//Anthony Suc SP P3 abril 2026
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Controlador.clsPeliculas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ferito
 */
public class PeliculasDAO {
    // Consultas SQL estandarizadas
    private static final String SQL_SELECT = "SELECT pelid, pelnombre, pelclasificacion, pelgenero, pelsubtitulado, pelidioma, pelprecio FROM tblpeliculas";
    private static final String SQL_INSERT = "INSERT INTO tblpeliculas(pelnombre, pelclasificacion, pelgenero, pelsubtitulado, pelidioma, pelprecio) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE tblpeliculas SET pelnombre=?, pelclasificacion=?, pelgenero=?, pelsubtitulado=?, pelidioma=?, pelprecio=? WHERE pelid = ?";
    private static final String SQL_DELETE = "DELETE FROM tblpeliculas WHERE pelid=?";
    private static final String SQL_QUERY = "SELECT pelid, pelnombre, pelclasificacion, pelgenero, pelsubtitulado, pelidioma, pelprecio FROM tblpeliculas WHERE pelid = ?";

    public List<clsPeliculas> mSeleccionarPeliculas() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clsPeliculas pelicula = null;
        List<clsPeliculas> peliculas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("pelid");
                String nombre = rs.getString("pelnombre");
                String clasificacion = rs.getString("pelclasificacion");
                String genero = rs.getString("pelgenero");
                String subtitulado = rs.getString("pelsubtitulado");
                String idioma = rs.getString("pelidioma");
                double precio = rs.getDouble("pelprecio");

                pelicula = new clsPeliculas();
                pelicula.setIPeliId(id);
                pelicula.setSPeliNombre(nombre);
                pelicula.setSPeliClasificacion(clasificacion);
                pelicula.setSPeliGenero(genero);
                pelicula.setSPeliSubtitulado(subtitulado);
                pelicula.setSPeliIdioma(idioma);
                pelicula.setDbPeliPrecio(precio);
                peliculas.add(pelicula);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return peliculas;
    }

    public int mInsertarPelicula(clsPeliculas p_pelicula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, p_pelicula.getSPeliNombre());
            stmt.setString(2, p_pelicula.getSPeliClasificacion());
            stmt.setString(3, p_pelicula.getSPeliGenero());
            stmt.setString(4, p_pelicula.getSPeliSubtitulado());
            stmt.setString(5, p_pelicula.getSPeliIdioma());
            stmt.setDouble(6, p_pelicula.getDbPeliPrecio());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int mActualizarPelicula(clsPeliculas p_pelicula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, p_pelicula.getSPeliNombre());
            stmt.setString(2, p_pelicula.getSPeliClasificacion());
            stmt.setString(3, p_pelicula.getSPeliGenero());
            stmt.setString(4, p_pelicula.getSPeliSubtitulado());
            stmt.setString(5, p_pelicula.getSPeliIdioma());
            stmt.setDouble(6, p_pelicula.getDbPeliPrecio());
            stmt.setInt(7, p_pelicula.getIPeliId());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int mBorrarPelicula(clsPeliculas p_pelicula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, p_pelicula.getIPeliId());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public clsPeliculas mBuscarPelicula(clsPeliculas p_pelicula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, p_pelicula.getIPeliId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                p_pelicula.setSPeliNombre(rs.getString("pelnombre"));
                p_pelicula.setSPeliClasificacion(rs.getString("pelclasificacion"));
                p_pelicula.setSPeliGenero(rs.getString("pelgenero"));
                p_pelicula.setSPeliSubtitulado(rs.getString("pelsubtitulado"));
                p_pelicula.setSPeliIdioma(rs.getString("pelidioma"));
                p_pelicula.setDbPeliPrecio(rs.getDouble("pelprecio"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return p_pelicula;
    }
}
