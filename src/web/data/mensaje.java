/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.data;

/**
 *
 * @author javier.albert
 */
import java.util.Date;

public class mensaje {
    private int id;
    private String mensaje;
    private Date fecha;
    private Date caducidad;
    
    public String getmensaje(){
        return mensaje;
    }
    public void setMensaje(String mensaje)
    {
        this.mensaje= mensaje;
    }
    
    public Date getFecha()
    {
        return fecha;
    }
    public void setFecha(Date fecha)
    {
        this.fecha= fecha;
    }    
    public Date getCaducidad()
    {
        return caducidad;
    }
    public void setCaducidad(Date caducidad)
    {
        this.caducidad=caducidad;
    }
    
    
    
    
}
