package Clases.Excepciones;
public class IngresoPalabra_Excepcion extends Exception{
    // CONSTRUCTOR
    public IngresoPalabra_Excepcion(){
        super("\n Solo debe ingresar caracteres alfabeticos.");
    }
}