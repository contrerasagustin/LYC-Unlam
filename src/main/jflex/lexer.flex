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

If = "if"
Else = "else"
Begin = "begin"
End = "end"
For = "for"
Equal = "=="
Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
Assig = ":="
OpenBracket = "("
CloseBracket = ")"
Letter = [a-zA-Z]
Digit = [0-9]

WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Digit}+
StringConstant = \"({Letter}|{Digit})*\"
%%


/* keywords */

<YYINITIAL> {
  /* identifiers */
  {Identifier}                             { return symbol(ParserSym.IDENTIFIER, yytext()); }
  /* Constants */
  {IntegerConstant}                        { return symbol(ParserSym.INTEGER_CONSTANT, yytext()); }

  /* operators */
  {Plus}                                    { return symbol(ParserSym.PLUS); }
  {Sub}                                     { return symbol(ParserSym.SUB); }
  {Mult}                                    { return symbol(ParserSym.MULT); }
  {Div}                                     { return symbol(ParserSym.DIV); }
  {Assig}                                   { return symbol(ParserSym.ASSIG); }
  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }
  {Equal}                                   {return symbol((ParserSym.EQUAL));}

  /* reserved words */

  {If}                                      { return symbol(ParserSym.IF);}
  {Else}                                    { return symbol(ParserSym.ELSE);}
  {Begin}                                   { return symbol(ParserSym.BEGIN);}
  {End}                                     { return symbol(ParserSym.END);}
  {For}                                     { return symbol(ParserSym.FOR);}




  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
