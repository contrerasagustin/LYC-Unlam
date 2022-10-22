package lyc.compiler.polacaInversa;

import java.util.ArrayList;
import java.util.LinkedList;

public class PolacaInversaManager {

    private ArrayList<String> lista = new ArrayList<String>();

    public void insertar(String elemento) {
        lista.add(elemento);
    }

    public void mostrar(){
        System.out.println("---MOSTRANDO POLACA INVERSA------");
        int i = 1;
        for(String celda : lista){
            System.out.println(i + ") " + celda);
            i++;
        }
    }
}
