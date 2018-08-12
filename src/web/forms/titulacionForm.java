/**
 *
 * @author cc
 */

package web.forms;

import org.apache.struts.action.*;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.*;

public class titulacionForm extends ActionForm
{
	    private String codigo;
	    private String titulo;
	    private ArrayList titulacionesList;
	    private String method;

	    public String getCodigo() {
	        return codigo;
	    }

	    public void setCodigo(String codigo) {
	        this.codigo = codigo;
	    }

	    

	    public String getTitulo() {
	        return titulo;
	    }

	    public void setTitulo(String titulo) {
	        this.titulo = titulo;
	    }

		public ArrayList getTitulacionesList() {
			return titulacionesList;
		}

		public void setTitulacionesList(ArrayList titulacionesList) {
			this.titulacionesList = titulacionesList;
		}

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		//Valida los errores del formulario
	    @Override
	    public ActionErrors validate (ActionMapping mapping, HttpServletRequest req)
	    {
	        ActionErrors errores = new ActionErrors();

	        //Errores del campo codigo
	        if(this.codigo==null || this.codigo.equals(""))
	        {
	            errores.add("titulacion.codigo.vacio", new ActionMessage("error.codigo"));
	        }
	        else if(codigo.length() > 5)
	        {
	            errores.add("titulacion.codigo.extenso", new ActionMessage("error.codigo.long"));
	        }
	     
	        //Errores del campo titulo
	        if(this.titulo==null || this.titulo.equals(""))
	        {
	            errores.add("titulacion.titulo.vacio", new ActionMessage("error.titulo"));
	        }
	        else if(titulo.length() > 50)
	        {
	            errores.add("titulacion.titulo.extenso", new ActionMessage("error.titulo.long"));
	        }

	        return errores;
	    }
}
