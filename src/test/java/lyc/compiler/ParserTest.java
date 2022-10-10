package lyc.compiler;

import java_cup.runtime.Symbol;
import lyc.compiler.factories.ParserFactory;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void myTest() throws Exception {
        System.out.println("Ejecutando Test");
        System.out.println("////////////////////////////////////////////");
        compilationSuccessful(readFromFile("test.txt"));
        System.out.println("////////////////////////////////////////////");
        System.out.println("Fin del Test");
    }

    @Test
    public void assignmentWithExpression() throws Exception {
        compilationSuccessful("c := d * ( e - 21 ) /4;");
    }

    @Test
    public void switchtest() throws Exception {
        compilationSuccessful(readFromFile("switch.txt"));
    }


    @Test
    public void syntaxError() {
        compilationError("1234");
    }

    @Test
    void assignments() throws Exception {
        compilationSuccessful(readFromFile("assignments.txt"));
    }

    @Test
    void write() throws Exception {
        compilationSuccessful(readFromFile("write.txt"));
    }

    @Test
    void allequal() throws Exception {
        compilationSuccessful(readFromFile("allequal.txt"));
    }

    @Test
    void repeat() throws Exception {
        compilationSuccessful(readFromFile("repeat.txt"));
    }

    @Test
    void read() throws Exception {
        compilationSuccessful(readFromFile("read.txt"));
    }

    @Test
    void commentWithCode() throws Exception {
        compilationSuccessful(readFromFile("comment_with_code.txt"));
    }

    @Test
    void commentWithNoCode() throws Exception {
        compilationError(readFromFile("comment_without_code.txt"));
    }

    @Test
    void init() throws Exception {
        compilationSuccessful(readFromFile("init.txt"));
    }

    @Test
    void and() throws Exception {
        compilationSuccessful(readFromFile("and.txt"));
    }

    @Test
    void or() throws Exception {
        compilationSuccessful(readFromFile("or.txt"));
    }

    @Test
    void not() throws Exception {
        compilationSuccessful(readFromFile("not.txt"));
    }

    @Test
    void ifStatement() throws Exception {
        compilationSuccessful(readFromFile("if.txt"));
    }

    @Test
    void whileStatement() throws Exception {
        compilationSuccessful(readFromFile("while.txt"));
    }


    private void compilationSuccessful(String input) throws Exception {
        assertThat(scan(input).sym).isEqualTo(ParserSym.EOF);
    }

    private void compilationError(String input){
        assertThrows(Exception.class, () -> scan(input));
    }

    private Symbol scan(String input) throws Exception {
        return ParserFactory.create(input).parse();
    }

    private String readFromFile(String fileName) throws IOException {
        InputStream resource = getClass().getResourceAsStream("/%s".formatted(fileName));
        assertThat(resource).isNotNull();
        return IOUtils.toString(resource, StandardCharsets.UTF_8);
    }


}
