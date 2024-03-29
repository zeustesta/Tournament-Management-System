package Clases.Excepciones;

public class IngresoAlfanumerico_Excepcion extends Exception {
    // CONSTRUCTOR
    public IngresoAlfanumerico_Excepcion(){
        super("\n Solo debe ingresar caracteres alfanumericos.");
    }
}