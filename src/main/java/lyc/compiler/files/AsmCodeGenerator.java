package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AsmCodeGenerator implements FileGenerator {

    //esta clase solamente graba en un archivo, la logica de generacion está en AssemblerCodeManager
    private ArrayList<String> instrucciones = new ArrayList<String>();
    public AsmCodeGenerator(ArrayList<String> instrucciones){
        this.instrucciones = instrucciones;
    }

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        for(String instruccion : instrucciones){
            fileWriter.write(instruccion + '\n');
        }
    }
}
