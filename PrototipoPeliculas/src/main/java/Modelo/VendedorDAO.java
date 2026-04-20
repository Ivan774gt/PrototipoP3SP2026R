/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Controlador.clsVendedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author deleo
 */
public class VendedorDAO {
    
    private static final String SQL_SELECT = 
        "SELECT codigo_vendedor, nombre_vendedor, direccion_vendedor, telefono_vendedor, nit_vendedor, estatus_vendedor FROM vendedores";

    private static final String SQL_INSERT = 
        "INSERT INTO vendedores(codigo_vendedor, nombre_vendedor, direccion_vendedor, telefono_vendedor, nit_vendedor, estatus_vendedor) VALUES(?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = 
        "UPDATE vendedores SET nombre_vendedor=?, direccion_vendedor=?, telefono_vendedor=?, nit_vendedor=?, estatus_vendedor=? WHERE codigo_vendedor=?";

    private static final String SQL_DELETE = 
        "DELETE FROM vendedores WHERE codigo_vendedor=?";

    private static final String SQL_SELECT_CODIGO = 
        "SELECT codigo_vendedor, nombre_vendedor, direccion_vendedor, telefono_vendedor, nit_vendedor, estatus_vendedor FROM vendedores WHERE codigo_vendedor = ?";


    // CONSULTA GENERAL
    public List<clsVendedor> consultaVendedores() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<clsVendedor> vendedores = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                clsVendedor vendedor = new clsVendedor();
                vendedor.setCodigo_vendedor(rs.getString("codigo_vendedor"));
                vendedor.setNombre_vendedor(rs.getString("nombre_vendedor"));
                vendedor.setDireccion_vendedor(rs.getString("direccion_vendedor"));
                vendedor.setTelefono_vendedor(rs.getString("telefono_vendedor"));
                vendedor.setNit_vendedor(rs.getString("nit_vendedor"));
                vendedor.setEstatus_vendedor(rs.getString("estatus_vendedor"));

                vendedores.add(vendedor);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return vendedores;
    }

    //INSERT
    public int ingresaVendedor(clsVendedor vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, vendedor.getCodigo_vendedor());
            stmt.setString(2, vendedor.getNombre_vendedor());
            stmt.setString(3, vendedor.getDireccion_vendedor());
            stmt.setString(4, vendedor.getTelefono_vendedor());
            stmt.setString(5, vendedor.getNit_vendedor());
            stmt.setString(6, vendedor.getEstatus_vendedor());

            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    //UPDATE
    public int actualizaVendedor(clsVendedor vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, vendedor.getNombre_vendedor());
            stmt.setString(2, vendedor.getDireccion_vendedor());
            stmt.setString(3, vendedor.getTelefono_vendedor());
            stmt.setString(4, vendedor.getNit_vendedor());
            stmt.setString(5, vendedor.getEstatus_vendedor());
            stmt.setString(6, vendedor.getCodigo_vendedor());

            System.out.println("Ejecutando query: " + SQL_UPDATE);
            rows = stmt.executeUpdate();

            System.out.println("Registros actualizados: " + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    //DELETE
    public int borrarVendedor(clsVendedor vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, vendedor.getCodigo_vendedor());

            System.out.println("Ejecutando query: " + SQL_DELETE);
            rows = stmt.executeUpdate();

            System.out.println("Registros eliminados: " + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    //BUSCAR POR CÓDIGO
    public clsVendedor consultaVendedorPorCodigo(clsVendedor vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_CODIGO);
            stmt.setString(1, vendedor.getCodigo_vendedor());

            System.out.println("Ejecutando query: " + SQL_SELECT_CODIGO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                vendedor.setNombre_vendedor(rs.getString("nombre_vendedor"));
                vendedor.setDireccion_vendedor(rs.getString("direccion_vendedor"));
                vendedor.setTelefono_vendedor(rs.getString("telefono_vendedor"));
                vendedor.setNit_vendedor(rs.getString("nit_vendedor"));
                vendedor.setEstatus_vendedor(rs.getString("estatus_vendedor"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return vendedor;
    }
}
