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
import web.forms.grupoForm;
import web.forms.registroForm;
import web.data.grupo;


public class gestionGrupos
{
    BBDD bbdd; //Base de Datos sobre la que se conectará

    public gestionGrupos (String driver, String url, String usuario, String password)
    {
        //Al instanciar esta clase crea un objeto BBDD
        //para permitir conexiones y desconexiones
        bbdd = new BBDD(driver,url,usuario,password);
    }

     //Devuelve un vector con todas las asignaturas
    public Vector listaAsignaturas(String nombre_titulacion)
    {
        try
        {
            Date fechaActual = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query =  "SELECT * FROM asignaturatotal ";
                   query += "WHERE fechainicio <='" + formato.format(fechaActual) +"' ";
                   query += "AND fechafin >= '"+formato.format(fechaActual)+"' AND tit_nombre='"+nombre_titulacion+"' AND activa = 's'";

            //Se crea un vector de asignaturas
            Vector  vectorAsignaturas = new Vector();

            //Se ejecuta la query 
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);
           
            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            while(resultado.next())
            {
                asignatura asignatura = new asignatura();
                
                asignatura.setCodigo(resultado.getString("codigo"));
                asignatura.setTitulo(resultado.getString("titulo"));
                asignatura.setFechaInicio(resultado.getDate("fechainicio"));
                asignatura.setFechaFin(resultado.getDate("fechafin"));
                asignatura.setResponsable(resultado.getString("responsable"));
                asignatura.setEmail(resultado.getString("email"));
                asignatura.setTelefono(resultado.getString("telefono"));
                
                //Se añade la asignatura al vector de asignaturas
                vectorAsignaturas.add(asignatura);
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return vectorAsignaturas;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a las asignaturas de la Base de Datos: "+e.getMessage());
            return null;
        }
    }

