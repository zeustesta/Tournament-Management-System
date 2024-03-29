package Clases.Partido;
import Clases.Persona.Arbitro;
import java.util.Scanner;
public abstract class Partido {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        protected boolean Activo;
        public static int ContadorPartidos=1;
        protected int Id_Partido;
        protected Arbitro Arbitro;
        protected int Nro_Ronda;
        protected boolean Ganador;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Partido(int _Nro_Ronda, Arbitro _Arbitro){
            Id_Partido = ContadorPartidos;
            ContadorPartidos++;
            Arbitro = _Arbitro;
            Nro_Ronda = _Nro_Ronda;
            Activo = true;
            Ganador = true;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS
        public abstract void MostrarEstadisticas();
        public abstract void MenuPartido(Scanner Teclado);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public boolean isActivo() {
            return Activo;
        }
        public String EstaActivo(){
            String Retornar;
            if (Activo){
                Retornar ="En curso";
            }
            else{
                Retornar="Finalizado";
            }
            return Retornar;
        }
        public Arbitro getArbitro() {
            return Arbitro;
        }
        public int getNro_Ronda() {
            return Nro_Ronda;
        }
        public String getNro_RondaString(){
            String Retornar = "";
            if (Nro_Ronda==0){
                Retornar += "Octavos";
            }
            else if (Nro_Ronda==1){
                Retornar += "Cuartos";
            }
            else if (Nro_Ronda==2){
                Retornar += "Semis";
            }
            else if (Nro_Ronda==3){
                Retornar += "Final";
            }
            return Retornar;
        }
        public void setGanador(boolean ganador) {
            Ganador = ganador;
        }
        public boolean isGanador() {
            return Ganador;
        }
        public int getId_Partido() {
            return Id_Partido;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDE
        @Override
        public String toString() {
            return "Partido{" +
                    "Activo=" + Activo +
                    ", Id_Partido=" + Id_Partido +
                    ", Arbitro=" + Arbitro +
                    ", Nro_Ronda=" + Nro_Ronda +
                    ", Ganador=" + Ganador +
                    '}';
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}