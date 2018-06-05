/**
 *
 * @author cc
 */

package web.forms;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class registroForm extends ActionForm
{
    public String nombre;
    public String apellidos;
    public String email;
    public String dni;
    public String telefono;

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //Valida los errores del formulario
    @Override
    public ActionErrors validate (ActionMapping mapping, HttpServletRequest req)
    {
        ActionErrors errores = new ActionErrors();

        //Errores del campo dni
        if(this.dni==null || this.dni.equals(""))
        {
            errores.add("usuario.dni.vacio", new ActionMessage("error.dni"));
        }
        else if(dni.length() > 9)
        {
            errores.add("usuario.dni.extenso", new ActionMessage("error.dni.long"));
        }
     
        //Errores del campo nombre
        if(this.nombre==null || this.nombre.equals(""))
        {
            errores.add("nombre.registro.vacio", new ActionMessage("error.nombre"));
        }
        else if(nombre.length() > 20)
        {
            errores.add("nombre.registro.extenso", new ActionMessage("error.nombre.long"));
        }

        //Errores del campo apellidos
        if(this.apellidos==null || this.apellidos.equals(""))
        {
            errores.add("apellidos.registro.vacio", new ActionMessage("error.apellidos"));
        }
        else if(apellidos.length() > 40)
        {
            errores.add("apellidos.registro.extenso", new ActionMessage("error.apellidos.long"));
        }

        //Errores del campo email
        if(this.email==null || this.email.equals(""))
        {
            errores.add("email.registro.vacio", new ActionMessage("error.email"));
        }
        else if(email.length() > 100)
        {
            errores.add("email.registro.extenso", new ActionMessage("error.email.long"));
        }

        //Errores del campo teléfono
        if(telefono.length() > 9)
        {
            errores.add("telefono.registro.extenso", new ActionMessage("error.telefono.long"));
        }

        return errores;
    }

}
