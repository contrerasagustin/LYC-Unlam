package lyc.compiler.asm;

import lyc.compiler.symbol_table.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AssemblerCodeManager {

    ArrayList<String> instrucciones = new ArrayList<String>();


    public AssemblerCodeManager(){
    }

    public void generarAssembler(List<Symbol> TablaDeSimbolos , ArrayList<String> polacaInversa){

        Stack<String> pila  = new Stack<String>();
        ArrayList<String> codigoProgramador = new ArrayList<String>();


        instrucciones.add("-------------- INICIO DE CODIGO ASSEMBLER ---------------");
        instrucciones.add(".MODEL LARGE");
        instrucciones.add(".386");
        instrucciones.add(".STACK 200h\n");

        ///tabla de simbolos
        instrucciones.add(".DATA\n");
        for(Symbol simbolo : TablaDeSimbolos){
            instrucciones.add(String.format("%-20s %-5s %-30s", simbolo.getName(),"dd", simbolo.getValue()));
        }

        //cabecera de instrucciones
        instrucciones.add("\n.CODE");
        instrucciones.add("\nMOV AX, @DATA");
        instrucciones.add("MOV DS, AX");
        instrucciones.add("MOV ES, AX\n");


        //codigo del programador
        int cantVariablesAuxiliares = 0;
        for(String celda : polacaInversa){
                switch (celda){
                    case ":=":
                    {
                        String op2 = pila.pop();
                        String op1 = pila.pop();
                        codigoProgramador.add("FLD " + op1);
                        codigoProgramador.add("FSTP " + op2);
                        codigoProgramador.add("");
                        break;
                    }
                    case "+":
                    {
                        String op2 = pila.pop();
                        String op1 = pila.pop();
                        String varAux = "@aux" + (cantVariablesAuxiliares+1);
                        cantVariablesAuxiliares++;
                        codigoProgramador.add("FLD " + op1);
                        codigoProgramador.add("FLD " + op2);
                        codigoProgramador.add("FADD");
                        codigoProgramador.add("FSTP " + varAux);
                        codigoProgramador.add("");
                        pila.add(varAux);
                        break;
                    }
                    case "-":
                    {
                        String op2 = pila.pop();
                        String op1 = pila.pop();
                        String varAux = "@aux" + (cantVariablesAuxiliares+1);
                        cantVariablesAuxiliares++;
                        codigoProgramador.add("FLD " + op1);
                        codigoProgramador.add("FLD " + op2);
                        codigoProgramador.add("FSUB");
                        codigoProgramador.add("FSTP " + varAux);
                        codigoProgramador.add("");
                        pila.add(varAux);
                        break;
                    }
                    case "/":
                    {
                        String op2 = pila.pop();
                        String op1 = pila.pop();
                        String varAux = "@aux" + (cantVariablesAuxiliares+1);
                        cantVariablesAuxiliares++;
                        codigoProgramador.add("FLD " + op1);
                        codigoProgramador.add("FLD " + op2);
                        codigoProgramador.add("FDIV");
                        codigoProgramador.add("FSTP " + varAux);
                        codigoProgramador.add("");
                        pila.add(varAux);
                        break;
                    }
                    case "*":
                    {
                        String op2 = pila.pop();
                        String op1 = pila.pop();
                        String varAux = "@aux" + (cantVariablesAuxiliares+1);
                        cantVariablesAuxiliares++;
                        codigoProgramador.add("FLD " + op1);
                        codigoProgramador.add("FLD " + op2);
                        codigoProgramador.add("FMUL");
                        codigoProgramador.add("FSTP " + varAux);
                        codigoProgramador.add("");
                        pila.add(varAux);
                        break;
                    }
                    default: {
                        pila.add(celda);
                        break;
                    }
                }
            }

        ///hago esto para que no se me rompa la logica agregando el codigo de la cabecera y etc
        for(String instruccion : codigoProgramador){
            instrucciones.add(instruccion);
        }

//        System.out.println("**************** MOSTRANDO PILA /**********************");
//        while(!pila.isEmpty()){
//            System.out.println(pila.pop());
//        }
//        System.out.println("**************** FIN DE LA PILA /**********************");

        //pie de fin de codigo
        instrucciones.add("\nMOV AX, 4C00h");
        instrucciones.add("INT 21h");
        instrucciones.add("END");

        instrucciones.add("-------------- FIN DE CODIGO ASSEMBLER ---------------");


        ///mostrando TODAS las instrucciones (incluye cabecera y etc)
        for(String instruccion : instrucciones){
            System.out.println(instruccion);
        }
    }

    public ArrayList<String> getInstrucciones(){
        return instrucciones;
    }


}
