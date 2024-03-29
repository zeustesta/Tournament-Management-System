package Clases.Exportador;
import Clases.Administrador.Administrador_Nueva;
import Clases.Equipo.Equipo;
import Clases.Partido.Partido;
import Clases.Torneo.Torneo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ImportarExportar_Gson {
    public static Administrador_Nueva ImportarDatos(String Path, String Name){
        Administrador_Nueva Retornar;
        Gson _Gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDate_Adaptador()).create();
        File Archivo = new File(Path, Name+".json");

        if (Archivo.exists() && !Archivo.isDirectory() && Archivo.canRead()){
            try{
                BufferedReader Lector = new BufferedReader(new FileReader(Archivo));
                String Json = Lector.readLine();
                Retornar = _Gson.fromJson(Json, Administrador_Nueva.class);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            Retornar = new Administrador_Nueva();
        }

        return Retornar;
    }
    public static void ExportarDatos(String Path, String Name, Administrador_Nueva Guardar){
        Gson _Gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDate_Adaptador()).create();
        String Escribir = _Gson.toJson(Guardar);
        try {
            BufferedWriter Escritor = new BufferedWriter(new FileWriter(new File(Path, Name+".json")));
            Escritor.write(Escribir);
            Escritor.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public static void SetearContadoresId(String Path, String Name){
        File Archivo;
        FileReader ArchivoLector;
        BufferedReader Lector;
        try {
            Archivo = new File(Path, Name);
            if (Archivo.exists()){
                ArchivoLector = new FileReader(new File(Path, Name));
                Lector = new BufferedReader(ArchivoLector);
                ArrayList<String> Leidos = new ArrayList<>();
                String Leido;
                while ((Leido = Lector.readLine())!=null){
                    Leidos.add(Leido);
                }
                Equipo.Contador_Equipos = Integer.parseInt(Leidos.get(0));
                Partido.ContadorPartidos = Integer.parseInt(Leidos.get(1));
                Torneo.ContadorTorneos = Integer.parseInt(Leidos.get(2));

                Lector.close();
                ArchivoLector.close();
            }
            else{
                Equipo.Contador_Equipos = 0;
                Partido.ContadorPartidos = 0;
                Torneo.ContadorTorneos = 0;
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void GuardarContadores(String Path, String Name){
        FileWriter Archivo;
        BufferedWriter Escritor;

        try{
            Archivo = new FileWriter(new File(Path, Name));
            Escritor = new BufferedWriter(Archivo);

            Escritor.write(Equipo.Contador_Equipos +"\n");
            Escritor.write(Partido.ContadorPartidos +"\n");
            Escritor.write(Torneo.ContadorTorneos +"\n");

            Escritor.close();
            Archivo.close();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}