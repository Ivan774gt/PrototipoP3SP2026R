//Ferdynand Monroy 9959 24 14049 abril 2026
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.PeliculasDAO;
import java.util.List;
import java.util.ArrayList; 
/**
 *
 * @author ferito
 */
public class clsPeliculas {
    // Atributos estandarizados (m_ + tipo + nombre)
    private int m_iPeliId;
    private String m_sPeliNombre;
    private String m_sPeliClasificacion;
    private String m_sPeliGenero;
    private String m_sPeliSubtitulado;
    private String m_sPeliIdioma;
    private double m_dbPeliPrecio;

    public clsPeliculas() {
    }

    // Getters y Setters
    public int getIPeliId() { return m_iPeliId; }
    public void setIPeliId(int p_iPeliId) { this.m_iPeliId = p_iPeliId; }

    public String getSPeliNombre() { return m_sPeliNombre; }
    public void setSPeliNombre(String p_sPeliNombre) { this.m_sPeliNombre = p_sPeliNombre; }

    public String getSPeliClasificacion() { return m_sPeliClasificacion; }
    public void setSPeliClasificacion(String p_sPeliClasificacion) { this.m_sPeliClasificacion = p_sPeliClasificacion; }

    public String getSPeliGenero() { return m_sPeliGenero; }
    public void setSPeliGenero(String p_sPeliGenero) { this.m_sPeliGenero = p_sPeliGenero; }

    public String getSPeliSubtitulado() { return m_sPeliSubtitulado; }
    public void setSPeliSubtitulado(String p_sPeliSubtitulado) { this.m_sPeliSubtitulado = p_sPeliSubtitulado; }

    public String getSPeliIdioma() { return m_sPeliIdioma; }
    public void setSPeliIdioma(String p_sPeliIdioma) { this.m_sPeliIdioma = p_sPeliIdioma; }

    public double getDbPeliPrecio() { return m_dbPeliPrecio; }
    public void setDbPeliPrecio(double p_dbPeliPrecio) { this.m_dbPeliPrecio = p_dbPeliPrecio; }

    // --- MÉTODOS DE OPERACIÓN (CRUD) ---

    public List<clsPeliculas> mGetListadoPeliculas() {
        PeliculasDAO peliculasDAO = new PeliculasDAO();
        return peliculasDAO.mSeleccionarPeliculas();
    }

    public int mSetIngresarPelicula(clsPeliculas p_pelicula) {
        PeliculasDAO peliculasDAO = new PeliculasDAO();
        return peliculasDAO.mInsertarPelicula(p_pelicula);
    }

    public int mSetModificarPelicula(clsPeliculas p_pelicula) {
        PeliculasDAO peliculasDAO = new PeliculasDAO();
        return peliculasDAO.mActualizarPelicula(p_pelicula);
    }

    public int mSetBorrarPelicula(clsPeliculas p_pelicula) {
        PeliculasDAO peliculasDAO = new PeliculasDAO();
        return peliculasDAO.mBorrarPelicula(p_pelicula);
    }

    public clsPeliculas mGetBuscarPelicula(clsPeliculas p_pelicula) {
        PeliculasDAO peliculasDAO = new PeliculasDAO();
        return peliculasDAO.mBuscarPelicula(p_pelicula);
    }
}
