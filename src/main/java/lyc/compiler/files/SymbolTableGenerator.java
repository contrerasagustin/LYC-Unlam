package lyc.compiler.files;

import lyc.compiler.symbol_table.Symbol;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class SymbolTableGenerator implements FileGenerator {
    public static Logger LOGGER = Logger.getLogger(SymbolTableGenerator.class.getName());

    private final List<Symbol> symbolList;

    public SymbolTableGenerator(List<Symbol> symbolList) {
        this.symbolList = symbolList;
    }

    public SymbolTableGenerator() {
        this.symbolList = Collections.emptyList();
    }

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        try {
            if (symbolList.isEmpty())
                return;

            fileWriter.write(String.format("%-30s|%-30s|%-30s|%-30s\n", "NOMBRE", "TIPODATO", "VALOR", "LONGITUD"));
            symbolList.forEach(symbol -> {
                try {
                    fileWriter.write(symbol.toString() + "\n");
                } catch (IOException e) {
                    LOGGER.severe("Ocurrio un error al guardar los symbols");
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            LOGGER.severe("Ocurrio un error al guardar el archivo");
            e.printStackTrace();
        }
    }
}
