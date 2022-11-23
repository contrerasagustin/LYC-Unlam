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

//    @Test
//    public void myTest() throws Exception {
//        System.out.println("Ejecutando Test");
//        System.out.println("////////////////////////////////////////////");
//        compilationSuccessful(readFromFile("test.txt"));
//        System.out.println("////////////////////////////////////////////");
//        System.out.println("Fin del Test");
//    }

//    @Test
//    public void myTest() throws Exception {
//        System.out.println("Ejecutando Test Basico");
//        System.out.println("////////////////////////////////////////////");
//        compilationSuccessful(readFromFile("testBasico.txt"));
//        System.out.println("////////////////////////////////////////////");
//        System.out.println("Fin del Test");
//    }

    @Test
    public void myTest() throws Exception {
        System.out.println("Ejecutando Test ASM Basico");
        System.out.println("////////////////////////////////////////////");
        compilationSuccessful(readFromFile("test_asm_basico.txt"));
        System.out.println("////////////////////////////////////////////");
        System.out.println("Fin del Test");
    }
//    @Test
//    public void assignmentWithExpression() throws Exception {
//        System.out.println("Ejecutando Asignacion");
//        compilationSuccessful("c := d * ( e - 21 ) /4;");
//    }

//    @Test
//    public void switchtest() throws Exception {
//        System.out.println("Ejecutando Switch");
//        compilationSuccessful(readFromFile("switch.txt"));
//    }

//    @Test
//    public void syntaxError() {
//        System.out.println("Ejecutando Test Error sintaxis");
//        compilationError("1234");
//    }
//
//    @Test
//    void assignments() throws Exception {
//        System.out.println("Ejecutando Test Asignacion");
//        compilationSuccessful(readFromFile("assignments.txt"));
//    }
//
//    @Test
//    void write() throws Exception {
//        System.out.println("Ejecutando Test Write");
//        compilationSuccessful(readFromFile("write.txt"));
//    }
//
//    @Test
//    void allequal() throws Exception {
//        System.out.println("Ejecutando Test Allequal");
//        compilationSuccessful(readFromFile("allequal.txt"));
//    }
//
//    @Test
//    void repeat() throws Exception {
//        System.out.println("Ejecutando Test Repeat");
//        compilationSuccessful(readFromFile("repeat.txt"));
//    }
//
//    @Test
//    void read() throws Exception {
//        System.out.println("Ejecutando Test Read");
//        compilationSuccessful(readFromFile("read.txt"));
//    }
//
//    @Test
//    void commentWithCode() throws Exception {
//        System.out.println("Ejecutando Test Comentario con codigo");
//        compilationSuccessful(readFromFile("comment_with_code.txt"));
//    }
//
//    @Test
//    void commentWithNoCode() throws Exception {
//        System.out.println("Ejecutando Test Comentario sin codigo");
//        compilationError(readFromFile("comment_without_code.txt"));
//    }
//
//    @Test
//    void init() throws Exception {
//        System.out.println("Ejecutando Test Init");
//        compilationSuccessful(readFromFile("init.txt"));
//    }
//
//    @Test
//    void and() throws Exception {
//        System.out.println("Ejecutando Test And");
//        compilationSuccessful(readFromFile("and.txt"));
//    }
//
//    @Test
//    void or() throws Exception {
//        System.out.println("Ejecutando Test Or");
//        compilationSuccessful(readFromFile("or.txt"));
//    }
//
//    @Test
//    void not() throws Exception {
//        System.out.println("Ejecutando Test Not");
//        compilationSuccessful(readFromFile("not.txt"));
//    }
//
//    @Test
//    void ifStatement() throws Exception {
//        System.out.println("Ejecutando Test If");
//        compilationSuccessful(readFromFile("if.txt"));
//    }
//
//    @Test
//    void whileStatement() throws Exception {
//        System.out.println("Ejecutando Test While");
//        compilationSuccessful(readFromFile("while.txt"));
//    }


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