    //Devuelve un vector con todas las titulaciones 
    public Vector listaTitulaciones()
    {
        try
        {

            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  =  "SELECT * FROM titulaciones WHERE activa='s'";

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

     //Devuelve un vector con todas los grupos
    public Vector listaGrupos(String asignatura)
    {
        try
        {
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT * FROM grupos ";
                   query += "WHERE asignasoc='"+asignatura+"' AND activa='s'";

            //Se crea un vector de grupos
            Vector  vectorGrupos = new Vector();

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            while(resultado.next())
            {
                grupo grupo = new grupo();

                grupo.setCodigoLab(resultado.getInt("codigolab"));
                grupo.setAsigAsoc(resultado.getString("asignasoc"));
                grupo.setDia(resultado.getString("dia"));
                grupo.setHora(resultado.getString("hora"));
                grupo.setPlazas(resultado.getInt("plazas"));
                grupo.setPlazasOcupadas(resultado.getInt("plazasocupadas"));
                grupo.setObservaciones(resultado.getString("observaciones"));

                //Se añade el grupo al vector de grupos
                vectorGrupos.add(grupo);
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return vectorGrupos;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a los grupos de la Base de Datos: "+e.getMessage());
            return null;
        }
    }

    public grupo datosGrupo(String codGrupo)
    {
        try
        {
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT * FROM grupos ";
                   query += "WHERE codigolab='"+codGrupo+"'";

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);
            
            grupo grupo=new grupo();

            //Extraemos las plazas ocupadas actualmente y actualizamos
            if(resultado.next())
            {                
            	grupo.setAsigAsoc(resultado.getString("asignasoc"));
                grupo.setCodigoLab(Integer.parseInt(resultado.getString("codigolab")));
                grupo.setDia(resultado.getString("dia"));
                grupo.setHora(resultado.getString("hora"));
                grupo.setObservaciones(resultado.getString("observaciones"));
                grupo.setPlazas(Integer.parseInt(resultado.getString("plazas")));
                grupo.setPlazasOcupadas(Integer.parseInt(resultado.getString("plazasocupadas")));
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return grupo;
        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer los datos del grupo en la Base de Datos: "+e.getMessage());
            return null;
        }
    }    
    
    public Vector listaAsignaturasTitulacion(String codigo_titulacion)
    {
        try
        {            
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query =  "SELECT * FROM asignaturas ";
                   query += "WHERE titulacion='"+codigo_titulacion+"'";

            //Se crea un vector de asignaturas
            Vector  vectorAsignaturas = new Vector();

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            while(resultado.next())
            {
                asignatura asignatura = new asignatura();

                asignatura.setCodigo(resultado.getString("codigo"));
                asignatura.setTitulo(resultado.getString("titulo"));
                asignatura.setFechaInicio(resultado.getDate("fechainicio"));
                asignatura.setFechaFin(resultado.getDate("fechafin"));
                asignatura.setResponsable(resultado.getString("responsable"));
                asignatura.setEmail(resultado.getString("email"));
                asignatura.setTelefono(resultado.getString("telefono"));

                //Se añade la asignatura al vector de asignaturas
                vectorAsignaturas.add(asignatura);
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return vectorAsignaturas;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a las asignaturas de la Base de Datos: "+e.getMessage());
            return null;
        }
    }
    
    public Vector listaGruposAsignatura(String asignatura)
    {
        try
        {            
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query =  "SELECT * FROM grupos ";
                   query += "WHERE asignasoc='"+asignatura+"' AND activa='s'";

            //Se crea un vector de asignaturas
            Vector  vectorGrupos = new Vector();

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            while(resultado.next())
            {
                grupo grupo = new grupo();

                grupo.setAsigAsoc(resultado.getString("asignasoc"));
                grupo.setCodigoLab(Integer.parseInt(resultado.getString("codigolab")));
                grupo.setDia(resultado.getString("dia"));
                grupo.setHora(resultado.getString("hora"));
                grupo.setObservaciones(resultado.getString("observaciones"));
                grupo.setPlazas(Integer.parseInt(resultado.getString("plazas")));
                grupo.setPlazasOcupadas(Integer.parseInt(resultado.getString("plazasocupadas")));

                //Se añade la asignatura al vector de asignaturas
                vectorGrupos.add(grupo);
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return vectorGrupos;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a los grupos de la Base de Datos: "+e.getMessage());
            return null;
        }
    }
    
    public asignatura datosAsignatura(String codigo)
    {
        try
        {
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT * FROM asignaturas ";
                   query += "WHERE codigo='"+codigo+"'";

            //Se crea una instancia de asignatura
            asignatura asignatura = new asignatura();

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            if(resultado.next())
            {
                asignatura.setCodigo(resultado.getString("codigo"));
                asignatura.setTitulo(resultado.getString("titulo"));
                asignatura.setFechaInicio(resultado.getDate("fechainicio"));
                asignatura.setFechaFin(resultado.getDate("fechafin"));
                asignatura.setResponsable(resultado.getString("responsable"));
                asignatura.setEmail(resultado.getString("email"));
                asignatura.setTelefono(resultado.getString("telefono"));
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return asignatura;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a la titulacion en la Base de Datos: "+e.getMessage());
            return null;
        }
    } 
    
    public asignatura comprobarAsignatura(String codigo, String titulo, String titulacion)
    {
        try
        {
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT * FROM asignaturas ";
                   query += "WHERE (codigo='"+codigo+"' ";
                   query += "OR titulo = '"+titulo+"') ";
                   query += "AND titulacion='"+titulacion+"'";

            //Se crea una instancia de asignatura
            asignatura asignatura = new asignatura();

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            if(resultado.next())
            {
                asignatura.setCodigo(resultado.getString("codigo"));
                asignatura.setTitulo(resultado.getString("titulo"));
                asignatura.setFechaInicio(resultado.getDate("fechainicio"));
                asignatura.setFechaFin(resultado.getDate("fechafin"));
                asignatura.setResponsable(resultado.getString("responsable"));
                asignatura.setEmail(resultado.getString("email"));
                asignatura.setTelefono(resultado.getString("telefono"));
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return asignatura;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a la asignatura en la Base de Datos: "+e.getMessage());
            return null;
        }
    }
    
    public boolean registrarGrupo (grupoForm formulario)
    {
    	boolean valido = false;
    	try{	
	    
	        //Preparamos la query
	        String query  = "INSERT INTO grupos ";
	               query += "(asignasoc,dia,hora,plazas,observaciones) ";
	
	               query += "VALUES ('"+formulario.getAsigAsoc()+"','"+formulario.getDia()+"','"+
	            		   formulario.getHora()+"','"+formulario.getPlazas()+"','"+formulario.getObservaciones()+"')";

        
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
            System.out.println("Error al inscribir al alumno en la Base de Datos: "+e.getMessage());
            return valido;
        }
    }
    
    public boolean modificarGrupo (grupoForm formulario)
    {
    	 boolean valido = false;
    	try
        {	
	        //Preparamos la query
	        String query  = "UPDATE grupos ";
	               query += "SET dia= '"+formulario.getDia()+"', ";
	               query += "hora = '"+formulario.getHora()+"', plazas = '"+formulario.getPlazas()+"', ";
	               query += "observaciones = '"+formulario.getObservaciones()+"' WHERE codigolab = '"+formulario.getCodigoLab()+"'";

        
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
            System.out.println("Error al actualizar el grupo: "+e.getMessage());
            return valido;
        }
    }
    
    public boolean borrarGrupo(String codGrupo)
    {
        boolean valido = false;

        //Se prepara la query
        String query  = "UPDATE grupos SET activa='n' ";
               query += "WHERE codigolab='"+codGrupo+"'";

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
            System.out.println("Error al borrar la asignatura de la base de datos: "+e.getMessage());
            return valido;
        }
    }
}
