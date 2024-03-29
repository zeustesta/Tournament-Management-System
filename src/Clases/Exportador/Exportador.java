package Clases.Exportador;
import Clases.Equipo.Equipo_Futbol;
import Clases.Equipo.Equipo_PingPong;
import Clases.Equipo.Equipo_Tennis;
import Clases.Generica.ListaDeDatos;
import Clases.Partido.Partido_Futbol;
import Clases.Partido.Partido_PingPong;
import Clases.Partido.Partido_Tennis;
import Clases.Persona.*;
import Clases.Torneo.Torneo_Futbol;
import Clases.Torneo.Torneo_PingPong;
import Clases.Torneo.Torneo_Tennis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Exportador {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS
        public static void ExportarArbitros(String Path, String Name, ListaDeDatos<Arbitro> Arbitros){
            try{
                BufferedWriter BufferSalida = new BufferedWriter(new FileWriter(new File(Path, Name+".txt")));
                BufferSalida.write(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                BufferSalida.write("\n |Documento Nacional de Identidad|-|-------Nombre-------|-|------Apellido------|-|----Nacionalidad----|-|Fecha de nacimiento|-|Partidos Arbitrados|-|Torneos Arbitrados|");
                BufferSalida.write("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for (Arbitro Aux : Arbitros){

                    BufferSalida.write("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    BufferSalida.write("\n |");

                    for (int i=0 ; i<(31-Aux.getDni().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getDni()+"|-|");

                    for (int i=0 ; i<(20-Aux.getNombre().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getNombre()+"|-|");

                    for (int i=0 ; i<(20-Aux.getApellido().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getApellido()+"|-|");

                    for (int i=0 ; i<(20-Aux.getNacionalidad().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getNacionalidad()+"|-|");

                    for (int i=0 ; i<(19-Aux.getFecha_Nacimiento().toString().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getFecha_Nacimiento().toString()+"|-|");

                    String TorneosYPartidosArbitrados = String.valueOf(Aux.getPartidosArbitrados());
                    for (int i=0 ; i<(19-TorneosYPartidosArbitrados.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(TorneosYPartidosArbitrados+"|-|");

                    TorneosYPartidosArbitrados = String.valueOf(Aux.getTorneosArbitrados());
                    for (int i=0 ; i<(18-TorneosYPartidosArbitrados.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(TorneosYPartidosArbitrados+"|");

                    BufferSalida.write("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                }

                BufferSalida.write("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                BufferSalida.close();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public static void ExportarEquiposFutbol(String Path, String Name, ArrayList<Equipo_Futbol> Equipos_Futbol){
            try{
                BufferedWriter BufferSalida = new BufferedWriter(new FileWriter(new File(Path, Name+".txt")));
                String AuxiliarConversion;
                BufferSalida.write(    " -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                BufferSalida.write(  "\n |Id de Equipo|-|-Rol en el Equipo-|-|Documento Nacional de Identidad|-|-------Nombre-------|-|------Apellido------|-|----Nacionalidad----|-|Fecha de nacimiento|-|Goles Hechos|-|------Torneos------|-|------Partidos------|-|------Faltas------|");
                BufferSalida.write(  "\n |            |-|                  |-|                               |-|                    |-|                    |-|                    |-|                   |-|            |-|Ganados|--|Perdidos|-|Ganados|---|Perdidos|-|Amarillas|--|Rojas|");
                BufferSalida.write(  "\n -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for (Equipo_Futbol Aux : Equipos_Futbol){
                    BufferSalida.write("\n -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    // ENTRENADOR
                    BufferSalida.write("\n |");

                    // ID EQUIPO
                    AuxiliarConversion = String.valueOf(Aux.getId_Equipo());
                    for (int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|-|");

                    // ROL EN EL EQUIPO
                    BufferSalida.write("Tecnico Entrenador|-|");

                    // DNI
                    for (int i=0 ; i<(31-Aux.getEntrenador().getDni().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getDni()+"|-|");

                    // NOMBRE
                    for (int i=0 ; i<(20-Aux.getEntrenador().getNombre().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getNombre()+"|-|");

                    // APELLIDO
                    for (int i=0 ; i<(20-Aux.getEntrenador().getApellido().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getApellido()+"|-|");

                    // NACIONALIDAD
                    for (int i=0 ; i<(20-Aux.getEntrenador().getNacionalidad().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getNacionalidad()+"|-|");

                    // FECHA DE NACIMIENTO
                    for (int i=0 ; i<(19-Aux.getEntrenador().getFecha_Nacimiento().toString().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getFecha_Nacimiento().toString()+"|-");

                    // GOLES HECHOS, TORNEOS GANADOS, PERDIDOS, PARTIDOS GANADOS, PERDIDOS
                    BufferSalida.write("|------------|-|-------|--|--------|-|-------|---|--------|");
                    BufferSalida.write("-|---------|--|-----|");


                    BufferSalida.write("\n -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    // JUGADORES
                    for(Jugador_Futbol JugadorAux : Aux.getJugadores()){
                        // ENTRENADOR
                        BufferSalida.write("\n |");

                        // ID EQUIPO
                        AuxiliarConversion = String.valueOf(Aux.getId_Equipo());
                        for (int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(AuxiliarConversion+"|-|");

                        // ROL EN EL EQUIPO
                        for (int i=0 ; i<(18-JugadorAux.getPosicion_Juego().length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(JugadorAux.getPosicion_Juego()+"|-|");

                        // DNI
                        for (int i=0 ; i<(31-JugadorAux.getDni().length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(JugadorAux.getDni()+"|-|");

                        // NOMBRE
                        for (int i=0 ; i<(20-JugadorAux.getNombre().length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(JugadorAux.getNombre()+"|-|");

                        // APELLIDO
                        for (int i=0 ; i<(20-JugadorAux.getApellido().length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(JugadorAux.getApellido()+"|-|");

                        // NACIONALIDAD
                        for (int i=0 ; i<(20-JugadorAux.getNacionalidad().length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(JugadorAux.getNacionalidad()+"|-|");

                        // FECHA DE NACIMIENTO
                        for (int i=0 ; i<(19-JugadorAux.getFecha_Nacimiento().toString().length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(JugadorAux.getFecha_Nacimiento().toString()+"|-|");

                        // GOLES HECHOS, TORNEOS GANADOS, PERDIDOS, PARTIDOS GANADOS, PERDIDOS

                        // GOLES HECHOS
                        AuxiliarConversion = String.valueOf(JugadorAux.getPuntoGanados_Goles());
                        for (int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(AuxiliarConversion+"|-|");

                        // TORNEOS GANADOS Y PERDIDOS
                        AuxiliarConversion = String.valueOf(JugadorAux.getTorneosGanados());
                        for (int i=0 ; i<(7-AuxiliarConversion.length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(AuxiliarConversion+"|--|");
                        AuxiliarConversion = String.valueOf(JugadorAux.getTorneosPerdidos());
                        for (int i=0 ; i<(8-AuxiliarConversion.length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(AuxiliarConversion+"|-|");

                        // PARTIDOS GANADOS Y PERDIDOS
                        AuxiliarConversion = String.valueOf(JugadorAux.getPartidosGanados());
                        for (int i=0 ; i<(7-AuxiliarConversion.length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(AuxiliarConversion+"|---|");
                        AuxiliarConversion = String.valueOf(JugadorAux.getPartidosPerdidos());
                        for (int i=0 ; i<(8-AuxiliarConversion.length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(AuxiliarConversion+"|-|");

                        // AMARILLAS Y ROJAS
                        AuxiliarConversion = String.valueOf(JugadorAux.getAmarillasRecibidas());
                        for (int i=0 ; i<(9-AuxiliarConversion.length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(AuxiliarConversion+"|--|");
                        AuxiliarConversion = String.valueOf(JugadorAux.getRojasRecibidas());
                        for (int i=0 ; i<(5-AuxiliarConversion.length()) ; i++){
                            BufferSalida.write(" ");
                        }
                        BufferSalida.write(AuxiliarConversion+"|");

                    }
                    BufferSalida.write("\n -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
                BufferSalida.write("\n -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                BufferSalida.close();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public static void ExportarEquiposTennis(String Path, String Name, ArrayList<Equipo_Tennis> Equipos_Tennis){
            try{
                BufferedWriter BufferSalida = new BufferedWriter(new FileWriter(new File(Path, Name+".txt")));
                String AuxiliarConversion;
                BufferSalida.write(    " ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                BufferSalida.write(  "\n |Id de Equipo|-|-Rol en el Equipo-|-|Documento Nacional de Identidad|-|-------Nombre-------|-|------Apellido------|-|----Nacionalidad----|-|Fecha de nacimiento|-|------Torneos------|-|------Partidos------|-|-------Sets-------|-|-------Games-------|-|------Puntos------|-|-------Aces-------|-|Dobles Faltas|");
                BufferSalida.write(  "\n |            |-|                  |-|                               |-|                    |-|                    |-|                    |-|                   |-|Ganados|--|Perdidos|-|Ganados|---|Perdidos|-|Ganados|-|Perdidos|-|Ganados|--|Perdidos|-|Ganados|-|Perdidos|-|Hechos|-|Recibidos|-|             |");
                BufferSalida.write(  "\n  ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for (Equipo_Tennis Aux : Equipos_Tennis){

                    BufferSalida.write("\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    // ENTRENADOR
                    BufferSalida.write("\n |");

                    // ID EQUIPO
                    AuxiliarConversion = String.valueOf(Aux.getId_Equipo());
                    for (int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|-|");

                    // ROL EN EL EQUIPO
                    BufferSalida.write("Tecnico Entrenador|-|");

                    // DNI
                    for (int i=0 ; i<(31-Aux.getEntrenador().getDni().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getDni()+"|-|");

                    // NOMBRE
                    for (int i=0 ; i<(20-Aux.getEntrenador().getNombre().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getNombre()+"|-|");

                    // APELLIDO
                    for (int i=0 ; i<(20-Aux.getEntrenador().getApellido().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getApellido()+"|-|");

                    // NACIONALIDAD
                    for (int i=0 ; i<(20-Aux.getEntrenador().getNacionalidad().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getNacionalidad()+"|-|");

                    // FECHA DE NACIMIENTO
                    for (int i=0 ; i<(19-Aux.getEntrenador().getFecha_Nacimiento().toString().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getFecha_Nacimiento().toString()+"|-");

                    // PUNTOS HECHOS, TORNEOS GANADOS, PERDIDOS, PARTIDOS GANADOS, PERDIDOS
                    //BufferSalida.write("|       |--|        |-|       |---|        |-|       |-|        |-|       |--|        |-|       |-|        |-|      |-|         |-|             |");
                    BufferSalida.write("|-------|--|--------|-|-------|---|--------|-|-------|-|--------|-|-------|--|--------|-|-------|-|--------|-|------|-|---------|-|-------------|");


                    // JUGADOR
                    BufferSalida.write("\n |");

                    // ID EQUIPO
                    AuxiliarConversion = String.valueOf(Aux.getId_Equipo());
                    for (int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|-|");

                    // ROL EN EL EQUIPO
                    BufferSalida.write("           Jugador|-|");

                    // DNI
                    for (int i=0 ; i<(31-Aux.getJugador().getDni().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getJugador().getDni()+"|-|");

                    // NOMBRE
                    for (int i=0 ; i<(20-Aux.getJugador().getNombre().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getJugador().getNombre()+"|-|");

                    // APELLIDO
                    for (int i=0 ; i<(20-Aux.getJugador().getApellido().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getJugador().getApellido()+"|-|");

                    // NACIONALIDAD
                    for (int i=0 ; i<(20-Aux.getJugador().getNacionalidad().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getJugador().getNacionalidad()+"|-|");

                    // FECHA DE NACIMIENTO
                    for (int i=0 ; i<(19-Aux.getJugador().getFecha_Nacimiento().toString().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getJugador().getFecha_Nacimiento().toString()+"|-|");

                    // TORNEOS GANADOS
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getTorneosGanados());
                    for (int i=0 ; i<(7-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|--|");
                    // TORNEOS PERDIDOS
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getTorneosPerdidos());
                    for (int i=0 ; i<(8-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|-|");

                    // PARTIDOS GANADOS
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getPartidosGanados());
                    for (int i=0 ; i<(7-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|---|");
                    // PARTIDOS PERDIDOS
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getPartidosPerdidos());
                    for (int i=0 ; i<(8-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|-|");

                    // SETS GANADOS
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getSetsGanados());
                    for (int i=0 ; i<(7-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|-|");
                    // SETS PERDIDOS
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getSetsPerdidos());
                    for (int i=0 ; i<(8-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|-|");

                    // GAMES GANADOS
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getGamesGanados());
                    for (int i=0 ; i<(7-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|--|");
                    // GAMES PERDIDOS
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getGamesPerdidos());
                    for (int i=0 ; i<(8-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|-|");

                    // PUNTOS GANADOS
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getPuntoGanados_Goles());
                    for (int i=0 ; i<(7-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|-|");
                    // PUNTOS PERDIDOS
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getPuntosPerdidos());
                    for (int i=0 ; i<(8-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|-|");

                    // ACES HECHOS
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getAces());
                    for (int i=0 ; i<(6-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|-|");

                    // ACES RECIBIDOS
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getSaqueNoRecibido());
                    for (int i=0 ; i<(9-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|-|");

                    // DOBLES FALTAS
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getDobleFalta());
                    for (int i=0 ; i<(13-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");

                    BufferSalida.write("\n  ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                }

                BufferSalida.write("\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                BufferSalida.close();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public static void ExportarEquiposPingPong(String Path, String Name, ArrayList<Equipo_PingPong> Equipos_PingPong){
            try{
                BufferedWriter BufferSalida = new BufferedWriter(new FileWriter(new File(Path, Name+".txt")));
                String AuxiliarConversion;
                BufferSalida.write(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                BufferSalida.write("\n |Id de Equipo|-|-Rol en el Equipo-|-|Documento Nacional de Identidad|-|-------Nombre-------|-|------Apellido------|-|----Nacionalidad----|-|Fecha de nacimiento|-|------Torneos------|-|------Partidos------|-|-------Sets-------|-|------Puntos------|-|-------Aces-------|-|Mal Saque|");
                BufferSalida.write("\n |            |-|                  |-|                               |-|                    |-|                    |-|                    |-|                   |-|Ganados|--|Perdidos|-|Ganados|---|Perdidos|-|Ganados|-|Perdidos|-|Ganados|-|Perdidos|-|Hechos|-|Recibidos|-|         |");
                BufferSalida.write(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                for (Equipo_PingPong Aux : Equipos_PingPong){
                    // ENTRENADOR
                    BufferSalida.write("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    // ID EQUIPO
                    BufferSalida.write("\n |");
                    AuxiliarConversion = String.valueOf(Aux.getId_Equipo());
                    for (int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");

                    // ROL EN EL EQUIPO
                    BufferSalida.write("-|");
                    BufferSalida.write("Tecnico Entrenador|");

                    // DNI
                    BufferSalida.write("-|");
                    for (int i=0 ; i<(31-Aux.getEntrenador().getDni().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getDni()+"|");

                    // NOMBRE
                    BufferSalida.write("-|");
                    for (int i=0 ; i<(20-Aux.getEntrenador().getNombre().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getNombre()+"|");

                    // APELLIDO
                    BufferSalida.write("-|");
                    for (int i=0 ; i<(20-Aux.getEntrenador().getApellido().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getApellido()+"|");

                    // NACIONALIDAD
                    BufferSalida.write("-|");
                    for (int i=0 ; i<(20-Aux.getEntrenador().getNacionalidad().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getNacionalidad()+"|");

                    // FECHA DE NACIMIENTO
                    BufferSalida.write("-|");
                    for (int i=0 ; i<(19-Aux.getEntrenador().getFecha_Nacimiento().toString().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getEntrenador().getFecha_Nacimiento().toString()+"|");

                    // RESTO DE DATOS
                    BufferSalida.write("-|-------|--|--------|-|-------|---|--------|-|-------|-|--------|-|-------|-|--------|-|------|-|---------|-|---------|");

                    // JUGADOR

                    // ID EQUIPO
                    BufferSalida.write("\n |");
                    AuxiliarConversion = String.valueOf(Aux.getId_Equipo());
                    for (int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");

                    // ROL EN EL EQUIPO
                    BufferSalida.write("-|");
                    BufferSalida.write("           Jugador|");

                    // DNI
                    BufferSalida.write("-|");
                    for (int i=0 ; i<(31-Aux.getJugador().getDni().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getJugador().getDni()+"|");

                    // NOMBRE
                    BufferSalida.write("-|");
                    for (int i=0 ; i<(20-Aux.getJugador().getNombre().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getJugador().getNombre()+"|");

                    // APELLIDO
                    BufferSalida.write("-|");
                    for (int i=0 ; i<(20-Aux.getJugador().getApellido().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getJugador().getApellido()+"|");

                    // NACIONALIDAD
                    BufferSalida.write("-|");
                    for (int i=0 ; i<(20-Aux.getJugador().getNacionalidad().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getJugador().getNacionalidad()+"|");

                    // FECHA DE NACIMIENTO
                    BufferSalida.write("-|");
                    for (int i=0 ; i<(19-Aux.getJugador().getFecha_Nacimiento().toString().length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(Aux.getJugador().getFecha_Nacimiento().toString()+"|");

                    // TORNEOS GANADOS
                    BufferSalida.write("-|");
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getTorneosGanados());
                    for (int i=0 ; i<(7-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");
                    // TORNEOS PERDIDOS
                    BufferSalida.write("--|");
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getTorneosPerdidos());
                    for (int i=0 ; i<(8-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");

                    // PARTIDOS GANADOS
                    BufferSalida.write("-|");
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getPartidosGanados());
                    for (int i=0 ; i<(7-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");
                    // PARTIDOS PERDIDOS
                    BufferSalida.write("---|");
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getPartidosPerdidos());
                    for (int i=0 ; i<(8-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");

                    // SETS GANADOS
                    BufferSalida.write("-|");
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getSetsGanados());
                    for (int i=0 ; i<(7-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");
                    // SETS PERDIDOS
                    BufferSalida.write("-|");
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getSetsPerdidos());
                    for (int i=0 ; i<(8-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");

                    // PUNTOS GANADOS
                    BufferSalida.write("-|");
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getPuntoGanados_Goles());
                    for (int i=0 ; i<(7-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");
                    // PUNTOS PERDIDOS
                    BufferSalida.write("-|");
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getPuntosPerdidos());
                    for (int i=0 ; i<(8-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");

                    // ACES HECHOS
                    BufferSalida.write("-|");
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getAces());
                    for (int i=0 ; i<(6-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");
                    // ACES RECIBIDOS
                    BufferSalida.write("-|");
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getSaqueNoRecibido());
                    for (int i=0 ; i<(9-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");

                    // DOBLES FALTAS
                    BufferSalida.write("-|");
                    AuxiliarConversion = String.valueOf(Aux.getJugador().getSaqueErrado());
                    for (int i=0 ; i<(9-AuxiliarConversion.length()) ; i++){
                        BufferSalida.write(" ");
                    }
                    BufferSalida.write(AuxiliarConversion+"|");

                    BufferSalida.write("\n  -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                }

                BufferSalida.write("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                BufferSalida.close();

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public static void ExportarTorneosFutbol(String Path, String Name, ArrayList<Torneo_Futbol> Torneos_Futbol){
            try{
                BufferedWriter BufferSalida = new BufferedWriter(new FileWriter(new File(Path, Name+".txt")));
                String AuxiliarConversion;
                BufferSalida.write(" ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                BufferSalida.write("\n |Id de Torneo|-|Nombre del Torneo|-|--Activo--|-|Id del Partido|-|--Activo--|-|Nro de Ronda|-|--------------------Arbitro--------------------|-|----Ganador----|-|----------------------Equipo 1----------------------|-|----------------------Equipo 2----------------------|");
                BufferSalida.write("\n |            |-|                 |-|          |-|              |-|          |-|            |-|----Dni----|-|----Nombre----|-|----Apellido----|-|               |-|----Nombre----|-|Goles|-|Amarillas|-|Rojas|-|Penales|-|----Nombre----|-|Goles|-|Amarillas|-|Rojas|-|Penales|");
                BufferSalida.write("\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for (Torneo_Futbol TorneoAux : Torneos_Futbol){
                    BufferSalida.write("\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    for (Partido_Futbol PartidoAux : TorneoAux.getPartidos()){
                        if (PartidoAux.EstaElPartidoCompleto()){
                            // ID TORNEO
                            AuxiliarConversion = String.valueOf(TorneoAux.getId());
                            BufferSalida.write("\n |");
                            for(int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // NOMBRE TORNEO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(17-TorneoAux.getNombre().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(TorneoAux.getNombre()+"|");

                            // EN CURSO O FINALIZADO TORNEO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(10-TorneoAux.EstaActivo().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(TorneoAux.EstaActivo()+"|");

                            // ID PARTIDO
                            AuxiliarConversion = String.valueOf(PartidoAux.getId_Partido());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // EN CURSO O FINALIZADO PARTIDO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(10-PartidoAux.EstaActivo().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.EstaActivo()+"|");

                            // NRO DE RONDA
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(12-PartidoAux.getNro_RondaString().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getNro_RondaString()+"|");

                            // DNI ARBITRO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(11-PartidoAux.getArbitro().getDni().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getArbitro().getDni()+"|");

                            // NOMBRE ARBITRO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-PartidoAux.getArbitro().getNombre().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getArbitro().getNombre()+"|");

                            // APELLIDO ARBITRO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(16-PartidoAux.getArbitro().getApellido().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getArbitro().getApellido()+"|");

                            // NOMBRE DEL GANADOR
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(15-PartidoAux.GanadorString().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.GanadorString()+"|");

                            // EQUIPO 1
                            // NOMBRE
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-PartidoAux.getEquipo_1().getNombreEquipo().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getEquipo_1().getNombreEquipo()+"|");
                            // GOLES
                            AuxiliarConversion = String.valueOf(PartidoAux.getGoles_E1());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(5-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");
                            // AMARILLAS
                            AuxiliarConversion = String.valueOf(PartidoAux.getAmarillas_E1());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(9-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");
                            // ROJAS
                            AuxiliarConversion = String.valueOf(PartidoAux.getRojas_E1());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(5-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");
                            // PENALES
                            AuxiliarConversion = String.valueOf(PartidoAux.getPenales_E1());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(7-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // EQUIPO 2
                            // NOMBRE
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-PartidoAux.getEquipo_2().getNombreEquipo().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getEquipo_2().getNombreEquipo()+"|");
                            // GOLES
                            AuxiliarConversion = String.valueOf(PartidoAux.getGoles_E2());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(5-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");
                            // AMARILLAS
                            AuxiliarConversion = String.valueOf(PartidoAux.getAmarillas_E2());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(9-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");
                            // ROJAS
                            AuxiliarConversion = String.valueOf(PartidoAux.getRojas_E2());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(5-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");
                            // PENALES
                            AuxiliarConversion = String.valueOf(PartidoAux.getPenales_E2());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(7-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");
                        }
                    }
                    BufferSalida.write("\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }

                BufferSalida.write("\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                BufferSalida.close();

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public static void ExportarTorneosTennis(String Path, String Name, ArrayList<Torneo_Tennis> Torneos_Tennis){
            try{
                BufferedWriter BufferSalida = new BufferedWriter(new FileWriter(new File(Path, Name+".txt")));
                String AuxiliarConversion;
                BufferSalida.write(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                BufferSalida.write("\n |Id de Torneo|-|Nombre del Torneo|-|--Activo--|-|Id del Partido|-|--Activo--|-|Nro de Ronda|-|--------------------Arbitro--------------------|-|---------Ganador---------|-|----------------------------------------------Jugador 01----------------------------------------------|-|----------------------------------------------Jugador 02----------------------------------------------|");
                BufferSalida.write("\n |            |-|                 |-|          |-|              |-|          |-|            |-|----Dni----|-|----Nombre----|-|----Apellido----|-|                         |-|----Nombre----|-|----Apellido----|-|Sets Ganados|-|Games Ganados|-|Puntos Ganados|-|Aces|-|Doble Falta|-|----Nombre----|-|----Apellido----|-|Sets Ganados|-|Games Ganados|-|Puntos Ganados|-|Aces|-|Doble Falta|");
                BufferSalida.write("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                for (Torneo_Tennis TorneoAux : Torneos_Tennis){
                    BufferSalida.write("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    for (Partido_Tennis PartidoAux : TorneoAux.getPartidos()){
                        if (PartidoAux.EstaElPartidoCompleto()){
                            // ID TORNEO
                            AuxiliarConversion = String.valueOf(TorneoAux.getId());
                            BufferSalida.write("\n |");
                            for(int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // NOMBRE TORNEO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(17-TorneoAux.getNombre().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(TorneoAux.getNombre()+"|");

                            // EN CURSO O FINALIZADO TORNEO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(10-TorneoAux.EstaActivo().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(TorneoAux.EstaActivo()+"|");

                            // ID PARTIDO
                            AuxiliarConversion = String.valueOf(PartidoAux.getId_Partido());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // EN CURSO O FINALIZADO PARTIDO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(10-PartidoAux.EstaActivo().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.EstaActivo()+"|");

                            // NRO DE RONDA
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(12-PartidoAux.getNro_RondaString().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getNro_RondaString()+"|");

                            // DNI ARBITRO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(11-PartidoAux.getArbitro().getDni().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getArbitro().getDni()+"|");

                            // NOMBRE ARBITRO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-PartidoAux.getArbitro().getNombre().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getArbitro().getNombre()+"|");

                            // APELLIDO ARBITRO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(16-PartidoAux.getArbitro().getApellido().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getArbitro().getApellido()+"|");

                            // NOMBRE DEL GANADOR
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(25-PartidoAux.GanadorString().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.GanadorString()+"|");

                            // EQUIPO 1
                            // NOMBRE
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-PartidoAux.getEquipo_1().getJugador().getNombre().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getEquipo_1().getJugador().getNombre()+"|");

                            // APELLIDO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(16-PartidoAux.getEquipo_1().getJugador().getApellido().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getEquipo_1().getJugador().getApellido()+"|");

                            // SETS GANADOS
                            AuxiliarConversion = String.valueOf(PartidoAux.getSetsGanados_E1());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // GAMES GANADOS
                            AuxiliarConversion = String.valueOf(PartidoAux.getGamesGanados_E1());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(13-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // PUNTOS GANADO
                            AuxiliarConversion = String.valueOf(PartidoAux.getPuntos_E1());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // ACES
                            AuxiliarConversion = String.valueOf(PartidoAux.getAces_E1());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(4-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // DOBLE FALTA
                            AuxiliarConversion = String.valueOf(PartidoAux.getDobleFalta_E1());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(11-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");



                            // EQUIPO 2
                            // NOMBRE
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-PartidoAux.getEquipo_2().getJugador().getNombre().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getEquipo_2().getJugador().getNombre()+"|");

                            // APELLIDO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(16-PartidoAux.getEquipo_2().getJugador().getApellido().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getEquipo_2().getJugador().getApellido()+"|");

                            // SETS GANADOS
                            AuxiliarConversion = String.valueOf(PartidoAux.getSetsGanados_E2());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // GAMES GANADOS
                            AuxiliarConversion = String.valueOf(PartidoAux.getGamesGanados_E2());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(13-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // PUNTOS GANADO
                            AuxiliarConversion = String.valueOf(PartidoAux.getPuntos_E2());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // ACES
                            AuxiliarConversion = String.valueOf(PartidoAux.getAces_E2());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(4-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // DOBLE FALTA
                            AuxiliarConversion = String.valueOf(PartidoAux.getDobleFalta_E2());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(11-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");
                        }
                    }
                    BufferSalida.write("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }

                BufferSalida.write("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                BufferSalida.close();

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public static void ExportarTorneosPingPong(String Path, String Name, ArrayList<Torneo_PingPong> Torneos_PingPong){
            try{
                BufferedWriter BufferSalida = new BufferedWriter(new FileWriter(new File(Path, Name+".txt")));
                String AuxiliarConversion;
                BufferSalida.write(" --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                BufferSalida.write("\n |Id de Torneo|-|Nombre del Torneo|-|--Activo--|-|Id del Partido|-|--Activo--|-|Nro de Ronda|-|--------------------Arbitro--------------------|-|---------Ganador---------|-|-------------------------------------Jugador 01-------------------------------------|-|-------------------------------------Jugador 02-------------------------------------|");
                BufferSalida.write("\n |            |-|                 |-|          |-|              |-|          |-|            |-|----Dni----|-|----Nombre----|-|----Apellido----|-|                         |-|----Nombre----|-|----Apellido----|-|Sets Ganados|-|Puntos Ganados|-|Aces|-|Mal saque|-|----Nombre----|-|----Apellido----|-|Sets Ganados|-|Puntos Ganados|-|Aces|-|Mal saque|");
                BufferSalida.write("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                for (Torneo_PingPong TorneoAux : Torneos_PingPong){
                    BufferSalida.write("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    for (Partido_PingPong PartidoAux : TorneoAux.getPartidos()){
                        if (PartidoAux.EstaElPartidoCompleto()){
                            // ID TORNEO
                            AuxiliarConversion = String.valueOf(TorneoAux.getId());
                            BufferSalida.write("\n |");
                            for(int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // NOMBRE TORNEO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(17-TorneoAux.getNombre().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(TorneoAux.getNombre()+"|");

                            // EN CURSO O FINALIZADO TORNEO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(10-TorneoAux.EstaActivo().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(TorneoAux.EstaActivo()+"|");

                            // ID PARTIDO
                            AuxiliarConversion = String.valueOf(PartidoAux.getId_Partido());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // EN CURSO O FINALIZADO PARTIDO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(10-PartidoAux.EstaActivo().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.EstaActivo()+"|");

                            // NRO DE RONDA
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(12-PartidoAux.getNro_RondaString().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getNro_RondaString()+"|");

                            // DNI ARBITRO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(11-PartidoAux.getArbitro().getDni().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getArbitro().getDni()+"|");

                            // NOMBRE ARBITRO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-PartidoAux.getArbitro().getNombre().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getArbitro().getNombre()+"|");

                            // APELLIDO ARBITRO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(16-PartidoAux.getArbitro().getApellido().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getArbitro().getApellido()+"|");

                            // NOMBRE DEL GANADOR
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(25-PartidoAux.GanadorString().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.GanadorString()+"|");

                            // EQUIPO 1
                            // NOMBRE
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-PartidoAux.getEquipo_1().getJugador().getNombre().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getEquipo_1().getJugador().getNombre()+"|");

                            // APELLIDO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(16-PartidoAux.getEquipo_1().getJugador().getApellido().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getEquipo_1().getJugador().getApellido()+"|");

                            // SETS GANADOS
                            AuxiliarConversion = String.valueOf(PartidoAux.getSetsGanados_E1());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // PUNTOS GANADO
                            AuxiliarConversion = String.valueOf(PartidoAux.getPuntosGanados_E1());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // ACES
                            AuxiliarConversion = String.valueOf(PartidoAux.getAces_E1());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(4-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // MAL SAQUE
                            AuxiliarConversion = String.valueOf(PartidoAux.getSaquesErrados_E1());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(9-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // EQUIPO 2
                            // NOMBRE
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-PartidoAux.getEquipo_2().getJugador().getNombre().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getEquipo_2().getJugador().getNombre()+"|");

                            // APELLIDO
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(16-PartidoAux.getEquipo_2().getJugador().getApellido().length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(PartidoAux.getEquipo_2().getJugador().getApellido()+"|");

                            // SETS GANADOS
                            AuxiliarConversion = String.valueOf(PartidoAux.getSetsGanados_E2());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(12-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // PUNTOS GANADO
                            AuxiliarConversion = String.valueOf(PartidoAux.getPuntosGanados_E2());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(14-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // ACES
                            AuxiliarConversion = String.valueOf(PartidoAux.getAces_E2());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(4-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                            // MAL SAQUE
                            AuxiliarConversion = String.valueOf(PartidoAux.getSaquesErrados_E2());
                            BufferSalida.write("-|");
                            for(int i=0 ; i<(9-AuxiliarConversion.length()) ; i++){
                                BufferSalida.write(" ");
                            }
                            BufferSalida.write(AuxiliarConversion+"|");

                        }
                    }
                    BufferSalida.write("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }

                BufferSalida.write("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                BufferSalida.close();

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
