package lyc.compiler;

import java_cup.runtime.Symbol;
import lyc.compiler.ParserSym;
import lyc.compiler.model.*;
import static lyc.compiler.constants.Constants.*;

%%

%public
%class Lexer
%unicode
%cup
%line
%column
%throws CompilerException
%eofval{
  return symbol(ParserSym.EOF);
%eofval}


%{
    // Ints of 16 bits -> From −32768 to 32767
    int RANGE_SIXTEEN_BITS = (int)  (Math.pow(2, 16));
    int INT_RANGE_NEG = RANGE_SIXTEEN_BITS * -1;
    int INT_RANGE_POS = RANGE_SIXTEEN_BITS - 1;

    // Floats of 32 bits -> From -2147483648 to 2147483647
    int RANGE_THIRTY_TWO_BITS = (int)  (Math.pow(2, 32));
    int FLOAT_RANGE_NEG = RANGE_THIRTY_TWO_BITS * -1;
    int FLOAT_RANGE_POS = RANGE_THIRTY_TWO_BITS - 1;

    int STRING_RANGE = 40;

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}


LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Identation =  [ \t\f]

Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
Assig = "="
OpenBracket = "("
CloseBracket = ")"
Letter = [a-zA-Z]
Digit = [0-9]
Dot = "."

WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
NumberConstant = {Digit}+
NumberOptionalConstant = {Digit}*

// DataTypeValues
FloatConstant = {Sub}? ({NumberConstant} {Dot} {NumberOptionalConstant} | {NumberOptionalConstant} {Dot} {NumberConstant})
IntegerConstant = {Sub}? {NumberConstant}
StringConstant =  \"({Letter}|{NumberConstant})*\"

%%


/* keywords */

<YYINITIAL> {
  /* identifiers */
  {Identifier}                             { return symbol(ParserSym.IDENTIFIER, yytext()); }

  /* operators */
  {Plus}                                    { return symbol(ParserSym.PLUS); }
  {Sub}                                     { return symbol(ParserSym.SUB); }
  {Mult}                                    { return symbol(ParserSym.MULT); }
  {Div}                                     { return symbol(ParserSym.DIV); }
  {Assig}                                   { return symbol(ParserSym.ASSIG); }
  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }

  /* DataTypeValues */
  {FloatConstant}                           {
                                             final Double floatConstantValue = Double.parseDouble(yytext());
                                             final boolean isValidPositiveRange = floatConstantValue <= FLOAT_RANGE_POS;
                                             final boolean isValidNegativeRange = floatConstantValue >= FLOAT_RANGE_NEG;
                                             if (isValidPositiveRange && isValidNegativeRange)
                                                 return symbol(ParserSym.FLOAT_CONSTANT);

                                             String errorMessage = "La constante [" + yytext() + "] esta por encima del limite de los flotantes. (Se obtuvo " + floatConstantValue + ", maximo permitido: " + FLOAT_RANGE_POS + ")";
                                             if(!isValidNegativeRange)
                                                 errorMessage = "La constante [" + yytext() + "] esta por debajo del limite de los flotantes. (Se obtuvo " + floatConstantValue + ", mínimo permitido: " + FLOAT_RANGE_NEG + ")";

                                             System.err.println(errorMessage);
                                             System.in.read();
                                             throw new Error(errorMessage);
                                             }

  {IntegerConstant}                           {
                                             final Integer integerConstantValue = Integer.parseInt(yytext());
                                             final boolean isValidPositiveRange = integerConstantValue <= INT_RANGE_POS;
                                             final boolean isValidNegativeRange = integerConstantValue >= INT_RANGE_NEG;
                                             if (isValidPositiveRange && isValidNegativeRange)
                                                 return symbol(ParserSym.INTEGER_CONSTANT);

                                             String errorMessage = "La constante [" + yytext() + "] esta por encima del limite de los flotantes. (Se obtuvo " + integerConstantValue + ", maximo permitido: " + INT_RANGE_POS + ")";
                                             if(!isValidNegativeRange)
                                                 errorMessage = "La constante [" + yytext() + "] esta por debajo del limite de los flotantes. (Se obtuvo " + integerConstantValue + ", mínimo permitido: " + INT_RANGE_NEG + ")";

                                             System.err.println(errorMessage);
                                             System.in.read();
                                             throw new Error(errorMessage);
                                             }

  {StringConstant}                           {
                                             final String stringConstant = new String(yytext());
                                             if (stringConstant.length() - 2 <= STRING_RANGE)
                                                 return symbol(ParserSym.STRING_CONSTANT);

                                             String errorMessage = "La constante [" + yytext() + "] excede el largo permitido para un string. (Se obtuvo una cadena de tamaño" + stringConstant.length() + ", maximo permitido: " + STRING_RANGE + ")";

                                             System.err.println(errorMessage);
                                             System.in.read();
                                             throw new Error(errorMessage);
                                             }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
