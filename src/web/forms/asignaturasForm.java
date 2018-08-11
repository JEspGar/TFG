/**
 *
 * @author cc
 */

package web.forms;

import org.apache.struts.action.*;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.*;

public class asignaturasForm extends ActionForm
{
	    private String codigo;
	    private String titulo;
	    private String fechaInicio;
	    private String fechaFin;
	    private String responsable;
	    private String email;
	    private String telefono;
	    private String titulacion;
	    private ArrayList titulacionesList;
	    private ArrayList asignaturasList;
	    private String method;

	    public String getCodigo() {
	        return codigo;
	    }

	    public void setCodigo(String codigo) {
	        this.codigo = codigo;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getFechaFin() {
	        return fechaFin;
	    }

	    public void setFechaFin(String fechaFin) {
	        this.fechaFin = fechaFin;
	    }

	    public String getFechaInicio() {
	        return fechaInicio;
	    }

	    public void setFechaInicio(String fechaInicio) {
	        this.fechaInicio = fechaInicio;
	    }

	    public String getResponsable() {
	        return responsable;
	    }

	    public void setResponsable(String responsable) {
	        this.responsable = responsable;
	    }

	    public String getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(String telefono) {
	        this.telefono = telefono;
	    }

	    public String getTitulo() {
	        return titulo;
	    }

	    public void setTitulo(String titulo) {
	        this.titulo = titulo;
	    }

	  public String getTitulacion() {
			return titulacion;
		}

		public void setTitulacion(String titulacion) {
			this.titulacion = titulacion;
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

		public ArrayList getAsignaturasList() {
			return asignaturasList;
		}

		public void setAsignaturasList(ArrayList asignaturasList) {
			this.asignaturasList = asignaturasList;
		}

		//Valida los errores del formulario
	    @Override
	    public ActionErrors validate (ActionMapping mapping, HttpServletRequest req)
	    {
	        ActionErrors errores = new ActionErrors();

	        //Errores del campo codigo
	        if(this.codigo==null || this.codigo.equals(""))
	        {
	            errores.add("asignatura.codigo.vacio", new ActionMessage("error.codigo"));
	        }
	        else if(codigo.length() > 15)
	        {
	            errores.add("asignatura.codigo.extenso", new ActionMessage("error.codigo.long"));
	        }
	     
	        //Errores del campo titulo
	        if(this.titulo==null || this.titulo.equals(""))
	        {
	            errores.add("asignatura.titulo.vacio", new ActionMessage("error.titulo"));
	        }
	        else if(titulo.length() > 50)
	        {
	            errores.add("asignatura.titulo.extenso", new ActionMessage("error.titulo.long"));
	        }

	        //Errores del campo titulacion
//	        if(this.titulacion==null || this.titulacion.equals(""))
//	        {
//	            errores.add("asignatura.titulacion.vacio", new ActionMessage("error.titulacion"));
//	        }
//	        else if(titulacion.length() > 50)
//	        {
//	            errores.add("asignatura.titulacion.extenso", new ActionMessage("error.titulacion.long"));
//	        }

	        //Errores del campo fechaInicio
	        if(this.fechaInicio==null || this.fechaInicio.equals(""))
	        {
	            errores.add("asignatura.fechaInicio.vacio", new ActionMessage("error.fechaInicio"));
	        }

	        //Errores del campo fechaFin
	        if(this.fechaFin==null || this.fechaFin.equals(""))
	        {
	            errores.add("asignatura.fechaFin.vacio", new ActionMessage("error.fechaFin"));
	        }
	        
	        //Errores del campo responsable
	        if(responsable.length() > 50)
	        {
	            errores.add("asignatura.responsable.extenso", new ActionMessage("error.responsable.long"));
	        }
	        
	        //Errores del campo email
	        if(email.length() > 50)
	        {
	            errores.add("asignatura.email.extenso", new ActionMessage("error.email.long"));
	        }
	        
	        //Errores del campo telefono
	        if(telefono.length() > 10)
	        {
	            errores.add("asignatura.telefono.extenso", new ActionMessage("error.telefono.long"));
	        }

	        return errores;
	    }
}
