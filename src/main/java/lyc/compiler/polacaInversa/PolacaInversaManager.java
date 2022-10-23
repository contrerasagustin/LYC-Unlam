package lyc.compiler.polacaInversa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class PolacaInversaManager {

    private ArrayList<String> lista = new ArrayList<String>();
    private Stack<Integer> pila = new Stack<Integer>();



    public void insertar(String elemento) {
        switch (elemento) {
            case "UNSTACK":
                String aux = lista.set(pila.pop(),"#"+(lista.size()+1));
                break;
            case "BLE":
            case "BEQ":
            case "BHI":
            case "BHE":
            case "BLS":
            case "BHG":
                lista.add(elemento);
                lista.add("#");
                pila.add(lista.size() - 1);
                break;
            default:
                lista.add(elemento);
                break;
        }
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
