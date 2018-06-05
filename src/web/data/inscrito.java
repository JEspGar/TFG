/**
 *
 * @author cc
 */

package web.data;
import java.util.Date;

public class inscrito
{
    String dni;
    String nombre;
    String apellidos;
    String carrera;
    String dia;
    String hora;
    String asignatura;
    String email;
    Date inscrito;

    public String getDni(){
        return dni;
    }
    public void setDni(String dni){
        this.dni=dni;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }


    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Date getInscrito() {
        return inscrito;
    }

    public void setInscrito(Date inscrito) {
        this.inscrito = inscrito;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
