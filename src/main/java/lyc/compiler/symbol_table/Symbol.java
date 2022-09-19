package lyc.compiler.symbol_table;

public class Symbol {
    private String name;
    private String value;
    private DataType type;
    private Integer lenght;

    public Symbol(String nombre, DataType tipo, String valor, Integer longitud) {
        this.name = nombre;
        this.type = tipo;
        this.value = valor;
        this.lenght = longitud;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    private String toStringObject(Object o) {
        return o != null ? "" + o : "-";
    }

    @Override
    public String toString() {
        return String.format(
                "%-30s|%-30s|%-30s|%-30s", toStringObject(name), toStringObject(type), toStringObject(value), toStringObject(lenght)
        );
    }

}
