package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IntermediateCodeGenerator implements FileGenerator {

    private ArrayList<String> listaPolacaInversa = new ArrayList<String>();

    public IntermediateCodeGenerator(ArrayList<String> listaPolacaInversa){
        this.listaPolacaInversa = listaPolacaInversa;
    }

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        try {
            if (listaPolacaInversa.isEmpty())
                return;

            fileWriter.write("--- INICIO DE POLACA INVERSA ---\n");

            int i = 1;
            for(String celda : listaPolacaInversa){
                fileWriter.write(i + ") " + celda + "\n");
                i++;
            }
            fileWriter.write("--- FIN DE POLACA INVERSA ---\n");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
