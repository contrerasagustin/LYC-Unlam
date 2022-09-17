package lyc.compiler;

import lyc.compiler.factories.LexerFactory;
import lyc.compiler.model.*;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;
import static lyc.compiler.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LexerTest {

    private Lexer lexer;

    @Disabled
    @Test
    public void comment() throws Exception {
        scan("/*This is a comment*/");
        assertThat(nextToken()).isEqualTo(ParserSym.EOF);
    }

    @Test
    public void invalidStringConstantLength() {
        assertThrows(InvalidLengthException.class, () -> {
            scan("\"%s\"".formatted(getRandomString()));
            nextToken();
        });
    }

    @Test
    public void invalidStringConstantLengthUpperLimit() {
        assertThrows(InvalidLengthException.class, () -> {
            scan("\"%s\"".formatted(getRandomString(STRING_RANGE + 1)));
            nextToken();
        });
    }

    @Test
    public void validStringConstantLengthLowerLimit() {
        assertDoesNotThrow(() -> {
            scan("\"%s\"".formatted(getRandomString(STRING_RANGE - 1)));
            nextToken();
        });
    }

    @Test
    public void validStringConstantLengthZero() {
        assertDoesNotThrow(() -> {
            scan("\"%s\"".formatted(EMPTY_STRING));
            nextToken();
        });
    }

    @Disabled
    @Test
    public void invalidIdLength() {
        assertThrows(InvalidLengthException.class, () -> {
            scan(getRandomString());
            nextToken();
        });
    }

    @Test
    public void invalidPositiveIntegerConstantValue() {
        assertThrows(InvalidIntegerException.class, () -> {
            scan("%d".formatted(9223372036854775807L));
            nextToken();
        });
    }

    @Test
    public void invalidNegativeIntegerConstantValue() {
        assertThrows(InvalidIntegerException.class, () -> {
            scan("%d".formatted(-9223372036854775807L));
            nextToken();
        });
    }

    @Test
    public void validNegativeIntegerConstantMinValue() {
        assertDoesNotThrow(() -> {
            scan("%d".formatted(INT_RANGE_NEG));
            nextToken();
        });
    }
  @Test
  public void random_test()throws  Exception{
    scan("!! ASDASD ASDASD AS !!");
    assertThat(nextToken()).isEqualTo(ParserSym.EOF);
//    scan("A >> 10");
//    assertThat(nextToken()).isEqualTo(ParserSym.IDENTIFIER);
//    assertThat(nextToken()).isEqualTo(ParserSym.HIGHER);
//    assertThat(nextToken()).isEqualTo(ParserSym.INTEGER_CONSTANT);
  }

    @Test
    public void validNegativeIntegerConstantMinValuePlusOne() {
        assertDoesNotThrow(() -> {
            scan("%d".formatted(INT_RANGE_NEG + 1));
            nextToken();
        });
    }

    @Test
    public void validNegativeIntegerConstantValueZero() {
        assertDoesNotThrow(() -> {
            scan("%d".formatted(0));
            nextToken();
        });
    }

    @Test
    public void validPositiveIntegerConstantMaxValueMinusOne() {
        assertDoesNotThrow(() -> {
            scan("%d".formatted(INT_RANGE_POS - 1));
            nextToken();
        });
    }

    @Test
    public void validPositiveIntegerConstantMaxValue() {
        assertDoesNotThrow(() -> {
            scan("%d".formatted(INT_RANGE_POS));
            nextToken();
        });
    }

    @Test
    public void invalidPositiveFloatConstantValue() {
        assertThrows(InvalidFloatException.class, () -> {
            scan("%f".formatted(9223372036854775807.0));
            nextToken();
        });
    }

    @Test
    public void invalidNegativeFloatConstantValue() {
        assertThrows(InvalidFloatException.class, () -> {
            scan("%f".formatted(-9223372036854775807.0));
            nextToken();
        });
    }

    @Test
    public void validNegativeFloatConstantMinValue() {
        assertDoesNotThrow(() -> {
            scan("%f".formatted(FLOAT_RANGE_NEG + 0.0));
            nextToken();
        });
    }

    @Test
    public void validNegativeFloatConstantMinValuePlusOne() {
        assertDoesNotThrow(() -> {
            scan("%f".formatted(FLOAT_RANGE_NEG + 1.0));
            nextToken();
        });
    }

    @Test
    public void validNegativeFloatConstantValueZero() {
        assertDoesNotThrow(() -> {
            scan("%f".formatted(0.0));
            nextToken();
        });
    }

    @Test
    public void validPositiveFloatConstantMaxValue() {
        assertDoesNotThrow(() -> {
            scan("%f".formatted(FLOAT_RANGE_POS + 0.0));
            nextToken();
        });
    }

    @Test
    public void validPositiveFloatConstantMaxValueMinusOne() {
        assertDoesNotThrow(() -> {
            scan("%f".formatted(FLOAT_RANGE_POS - 1.0));
            nextToken();
        });
    }

    @Test
    public void validPositiveFloatLeftDot() {
        assertDoesNotThrow(() -> {
            scan("%f".formatted(.075));
            nextToken();
        });
    }

    @Test
    public void validPositiveFloatRightDdt() {
        assertDoesNotThrow(() -> {
            scan("%f".formatted(90.));
            nextToken();
        });
    }

    @Test
    public void validNegativeFloatLeftDot() {
        assertDoesNotThrow(() -> {
            scan("%f".formatted(-.075));
            nextToken();
        });
    }

    @Test
    public void validNegativeFloatRightDdt() {
        assertDoesNotThrow(() -> {
            scan("%f".formatted(-90.));
            nextToken();
        });
    }

    @Test
    public void assignmentWithExpressions() throws Exception {
        scan("c := d * ( e - 21 ) / 4");
        assertThat(nextToken()).isEqualTo(ParserSym.IDENTIFIER);
        assertThat(nextToken()).isEqualTo(ParserSym.ASSIG);
        assertThat(nextToken()).isEqualTo(ParserSym.IDENTIFIER);
        assertThat(nextToken()).isEqualTo(ParserSym.MULT);
        assertThat(nextToken()).isEqualTo(ParserSym.OPEN_BRACKET);
        assertThat(nextToken()).isEqualTo(ParserSym.IDENTIFIER);
        assertThat(nextToken()).isEqualTo(ParserSym.SUB);
        assertThat(nextToken()).isEqualTo(ParserSym.INTEGER_CONSTANT);
        assertThat(nextToken()).isEqualTo(ParserSym.CLOSE_BRACKET);
        assertThat(nextToken()).isEqualTo(ParserSym.DIV);
        assertThat(nextToken()).isEqualTo(ParserSym.INTEGER_CONSTANT);
        assertThat(nextToken()).isEqualTo(ParserSym.EOF);
    }

    @Test
    public void unknownCharacter() {
        assertThrows(UnknownCharacterException.class, () -> {
            scan("#");
            nextToken();
        });
    }

    @AfterEach
    public void resetLexer() {
        lexer = null;
    }

    private void scan(String input) {
        lexer = LexerFactory.create(input);
    }

    private int nextToken() throws IOException, CompilerException {
        return lexer.next_token().sym;
    }

    private static String getRandomString() {
        return getRandomString(STRING_RANGE * 2);
    }

    private static String getRandomString(final int size) {
        return new RandomStringGenerator.Builder()
                .filteredBy(CharacterPredicates.LETTERS)
                .withinRange('a', 'z')
                .build().generate(size);
    }

}
