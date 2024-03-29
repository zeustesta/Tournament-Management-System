package Clases.Equipo;
import Clases.Persona.Tecnico_Entrenador;
public abstract class Equipo {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        public static int Contador_Equipos=1;
        protected int Id_Equipo;
        protected Tecnico_Entrenador Entrenador;
        protected int PartidosGanados, PartidosPerdidos;
        protected int TorneosGanados, TorneosPerdidos;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Equipo(){
            Id_Equipo = Contador_Equipos;
            Contador_Equipos++;
            Entrenador=null;
            PartidosGanados=0;
            PartidosPerdidos=0;
            TorneosGanados=0;
            TorneosPerdidos=0;
        }
        public Equipo(Tecnico_Entrenador _Tecnico_Entrenador){
            Id_Equipo = Contador_Equipos;
            Contador_Equipos++;
            Entrenador = _Tecnico_Entrenador;
            PartidosGanados=0;
            PartidosPerdidos=0;
            TorneosGanados=0;
            TorneosPerdidos=0;
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS AUMENTADORES
        public void AumentarPartidosGanados(){
            PartidosGanados++;
        }
        public void AumentarPartidosPerdidos(){
            PartidosPerdidos++;
        }
        public void AumentarTorneosGanados(){
            TorneosGanados++;
        }
        public void AumentarTorneosPerdidos(){
            TorneosPerdidos++;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // AGREGAR
        public void Agregar_Tecnico(Tecnico_Entrenador _Entrenador){
            Entrenador = _Entrenador;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public int getPartidosGanados() {
            return PartidosGanados;
        }
        public int getPartidosPerdidos() {
            return PartidosPerdidos;
        }
        public int getTorneosGanados() {
            return TorneosGanados;
        }
        public int getTorneosPerdidos() {
            return TorneosPerdidos;
        }
        public Tecnico_Entrenador getEntrenador() {
            return Entrenador;
        }
        public int getId_Equipo() {
            return Id_Equipo;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDE|
        @Override
        public String toString() {
            return "Equipo{" +
                    "Id_Equipo=" + Id_Equipo +
                    ", Entrenador=" + Entrenador +
                    ", PartidosGanados=" + PartidosGanados +
                    ", PartidosPerdidos=" + PartidosPerdidos +
                    ", TorneosGanados=" + TorneosGanados +
                    ", TorneosPerdidos=" + TorneosPerdidos +
                    '}';
        }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
