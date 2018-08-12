/**
 *
 * @author cc
 */
package web.recursos;


import java.sql.*;
import java.util.Vector;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import web.data.asignatura;
import web.data.titulacion;
import web.forms.asignaturasForm;
import web.forms.registroForm;
import web.forms.titulacionForm;
import web.data.grupo;


public class gestionTitulaciones
{
    BBDD bbdd; //Base de Datos sobre la que se conectará

    public gestionTitulaciones (String driver, String url, String usuario, String password)
    {
        //Al instanciar esta clase crea un objeto BBDD
        //para permitir conexiones y desconexiones
        bbdd = new BBDD(driver,url,usuario,password);
    }
    
    //Devuelve un vector con todas las titulaciones 
    public Vector listaTitulaciones()
    {
        try
        {

            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  =  "SELECT * FROM titulaciones ";

            //Se crea un vector de asignaturas
            Vector  vectorTitulaciones = new Vector();

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            while(resultado.next())
            {
                titulacion titulacion = new titulacion();

                titulacion.setCodigo(resultado.getString("codigo"));
                titulacion.setTitulacion(resultado.getString("titulacion"));

                //Se añade la asignatura al vector de asignaturas
                vectorTitulaciones.add(titulacion);
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return vectorTitulaciones;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a las titulaciones de la Base de Datos: "+e.getMessage());
            return null;
        }
    }
    
    public boolean registrarTitulacion (titulacionForm formulario)
    {
    	boolean valido = false;
    	try{	
	        	
	        //Preparamos la query
	        String query  = "INSERT INTO titulaciones ";
	               query += "(codigo,titulacion) ";
	
	               query += "VALUES ('"+formulario.getCodigo()+"','"+formulario.getTitulo()+"')";

        
            Connection conexion=bbdd.getConexion();
            Statement st=conexion.createStatement();

            //Se ejecuta la query
            st.execute(query);

            st.close();
            bbdd.cerrarConexion(conexion);
            
            valido = true;
            return valido;

        }
        catch(SQLException e)
        {
            System.out.println("Error al inscribir la titulacion en la Base de Datos: "+e.getMessage());
            return valido;
        }
    }
    
    public titulacion comprobarTitulacion(String codigo, String titulo)
    {
        try
        {
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT * FROM titulaciones ";
                   query += "WHERE (codigo='"+codigo+"' ";
                   query += "OR titulacion = '"+titulo+"') ";

            //Se crea una instancia de asignatura
            titulacion titulacion = new titulacion();

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            if(resultado.next())
            {
                titulacion.setCodigo(resultado.getString("codigo"));
                titulacion.setTitulacion(resultado.getString("titulacion"));
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return titulacion;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a la titulacion en la Base de Datos: "+e.getMessage());
            return null;
        }
    }
    
    public titulacion datosTitulacion(String codigo)
    {
        try
        {
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT * FROM titulaciones ";
                   query += "WHERE codigo='"+codigo+"'";

            //Se crea una instancia de asignatura
            titulacion titulacion = new titulacion();

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            if(resultado.next())
            {
                titulacion.setCodigo(resultado.getString("codigo"));
                titulacion.setTitulacion(resultado.getString("titulacion"));
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return titulacion;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a la titulacion en la Base de Datos: "+e.getMessage());
            return null;
        }
    } 
    
    public boolean modificarTitulacion (titulacionForm formulario)
    {
    	 boolean valido = false;
    	try
        {	
	        //Preparamos la query
	        String query  = "UPDATE titulaciones ";
	               query += "SET titulacion= '"+formulario.getTitulo().toLowerCase()+"' ";
	               query += "WHERE codigo = '"+formulario.getCodigo()+"'";

        
            Connection conexion=bbdd.getConexion();
            Statement st=conexion.createStatement();

            //Se ejecuta la query
            st.execute(query);

            st.close();
            bbdd.cerrarConexion(conexion);
            
            valido = true;
            return valido;

        }
        catch(SQLException e)
        {
            System.out.println("Error al actualizar la titulacion: "+e.getMessage());
            return valido;
        }
    }
    
    public boolean borrarTitulacion(String codTitulacion)
    {
        boolean valido = false;

        //Se prepara la query
        String query  = "DELETE FROM titulaciones ";
               query += "WHERE codigo='"+codTitulacion+"'";

        try
        {
            Connection conexion=bbdd.getConexion();
            Statement st=conexion.createStatement();

            //Se ejecuta la query
            st.execute(query);

            st.close();
            bbdd.cerrarConexion(conexion);
            
            valido = true;
            return valido;

        }
        catch(SQLException e)
        {
            System.out.println("Error al borrar la titulacion de la base de datos: "+e.getMessage());
            return valido;
        }
    }
}
