package lyc.compiler.constants;

public final class Constants {
    // Ints of 16 bits -> From −32768 to 32767
    public static final int  RANGE_SIXTEEN_BITS = (int)  (Math.pow(2, 16));
    public static final int  INT_RANGE_NEG = RANGE_SIXTEEN_BITS * -1;
    public static final int  INT_RANGE_POS = RANGE_SIXTEEN_BITS - 1;

    // Floats of 32 bits -> From -2147483648 to 2147483647
    public static final int  RANGE_THIRTY_TWO_BITS = (int)  (Math.pow(2, 32));
    public static final int  FLOAT_RANGE_NEG = RANGE_THIRTY_TWO_BITS * -1;
    public static final int  FLOAT_RANGE_POS = RANGE_THIRTY_TWO_BITS - 1;

    public static final int STRING_RANGE = 40;
}
