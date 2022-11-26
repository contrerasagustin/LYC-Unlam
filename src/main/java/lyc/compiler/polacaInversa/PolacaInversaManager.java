package lyc.compiler.polacaInversa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class PolacaInversaManager {

    private ArrayList<String> lista = new ArrayList<String>();
    private Stack<Integer> pila = new Stack<Integer>();

    private Stack<String> pilaM = new Stack<String>(); //condiciones multiples

    private int cont = 1;

    private Stack<String> allequal = new Stack<String>();


    public void insertar(String elemento) {
        switch (elemento) {
            case "UNSTACK":
                lista.set(pila.pop()-1,"#"+(lista.size()+1));
                if(pilaM.size()!=0 && (pilaM.peek() == "AND" ||  pilaM.peek() == "OR") ){
                    pilaM.pop();
                    lista.set(pila.pop()-1,"#"+(lista.size()+1));
                }
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
            case "ET":
                lista.add("ET");
                pila.add(lista.size());
                break;
            case "BI":
                lista.set(pila.pop()-1,"#"+(lista.size()+3));
                lista.add(elemento);
                lista.add("#"+pila.pop());
                break;
            case "AND":
            case "OR":
            case "SINGLE":
                pilaM.add(elemento);
                break;
            case "COMPONENT":
                lista.add("_aux"+cont);
                allequal.add("_aux"+cont);
                lista.add(":=");
                cont++;
                break;
            case "ALLEQUAL":
//                System.out.println("tope de pila "+allequal.peek());
//                System.out.println("tam "+allequal.size());
                while (allequal.size()!=0){
                    lista.add(allequal.pop());
                    String aux = allequal.pop();
                    if (allequal.size()!=0){
                        lista.add(allequal.pop());
                        allequal.add(aux);
                    }else {
                        lista.add(aux);
                    }
                    lista.add("CMP");
                    lista.add("BNE");
                    lista.add("#");
                    pila.add(lista.size());
                    pilaM.add("AND");
                }
                cont = cont / 2;
                if (cont == 1){
                    lista.set(pila.pop()-1,"#"+(lista.size()+4));
                }else
                {
                    while (cont != 1){
                        lista.set(pila.pop()-1,"#"+(lista.size()+4));
                        if(pilaM.pop()=="AND"){
                            lista.set(pila.pop()-1,"#"+(lista.size()+4));
                        }
                        cont --;
                    }
                }
                lista.add("TRUE");
                lista.add("BI");
                lista.add("#"+(lista.size()+3));
                lista.add("FALSE");
                cont = 1;
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
