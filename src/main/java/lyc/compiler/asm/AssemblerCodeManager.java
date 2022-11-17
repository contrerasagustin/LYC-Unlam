package lyc.compiler.asm;

import lyc.compiler.symbol_table.Symbol;

import java.util.ArrayList;
import java.util.List;

public class AssemblerCodeManager {

    ArrayList<String> instrucciones = new ArrayList<String>();


    public AssemblerCodeManager(){
    }

    public void generarAssembler(List<Symbol> TablaDeSimbolos , ArrayList<String> polacaInversa){

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

        ///instrucciones del programador
        //FALTA IMPLEMENTAR!!!!!
        int i = 0;
        for(i = 0 ; i < 10 ; i++){
            instrucciones.add("MOV " + i);
        }


        //pie de fin de codigo
        instrucciones.add("\nMOV AX, 4C00h");
        instrucciones.add("INT 21h");
        instrucciones.add("END");
    }

    public ArrayList<String> getInstrucciones(){
        return instrucciones;
    }


}
