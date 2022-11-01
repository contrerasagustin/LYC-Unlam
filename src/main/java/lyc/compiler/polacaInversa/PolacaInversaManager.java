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
                lista.set(pila.pop()-1,"#"+(lista.size()+1));
                break;
            case "BLE":
            case "BNE":
            case "BEQ":
            case "BGT":
            case "BLT":
            case "BGE":
                lista.add("CMP");
                lista.add(elemento);
                lista.add("#");
                pila.add(lista.size());
                break;
            case "BI":
                int auxInt = pila.peek()  - 5;
                System.out.println("aux int"+ auxInt);
                System.out.println("lista"+ lista.get(auxInt));
                lista.add("BI");
                while (auxInt != 0){
                    if(! lista.get(auxInt-1).contains("#")){
                        lista.add("#"+(auxInt));
                        break;
                    }else{
                        auxInt = auxInt  - 4;
                    }
                }
                if(auxInt==0)
                    lista.add("#1");
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


    public ArrayList<String> getLista(){
        return this.lista;
    }
}
