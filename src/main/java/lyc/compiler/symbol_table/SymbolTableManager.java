package lyc.compiler.symbol_table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SymbolTableManager {
    private final List<Symbol> symbolList;
    private int constStringNumber;

    public SymbolTableManager() {
        this.symbolList = new ArrayList<>();
        this.constStringNumber = 0;
    }

    public List<Symbol> getSymbolList() {
        return symbolList;
    }

    public void add(String nombre, DataType tipo, String valor, Integer longitud) {
        if (!isInTable(nombre)) {
            symbolList.add(new Symbol(nombre, tipo, valor, longitud));
        }
    }

    public String addStringConstant(DataType tipo, String valor, Integer longitud) {
        String nombre = "_constString" + constStringNumber;
        constStringNumber++;
        if (!isInTable(nombre)) {
            symbolList.add(new Symbol(nombre, tipo, valor, longitud));
        }
        return nombre;
    }

    public void addIdentifiers(ArrayList<String> identifiers, DataType dataType) {
        Iterator<String> i = identifiers.iterator();
        while (i.hasNext()) {
            // must be called before you can call i.remove()
            String id = i.next();
            if (!(isInTable(id))) {
                this.add(id, dataType, "-", null);
            } else {
                throw new Error("Error de sintaxis: la variable '" + id + "' ya habia sido declarada.");
            }
            // Remove identifier from list
            i.remove();
        }
    }

    public Boolean isInTable(String nombre) {
        return symbolList.stream().anyMatch(symbol -> symbol.getName().equals(nombre));
    }
}
