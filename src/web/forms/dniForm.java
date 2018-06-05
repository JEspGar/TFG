/**
 *
 * @author cc
 */

package web.forms;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class dniForm extends ActionForm
{
    public String dni;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

        return errores;
    }
}
