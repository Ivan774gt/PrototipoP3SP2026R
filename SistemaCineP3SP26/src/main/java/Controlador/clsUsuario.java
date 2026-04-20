// Ferdynand Monroy Abril 2026 Progra 3 SP
package Controlador;

import java.util.List;
import Modelo.UsuarioDAO;

/**
 * @author Ferdynand Monroy
 */
public class clsUsuario {
    //atributos
    private int m_iId;
    private String m_sNombre;
    private String m_sContrasena;
    private String m_sUltimaSesion;
    private String m_sEstatus;
    private String m_sNombreReal;
    private String m_sCorreo;
    private String m_sTelefono;
    private String m_sDireccion;

    // GETTERS Y SETTERS 
    public int getUsuId() { return m_iId; }
    public void setUsuId(int p_iId) { this.m_iId = p_iId; }

    public String getUsuNombre() { return m_sNombre; }
    public void setUsuNombre(String p_sNombre) { this.m_sNombre = p_sNombre; }

    public String getUsuContrasena() { return m_sContrasena; }
    public void setUsuContrasena(String p_sContrasena) { this.m_sContrasena = p_sContrasena; }

    public String getUsuUltimaSesion() { return m_sUltimaSesion; }
    public void setUsuUltimaSesion(String p_sSesion) { this.m_sUltimaSesion = p_sSesion; }

    public String getUsuEstatus() { return m_sEstatus; }
    public void setUsuEstatus(String p_sEstatus) { this.m_sEstatus = p_sEstatus; }

    public String getUsuNombreReal() { return m_sNombreReal; }
    public void setUsuNombreReal(String p_sReal) { this.m_sNombreReal = p_sReal; }

    public String getUsuCorreo() { return m_sCorreo; }
    public void setUsuCorreo(String p_sCorreo) { this.m_sCorreo = p_sCorreo; }

    public String getUsuTelefono() { return m_sTelefono; }
    public void setUsuTelefono(String p_sTel) { this.m_sTelefono = p_sTel; }

    public String getUsuDireccion() { return m_sDireccion; }
    public void setUsuDireccion(String p_sDir) { this.m_sDireccion = p_sDir; }

    // CONSTRUCTORES
    public clsUsuario() { }

    public clsUsuario(int p_iId) {
        this.m_iId = p_iId;
    }

    // Constructor completo
    public clsUsuario(int p_iId, String p_sNom, String p_sPass, String p_sSes, String p_sEst, String p_sReal, String p_sCor, String p_sTel, String p_sDir) {
        this.m_iId = p_iId;
        this.m_sNombre = p_sNom;
        this.m_sContrasena = p_sPass;
        this.m_sUltimaSesion = p_sSes;
        this.m_sEstatus = p_sEst;
        this.m_sNombreReal = p_sReal;
        this.m_sCorreo = p_sCor;
        this.m_sTelefono = p_sTel;
        this.m_sDireccion = p_sDir;
    }

    @Override
    public String toString() {
        return "clsUsuario{" + "Id=" + m_iId + ", Nombre=" + m_sNombre + ", Estatus=" + m_sEstatus + '}';
    }

    // MÉTODOS DE ACCESO A CAPA CONTROLADOR 
    public clsUsuario mBuscarUsuarioPorNombre(clsUsuario p_usuario) {
        UsuarioDAO daousuario = new UsuarioDAO();
        return daousuario.consultaUsuariosPorNombre(p_usuario);
    }

    public List<clsUsuario> mGetListadoUsuarios() {
        UsuarioDAO daousuario = new UsuarioDAO();
        return daousuario.consultaUsuarios();
    }

    public int mSetIngresarUsuario(clsUsuario p_usuario) {
        UsuarioDAO daousuario = new UsuarioDAO();
        return daousuario.ingresaUsuarios(p_usuario);
    }

    public int mSetModificarUsuario(clsUsuario p_usuario) {
        UsuarioDAO daousuario = new UsuarioDAO();
        return daousuario.actualizaUsuarios(p_usuario);
    }

    public int mSetBorrarUsuario(clsUsuario p_usuario) {
        UsuarioDAO daousuario = new UsuarioDAO();
        return daousuario.borrarUsuarios(p_usuario);
    }
}