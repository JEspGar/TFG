/**
 *
 * @author cc
 */

package web.forms;

import org.apache.struts.action.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.*;

public class grupoForm extends ActionForm
{
		private String codigoLab;
	    private String asigAsoc;
	    private String dia;
	    private String hora;
	    private String plazas;
	    private String plazasOcupadas;
	    private String observaciones;
	    private String codigoTit;
	    private ArrayList titulacionesList;
	    private ArrayList asignaturasList;
	    private String method;
	    

		public String getCodigoLab() {
			return codigoLab;
		}

		public void setCodigoLab(String codigoLab) {
			this.codigoLab = codigoLab;
		}

		public String getAsigAsoc() {
			return asigAsoc;
		}

		public void setAsigAsoc(String asigAsoc) {
			this.asigAsoc = asigAsoc;
		}

		public String getDia() {
			return dia;
		}

		public void setDia(String dia) {
			this.dia = dia;
		}

		public String getHora() {
			return hora;
		}

		public void setHora(String hora) {
			this.hora = hora;
		}

		public String getPlazas() {
			return plazas;
		}

		public void setPlazas(String plazas) {
			this.plazas = plazas;
		}

		public String getPlazasOcupadas() {
			return plazasOcupadas;
		}

		public void setPlazasOcupadas(String plazasOcupadas) {
			this.plazasOcupadas = plazasOcupadas;
		}

		public String getObservaciones() {
			return observaciones;
		}

		public void setObservaciones(String observaciones) {
			this.observaciones = observaciones;
		}

		public String getCodigoTit() {
			return codigoTit;
		}

		public void setCodigoTit(String codigoTit) {
			this.codigoTit = codigoTit;
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

	        //Errores del campo plazas
	        if(this.plazas==null || this.plazas.equals(""))
	        {
	            errores.add("grupo.plazas.vacio", new ActionMessage("error.plazas"));
	        }
	        else if(!StringUtils.isNumeric(this.plazas))
	        {
	            errores.add("grupo.plazas.erroneo", new ActionMessage("error.plazas.numerico"));
	        }
	        
//	        //Errores del campo plazas ocupadas
//	        if(this.plazasOcupadas==null || this.plazasOcupadas.equals(""))
//	        {
//	            errores.add("grupo.plazasOcupadas.vacio", new ActionMessage("error.plazasOcupadas"));
//	        }
//	        else if(!StringUtils.isNumeric(this.plazasOcupadas))
//	        {
//	            errores.add("grupo.plazasOcupadas.erroneo", new ActionMessage("error.plazasOcupadas.numerico"));
//	        }
	     
	        //Errores del campo dia
	        if(this.dia==null || this.dia.equals(""))
	        {
	            errores.add("grupo.dia.vacio", new ActionMessage("error.dia"));
	        }
	        else if(dia.length() > 20)
	        {
	            errores.add("grupo.dia.extenso", new ActionMessage("error.dia.long"));
	        }	     
	        
	        //Errores del campo observaciones
	        if(observaciones.length() > 100)
	        {
	            errores.add("grupo.observaciones.extenso", new ActionMessage("error.observaciones.long"));
	        }

	        return errores;
	    }
}
