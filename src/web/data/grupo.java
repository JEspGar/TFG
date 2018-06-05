/**
 *
 * @author cc
 */

package web.data;


public class grupo
{
    int codigoLab;
    String asigAsoc;
    String dia;
    String hora;
    int plazas;
    int plazasOcupadas;
    String observaciones;

    public String getAsigAsoc() {
        return asigAsoc;
    }

    public void setAsigAsoc(String asigAsoc) {
        this.asigAsoc = asigAsoc;
    }

    public int getCodigoLab() {
        return codigoLab;
    }

    public void setCodigoLab(int codigoLab) {
        this.codigoLab = codigoLab;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public int getPlazasOcupadas() {
        return plazasOcupadas;
    }

    public void setPlazasOcupadas(int plazasOcupadas) {
        this.plazasOcupadas = plazasOcupadas;
    }

}
