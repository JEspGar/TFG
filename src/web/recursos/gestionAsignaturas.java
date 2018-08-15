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
import web.data.grupo;


public class gestionAsignaturas
{
    BBDD bbdd; //Base de Datos sobre la que se conectará

    public gestionAsignaturas (String driver, String url, String usuario, String password)
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

    public String nombreAsociado(String codAsignatura)
    {
        try
        {
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT titulo FROM asignaturas ";
                   query += "WHERE codigo='"+codAsignatura+"'";

            //Se crea un nombre de asignatura
            String  nombreAsignatura ="";

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            if(resultado.next())
            {
                nombreAsignatura = resultado.getString("titulo");
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return nombreAsignatura;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a la asignatura en la Base de Datos: "+e.getMessage());
            return null;
        }
    }
    
    public String codigoAsociado(String asignatura,String titulacion)
    {
        try
        {
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT codigo FROM asignaturas ";
                   query += "WHERE titulo='"+asignatura+"' AND titulacion='"+titulacion+"'";

            //Se crea un codigo de asignatura
            String  codigoAsignatura ="";

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            if(resultado.next())
            {
                codigoAsignatura = resultado.getString("codigo");
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return codigoAsignatura;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a la asignatura en la Base de Datos: "+e.getMessage());
            return null;
        }
    }

    public String codigoTitAsociado(String carrera)
    {
        try
        {
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT codigo FROM titulaciones ";
                   query += "WHERE titulacion='"+carrera+"'";

            //Se crea un codigo de titulacion
            String  codigoTitulacion ="";

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            if(resultado.next())
            {
                codigoTitulacion = resultado.getString("codigo");
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return codigoTitulacion;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a la titulacion en la Base de Datos: "+e.getMessage());
            return null;
        }
    }

    public void actualizarPlazas(String grupo)
    {
        try
        {
            int plazas;

            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT plazasocupadas FROM grupos ";
                   query += "WHERE codigolab='"+grupo+"'";

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Extraemos las plazas ocupadas actualmente y actualizamos
            resultado.next();
            plazas = resultado.getInt(1);
            plazas += 1;

            //Se prepara la query
            String query2  = "UPDATE grupos ";
                   query2 += "SET plazasocupadas='"+plazas+"' ";
                   query2 += "WHERE codigolab='"+grupo+"'";

            Statement st2 = conexion.createStatement();

            //Se ejecuta la query
            st2.executeUpdate(query2);

            st2.close();

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

        }
        catch(SQLException e)
        {
            System.out.println("Error al actualizar las plazas en la Base de Datos: "+e.getMessage());
        }
    }

    public void datosGrupo(grupo grupo, String codGrupo)
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

            //Extraemos las plazas ocupadas actualmente y actualizamos
            if(resultado.next())
            {                
                grupo.setDia(resultado.getString("dia"));
                grupo.setHora(resultado.getString("hora"));
                grupo.setObservaciones(resultado.getString("observaciones"));
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer los datos del grupo en la Base de Datos: "+e.getMessage());
        }
    }

    public boolean grupoCompleto(String codGrupo)
    {
        try
        {
            boolean completo = false;

            int plazas,plazasOcupadas;

            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT * FROM grupos ";
                   query += "WHERE codigolab='"+codGrupo+"'";

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            //Extraemos las plazas ocupadas actualmente y actualizamos
            if(resultado.next())
            {
                plazas = resultado.getInt("plazas");
                plazasOcupadas = resultado.getInt("plazasocupadas");

                completo = (plazas - plazasOcupadas <= 0);
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return completo;

        }
        catch(SQLException e)
        {
            System.out.println("Error al extraer los datos del grupo en la Base de Datos: "+e.getMessage());
            return false;
        }
    }

     //Devuelve un vector con todas las asignaturas
    public Vector listaAsignaturasTotal(String nombre_titulacion)
    {
        try
        {            
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query =  "SELECT * FROM asignaturatotal ";
                   query += "WHERE tit_nombre='"+nombre_titulacion+"' AND activa = 's'";

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
    
    public boolean registrarAsignatura (asignaturasForm formulario)
    {
    	boolean valido = false;
    	try{	
	        SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
	        Date fechaInicio = formato1.parse(formulario.getFechaInicio());
	        Date fechaFin = formato1.parse(formulario.getFechaFin());
	        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	        //Preparamos la query
	        String query  = "INSERT INTO asignaturas ";
	               query += "(codigo,titulo,fechainicio,fechafin,responsable,email,telefono,titulacion) ";
	
	               query += "VALUES ('"+formulario.getCodigo()+"','"+formulario.getTitulo().toLowerCase()+"','"+
	            		   formato.format(fechaInicio)+"','"+formato.format(fechaFin)+"','"+formulario.getResponsable().toLowerCase()+"','"+
	                        formulario.getEmail().toLowerCase()+"','"+formulario.getTelefono()+"','"+formulario.getTitulacion()+"')";

        
            Connection conexion=bbdd.getConexion();
            Statement st=conexion.createStatement();

            //Se ejecuta la query
            st.execute(query);

            st.close();
            bbdd.cerrarConexion(conexion);
            
            valido = true;
            return valido;

        }
        catch(SQLException | ParseException e)
        {
            System.out.println("Error al inscribir al alumno en la Base de Datos: "+e.getMessage());
            return valido;
        }
    }
    
    public boolean modificarAsignatura (asignaturasForm formulario)
    {
    	 boolean valido = false;
    	try
        {
    		SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
	        Date fechaInicio = formato1.parse(formulario.getFechaInicio());
	        Date fechaFin = formato1.parse(formulario.getFechaFin());
	        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	        //Preparamos la query
	        String query  = "UPDATE asignaturas ";
	               query += "SET titulo= '"+formulario.getTitulo()+"', ";
	               query += "fechainicio = '"+formato.format(fechaInicio)+"', fechafin = '"+formato.format(fechaFin)+"', ";
	               query += "responsable = '"+formulario.getResponsable()+"', email = '"+formulario.getEmail().toLowerCase()+"', ";
	               query += "telefono = '"+formulario.getTelefono()+"' WHERE codigo = '"+formulario.getCodigo()+"'";

        
            Connection conexion=bbdd.getConexion();
            Statement st=conexion.createStatement();

            //Se ejecuta la query
            st.execute(query);

            st.close();
            bbdd.cerrarConexion(conexion);
            
            valido = true;
            return valido;

        }
        catch(SQLException | ParseException e)
        {
            System.out.println("Error al actualizar la asignatura: "+e.getMessage());
            return valido;
        }
    }
    
    public boolean borrarAsignatura(String codAsignatura)
    {
        boolean valido = false;

        //Se prepara la query
        String query  = "UPDATE asignaturas SET activa='n' ";
               query += "WHERE codigo='"+codAsignatura+"'";

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
    
    public boolean activarAsignatura(String codAsignatura)
    {
        boolean valido = false;

        //Se prepara la query
        String query  = "UPDATE asignaturas SET activa='s' ";
               query += "WHERE codigo='"+codAsignatura+"'";

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
            System.out.println("Error al activar la asignatura de la base de datos: "+e.getMessage());
            return valido;
        }
    }
}
