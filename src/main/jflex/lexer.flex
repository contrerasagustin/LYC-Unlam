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
Write = "write"
Read = "read"
Init = "init"
Equal = "=="
Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
Assig = ":="
Higher = ">>"
Lower = "<<"
HigherEqual = ">="
LowerEqual = "<="
And = "and"
Or = "or"
Not = "not"
Distinct  = "!="
True_Bool = "true"
False_Bool = "false"
Increment = ":+"
Decrement = ":-"
Semicolon = ";"
OpenBracket = "("
CloseBracket = ")"
OpenCurlyBracket = "{"
CloseCurlyBracket = "}"

Letter = [a-zA-Z]
Digit = [0-9]

WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Digit}+
StringConstant = \"({Letter}|{Digit})*\"
%%


/* keywords */

<YYINITIAL> {

  /* Constants */
  {IntegerConstant}                         { return symbol(ParserSym.INTEGER_CONSTANT, yytext()); }

  /* operators */
  {Plus}                                    { return symbol(ParserSym.PLUS); }
  {Sub}                                     { return symbol(ParserSym.SUB); }
  {Mult}                                    { return symbol(ParserSym.MULT); }
  {Div}                                     { return symbol(ParserSym.DIV); }
  {Assig}                                   { return symbol(ParserSym.ASSIG); }
  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }
  {OpenCurlyBracket}                        { return symbol(ParserSym.OPEN_CURLY_BRACKET); }
  {CloseCurlyBracket}                       { return symbol(ParserSym.CLOSE_CURLY_BRACKET); }
  {Equal}                                   {return symbol((ParserSym.EQUAL));}
  {Higher}                                  {return symbol((ParserSym.HIGHER));}
  {Lower}                                   {return symbol((ParserSym.LOWER));}
  {HigherEqual}                             {return symbol((ParserSym.HIGHER_EQUAL));}
  {LowerEqual}                              {return symbol((ParserSym.LOWER_EQUAL));}
  {And}                                     {return symbol((ParserSym.AND));}
  {Or}                                      {return symbol((ParserSym.OR));}
  {Not}                                     {return symbol((ParserSym.NOT));}
  {Distinct}                                {return symbol((ParserSym.DISTINCT));}
  {True_Bool}                               {return symbol((ParserSym.TRUE_BOOL));}
  {False_Bool}                              {return symbol((ParserSym.FALSE_BOOL));}
  {Increment}                               {return symbol((ParserSym.INCREMENT));}
  {Decrement}                               {return symbol((ParserSym.DECREMENT));}
  {Semicolon}                               {return symbol((ParserSym.SEMICOLON));}



  /* reserved words */

  {If}                                      { return symbol(ParserSym.IF);}
  {Else}                                    { return symbol(ParserSym.ELSE);}
  {Begin}                                   { return symbol(ParserSym.BEGIN);}
  {End}                                     { return symbol(ParserSym.END);}
  {For}                                     { return symbol(ParserSym.FOR);}
  {Write}                                   { return symbol(ParserSym.WRITE);}
  {Read}                                    { return symbol(ParserSym.READ);}
  {Init}                                    { return symbol(ParserSym.INIT);}

  /* identifiers */
  {Identifier}                              { return symbol(ParserSym.IDENTIFIER, yytext()); }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
