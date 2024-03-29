package Clases.Generica;
import java.util.ArrayList;
import java.util.Iterator;
public class ListaDeDatos <T> implements Iterable<T>{
    private ArrayList<T>datos = new ArrayList<>();
    public ListaDeDatos() {

    }
    public void add(int indice,T elemento){
        datos.add(indice,elemento);
    }
    public int size(){
        return datos.size();
    }
    public void remove(int indice){
        datos.remove(indice);
    }
    public boolean add(T elemento){
        return datos.add(elemento);
    }
    public T get(int indice){
        return datos.get(indice);
    }
    public int indexOf(T elemento){
        return datos.indexOf(elemento);
    }
    @Override
    public Iterator<T> iterator() {
        return datos.iterator();
    }
}