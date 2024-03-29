package Clases.Persona;
import java.io.Serializable;
import java.time.LocalDate;
public class Persona implements Serializable {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        protected String Nombre, Apellido, Dni, Nacionalidad;
        protected LocalDate Fecha_Nacimiento;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Persona(){}
        public Persona (String _Nombre, String _Apellido, String _Dni, String _Nacionalidad, LocalDate _Fecha_Nacimiento){
            Nombre = _Nombre;
            Apellido = _Apellido;
            Dni = _Dni;
            Nacionalidad = _Nacionalidad;
            Fecha_Nacimiento = _Fecha_Nacimiento;
        }
        public Persona (Persona Nueva){
            Nombre = Nueva.getNombre();
            Apellido = Nueva.getApellido();
            Dni = Nueva.getDni();
            Nacionalidad = Nueva.getNacionalidad();
            Fecha_Nacimiento = Nueva.getFecha_Nacimiento();
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public String getNombre() {
            return Nombre;
        }
        public String getApellido() {
            return Apellido;
        }
        public String getDni() {
            return Dni;
        }
        public String getNacionalidad() {
            return Nacionalidad;
        }
        public LocalDate getFecha_Nacimiento() {
            return Fecha_Nacimiento;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDE
        @Override
        public String toString() {
            return "Persona{" +
                    "Nombre='" + Nombre + '\'' +
                    ", Apellido='" + Apellido + '\'' +
                    ", Dni='" + Dni + '\'' +
                    ", Nacionalidad='" + Nacionalidad + '\'' +
                    ", Fecha_Nacimiento=" + Fecha_Nacimiento +
                    '}';
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}