/**
 *
 * @author cc
 */

package web.recursos;

import java.sql.*;

public class BBDD
{

    String driver;   //driver de la base de datos.
    String url;      //Url de la base de datos.
    String usuario;  //usuario de la base de datos.
    String password; //contraseña de la base de datos.

    public BBDD (String driver,String url,String usuario,String password)
    {
        //Guardamos los datos para la conexion con la base de datos
        this.driver=driver;
        this.url=url;
        this.usuario=usuario;
        this.password=password;
    }


    //Devuelve una conexión a la base de datos.
    public Connection getConexion()
    {
        Connection conexion=null;

        try
        {
            Class.forName(this.driver).newInstance();
            conexion = DriverManager.getConnection(this.url,this.usuario,this.password);
            // System.out.println("La conexion se ha establecido bien!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return conexion;
    }


    //Cerramos la conexion con la Base de Datos
    public void cerrarConexion(Connection conexion)
    {
        try
        {
            if(conexion!=null && !conexion.isClosed())
            {
                conexion.close();
            }
        }
        catch(SQLException se)
        {
            System.err.println("Se ha producido un error al cerrar la Base de Datos");
            System.err.println(se.getMessage());
        }
    }

}
