package Clases.Excepciones;
public class IngresoNumero_Excepcion extends Exception{
    // CONSTRUCTOR
    public IngresoNumero_Excepcion(){
        super("\n Solo debe ingresar caracteres numericos.");
    }
}