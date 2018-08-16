/**
 *
 * @author cc
 */
package web.recursos;
//import web.data.inscrito;
import java.sql.*;
import web.forms.*;
import web.data.inscrito;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class gestionAlumnos
{
    BBDD bbdd; //base de datos sobre la que se conectará.

    public gestionAlumnos(String driver, String url, String usuario, String password)
    {
        bbdd = new BBDD(driver,url,usuario,password); //Creamos un objeto BBDD para las conexiones y desconexiones
    }

    //Comprueba en la Base de Datos si un alumno está matriculado en la asignatura
    public boolean alumnoInscrito(String dni, String asignatura)
    {
        boolean inscrito = false;

        try
        {
            //Se abre la conexion
            Connection conexion = this.bbdd.getConexion();

            //Formamos la query
            String query  = "SELECT dni FROM inscritos ";
                   query += "WHERE dni='"+dni+"' and asignatura='"+asignatura+"'";

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            inscrito = resultado.next();

            //Cerramos la conexion
            this.bbdd.cerrarConexion(conexion);

            return inscrito;

        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a la base de datos para verificar el usuario: "+e.getMessage());
            return inscrito;
        }
    }

    //Registra un nuevo alumno en un grupo en la Base de Datos
    public boolean registrarAlumno (registroForm formulario, String grupo, String asignatura, String carrera)
    {
        boolean valido = false;

        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        //Preparamos la query
        String query  = "INSERT INTO inscritos ";
               query += "(dni,nombre,apellidos,carrera,lab,asignatura,inscrito,email,telefono) ";

               query += "VALUES ('"+formulario.getDni()+"','"+formulario.getNombre().toLowerCase()+"','"+
                        formulario.getApellidos().toLowerCase()+"','"+carrera+"','"+grupo+"','"+
                        asignatura+"','"+formato.format(fechaActual)+"','"+formulario.getEmail()+"','"+
                        formulario.getTelefono()+"')";

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
            System.out.println("Error al inscribir al alumno en la Base de Datos: "+e.getMessage());
            return valido;
        }
    }

    //Registra un nuevo alumno en un grupo en la Base de Datos
    public Vector listaGrupos (String dni)
    {   
        try
        {
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT inscritos.*,grupos.dia, grupos.hora, asignaturas.titulo ";
                   query += "FROM inscritos, grupos, asignaturas ";
                   query += "WHERE inscritos.dni='"+dni+"' AND inscritos.lab=grupos.codigolab AND inscritos.asignatura=asignaturas.codigo";

            //Se crea un vector de categorias.
            Vector  vectorAlumnos = new Vector();

            //Se ejecuta la query 1
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);


            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            while(resultado.next())
            {
                inscrito alumno = new inscrito();

                alumno.setNombre(resultado.getString("nombre"));
                alumno.setApellidos(resultado.getString("apellidos"));
                alumno.setCarrera(resultado.getString("carrera"));
                alumno.setDia(resultado.getString("dia"));
                alumno.setHora(resultado.getString("hora"));
                alumno.setAsignatura(resultado.getString("titulo"));
                alumno.setInscrito(resultado.getTimestamp("inscrito"));

                //Se añade la categoria al vector de categorias
                vectorAlumnos.add(alumno);
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return vectorAlumnos;
        }
        catch(Exception e)
        {
            System.out.println("Error al acceder a las inscripciones de la Base de Datos: "+e.getMessage());
            return null;
        }
    }

    public Vector listaAlumnosGrupo(String codigoLab)
    {
         try
        {
            //Se obtiene una conexion
            Connection conexion = this.bbdd.getConexion();

            //Se prepara la query
            String query  = "SELECT inscritos.*,grupos.dia, grupos.hora, asignaturas.titulo ";
                   query += "FROM inscritos, grupos, asignaturas ";
                   query += "WHERE inscritos.lab='"+codigoLab+"' AND inscritos.lab=grupos.codigolab AND inscritos.asignatura=asignaturas.codigo";

            //Se crea un vector de categorias.
            Vector  vectorGrupos = new Vector();

            //Se ejecuta la query 1
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);


            //Para cada fila se creará un objeto y se rellenará
            //con los valores de las columnas.
            while(resultado.next())
            {
                inscrito alumno = new inscrito();

                alumno.setDni(resultado.getString("dni"));
                alumno.setNombre(resultado.getString("nombre"));
                alumno.setApellidos(resultado.getString("apellidos"));
                alumno.setCarrera(resultado.getString("carrera"));
                alumno.setDia(resultado.getString("dia"));
                alumno.setHora(resultado.getString("hora"));
                alumno.setAsignatura(resultado.getString("titulo"));
                alumno.setEmail(resultado.getString("email"));
                alumno.setInscrito(resultado.getTimestamp("inscrito"));

                //Se añade la categoria al vector de categorias
                vectorGrupos.add(alumno);
            }

            //Se cierra la conexión
            this.bbdd.cerrarConexion(conexion);

            return vectorGrupos;
        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a las inscripciones de la Base de Datos: "+e.getMessage());
            return null;
        }
        
    }
    
    public boolean borrarInscripcionesGrupo(String codGrupo)
    {
        boolean valido = false;

        //Se prepara la query
        String query  = "DELETE FROM inscritos ";
               query += "WHERE lab='"+codGrupo+"'";

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
            System.out.println("Error al borrar las inscripciones de la base de datos: "+e.getMessage());
            return valido;
        }
    }

    public String datosAlumnoInscrito(String dni, String asignatura)
    {
        String inscripcion = "";

        try
        {
            //Se abre la conexion
            Connection conexion = this.bbdd.getConexion();

            //Formamos la query
            String query  = "SELECT inscripcion, email FROM inscritos ";
                   query += "WHERE dni='"+dni+"' and asignatura='"+asignatura+"'";

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            if(resultado.next())
            {
	            inscripcion = resultado.getString("inscripcion");
	            inscripcion = inscripcion +"&"+resultado.getString("email");
            }
            //Cerramos la conexion
            this.bbdd.cerrarConexion(conexion);

            return inscripcion;

        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a la base de datos para verificar el usuario: "+e.getMessage());
            return inscripcion;
        }
    }
    
    public String grupoInscripcion(String inscripcion)
    {
        String grupo = "";

        try
        {
            //Se abre la conexion
            Connection conexion = this.bbdd.getConexion();

            //Formamos la query
            String query  = "SELECT lab FROM inscritos ";
                   query += "WHERE inscripcion='"+inscripcion+"'";

            //Se ejecuta la query
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(query);

            if(resultado.next())
            {
	            grupo = resultado.getString("lab");
            }
            //Cerramos la conexion
            this.bbdd.cerrarConexion(conexion);

            return grupo;

        }
        catch(SQLException e)
        {
            System.out.println("Error al acceder a la base de datos para obtener el grupo: "+e.getMessage());
            return inscripcion;
        }
    }
    
    public boolean modificarInscripcion(String codInscrip, String lab)
    {
        boolean valido = false;

        //Se prepara la query
        String query  = "UPDATE inscritos SET lab='"+lab+"' ";
               query += "WHERE inscripcion='"+codInscrip+"'";

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
            System.out.println("Error al modificar las inscripciones de la base de datos: "+e.getMessage());
            return valido;
        }
    }
}
