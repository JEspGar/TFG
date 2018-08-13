/**
 *
 * @author cc
 */
package web.recursos;


import java.sql.*;
import java.util.Vector;
import java.util.Collection;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import web.data.User;
import web.data.asignatura;
import web.data.titulacion;
import web.forms.asignaturasForm;
import web.forms.registroForm;
import web.forms.titulacionForm;
import web.data.grupo;


public class gestionUsuarios
{
    BBDD bbdd; //Base de Datos sobre la que se conectará

    public gestionUsuarios (String driver, String url, String usuario, String password)
    {
        //Al instanciar esta clase crea un objeto BBDD
        //para permitir conexiones y desconexiones
        bbdd = new BBDD(driver,url,usuario,password);
    }
    
    public String comprobarUsuario(String usuario, String password)
    {
        try
        {
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT role FROM profesores ";
                   query += "WHERE (email='"+usuario+"' ";
                   query += "AND password = '"+password+"') ";

       

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);
            
            String role="";

            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            if(resultado.next())
            {
                role=resultado.getString("role");
                
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return role;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a la titulacion en la Base de Datos: "+e.getMessage());
            return null;
        }
    }
}
