package lyc.compiler.polacaInversa;

import java.util.LinkedList;

public class PolacaInversaManager {

    private LinkedList<String> lista = new LinkedList<String>();

    public void insertar(String elemento) {
        lista.push(elemento);
    }

    public void mostrar(){
        System.out.println("---MOSTRANDO POLACA INVERSA------");
        for(String celda : lista){
            System.out.println(celda);
        }
    }
}
