/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.recursos;

/**
 *
 * @author javier.albert
 */
import java.sql.*;
import java.util.Vector;
import java.util.Date;
import java.text.SimpleDateFormat;
import web.data.mensaje;


public class gestionMensajes {
    BBDD bbdd; //Base de Datos sobre la que se conectará

    public gestionMensajes(String driver, String url, String usuario, String password)
    {
        //Al instanciar esta clase crea un objeto BBDD
        //para permitir conexiones y desconexiones
        bbdd = new BBDD(driver,url,usuario,password);
    }
    //Devuelve un vector con todas las titulaciones 
    public Vector listaMensajes()
    {
        try
        {

            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();
            //Se prepara la query
            String query  =  "SELECT * FROM mensajes ";

            //Se crea un vector de mensajes
            Vector  vectorMensajes = new Vector();

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            while(resultado.next())
            {                
                mensaje mensajes= new mensaje();
                mensajes.setMensaje(resultado.getString("mensaje"));
                mensajes.setCaducidad(resultado.getDate("caducidad"));
                //Se añade la asignatura al vector de mensajes
                vectorMensajes.add(mensajes);
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return vectorMensajes;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a las titulaciones de la Base de Datos: "+e.getMessage());
            return null;
        }
    }

    
}
