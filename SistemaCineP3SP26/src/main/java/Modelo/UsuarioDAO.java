// Ferdynand Monroy Abril 2026 progra 3 SP
package Modelo;

import Controlador.clsUsuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.BitacoraDAO;
import Controlador.clsUsuarioConectado;

/**
 * @author Ferdynand Monroy
 */
public class UsuarioDAO {

    // Consultas SQL estandarizadas 
    private static final String SQL_SELECT = "SELECT usuid, usunombre, usucontrasena, usuultimasesion, usuestatus, usunombrereal, usucorreoe, usutelefono, usudireccion FROM tblusuario";
    private static final String SQL_INSERT = "INSERT INTO tblusuario(usunombre, usucontrasena, usuultimasesion, usuestatus, usunombrereal, usucorreoe, usutelefono, usudireccion) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE tblusuario SET usunombre=?, usucontrasena=?, usuultimasesion=?, usuestatus=?, usunombrereal=?, usucorreoe=?, usutelefono=?, usudireccion=? WHERE usuid = ?";
    private static final String SQL_DELETE = "DELETE FROM tblusuario WHERE usuid=?";
    private static final String SQL_SELECT_NOMBRE = "SELECT usuid, usunombre, usucontrasena, usuultimasesion, usuestatus, usunombrereal, usucorreoe, usutelefono, usudireccion FROM tblusuario WHERE usunombre = ?";
    private static final String SQL_SELECT_ID = "SELECT usuid, usunombre, usucontrasena, usuultimasesion, usuestatus, usunombrereal, usucorreoe, usutelefono, usudireccion FROM tblusuario WHERE usuid = ?";     

    public List<clsUsuario> consultaUsuarios() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<clsUsuario> usuarios = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                clsUsuario usuario = new clsUsuario();
                usuario.setUsuId(rs.getInt("usuid"));
                usuario.setUsuNombre(rs.getString("usunombre"));
                usuario.setUsuContrasena(rs.getString("usucontrasena"));
                usuario.setUsuUltimaSesion(rs.getString("usuultimasesion"));
                usuario.setUsuEstatus(rs.getString("usuestatus"));
                usuario.setUsuNombreReal(rs.getString("usunombrereal"));
                usuario.setUsuCorreo(rs.getString("usucorreoe")); 
                usuario.setUsuTelefono(rs.getString("usutelefono"));
                usuario.setUsuDireccion(rs.getString("usudireccion"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarios;
    }

    public int ingresaUsuarios(clsUsuario p_usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, p_usuario.getUsuNombre());
            stmt.setString(2, p_usuario.getUsuContrasena());
            stmt.setString(3, p_usuario.getUsuUltimaSesion());
            stmt.setString(4, p_usuario.getUsuEstatus());
            stmt.setString(5, p_usuario.getUsuNombreReal());
            stmt.setString(6, p_usuario.getUsuCorreo());
            stmt.setString(7, p_usuario.getUsuTelefono());
            stmt.setString(8, p_usuario.getUsuDireccion());
            rows = stmt.executeUpdate();

            if (rows > 0) {
                BitacoraDAO bitacora = new BitacoraDAO();
                bitacora.insert(clsUsuarioConectado.getUsuId(), 1, "INSERT usuario: " + p_usuario.getUsuNombre());
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizaUsuarios(clsUsuario p_usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, p_usuario.getUsuNombre());
            stmt.setString(2, p_usuario.getUsuContrasena());
            stmt.setString(3, p_usuario.getUsuUltimaSesion());
            stmt.setString(4, p_usuario.getUsuEstatus());
            stmt.setString(5, p_usuario.getUsuNombreReal());
            stmt.setString(6, p_usuario.getUsuCorreo());
            stmt.setString(7, p_usuario.getUsuTelefono());
            stmt.setString(8, p_usuario.getUsuDireccion());
            stmt.setInt(9, p_usuario.getUsuId());
            rows = stmt.executeUpdate();
            
            if (rows > 0) {
                BitacoraDAO bitacora = new BitacoraDAO();
                bitacora.insert(clsUsuarioConectado.getUsuId(), 1, "UPDATE usuario ID: " + p_usuario.getUsuId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int borrarUsuarios(clsUsuario p_usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, p_usuario.getUsuId());
            rows = stmt.executeUpdate();
            
            if (rows > 0) {
                BitacoraDAO bitacora = new BitacoraDAO();
                bitacora.insert(clsUsuarioConectado.getUsuId(), 1, "DELETE usuario ID: " + p_usuario.getUsuId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public clsUsuario consultaUsuariosPorNombre(clsUsuario p_usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_NOMBRE);
            stmt.setString(1, p_usuario.getUsuNombre());
            rs = stmt.executeQuery();
            if (rs.next()) {
                p_usuario.setUsuId(rs.getInt("usuid"));
                p_usuario.setUsuContrasena(rs.getString("usucontrasena"));
                p_usuario.setUsuUltimaSesion(rs.getString("usuultimasesion"));
                p_usuario.setUsuEstatus(rs.getString("usuestatus"));
                p_usuario.setUsuNombreReal(rs.getString("usunombrereal"));
                p_usuario.setUsuCorreo(rs.getString("usucorreoe")); 
                p_usuario.setUsuTelefono(rs.getString("usutelefono"));
                p_usuario.setUsuDireccion(rs.getString("usudireccion"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return p_usuario;
    }

    public clsUsuario consultaUsuariosPorId(clsUsuario p_usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, p_usuario.getUsuId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                p_usuario.setUsuNombre(rs.getString("usunombre"));
                p_usuario.setUsuContrasena(rs.getString("usucontrasena"));
                p_usuario.setUsuUltimaSesion(rs.getString("usuultimasesion"));
                p_usuario.setUsuEstatus(rs.getString("usuestatus"));
                p_usuario.setUsuNombreReal(rs.getString("usunombrereal"));
                p_usuario.setUsuCorreo(rs.getString("usucorreoe")); 
                p_usuario.setUsuTelefono(rs.getString("usutelefono"));
                p_usuario.setUsuDireccion(rs.getString("usudireccion"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return p_usuario;
    }
}