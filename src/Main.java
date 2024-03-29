import Clases.Administrador.Administrador_Nueva;
import Clases.Exportador.ImportarExportar_Gson;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        // ABRIR TECLADO
        Scanner Teclado = new Scanner(System.in);

        // IMPORTAR CONTADORES ESTATICOS Y DATOS DE JSON CON GSON
        ImportarExportar_Gson.SetearContadoresId("src/Archivos", "Contadores.txt");
        Administrador_Nueva Admin = ImportarExportar_Gson.ImportarDatos("src/Clases/Administrador", "ObjetoAdministrador");

        // DESCOMENTAR ESTA LINEA EN CASO DE NO TENER CARGADOS EQUIPOS
        //Admin.Importar_Personas_Equipos();

        // MENU PRINCIPAL
        Admin.Menu_Principal(Teclado);

        // EXPORTAR CONTADORES ESTATICOS Y DATOS A JSON CON GSON
        ImportarExportar_Gson.ExportarDatos("src/Clases/Administrador", "ObjetoAdministrador", Admin);
        ImportarExportar_Gson.GuardarContadores("src/Archivos", "Contadores.txt");

        // CERRAR TECLADO
        Teclado.close();
    }
}