package lexer;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.IOException;
import java.io.StringReader;

import static lexer.Token.Type.*;
import static org.junit.Assert.*;

//import org.junit.Test;

/**
 * This class contains unit tests for your lexer. You
 * are strongly encouraged to write your own tests.
 */
public class LexerTests {
  public static void main(String... args){
    String output = "";
    Result result = JUnitCore.runClasses(LexerTests.class);
    for (Failure failure : result.getFailures()) {
      output += (failure.toString()+"\n");
    }
    output += (result.wasSuccessful() +"\n");
    System.out.println(output);
    return ;
  }
  // helper method to run tests; no need to change this
  private final void runtest(String input, Token... output) {
    Lexer lexer = new Lexer(new StringReader(input));
    int i=0;
    Token actual, expected;
    try {
      do {
        assertTrue(i < output.length);
        expected = output[i++];
        try {
          actual = lexer.nextToken();
          assertEquals(expected, actual);
        } catch(Error e) {
          if(expected != null)
            fail(e.getMessage());
          return;
        }
      } while(!actual.isEOF());
    } catch (IOException e) {
      e.printStackTrace();
      fail(e.getMessage());
    }
  }

  /** Example unit test. */
  //TestHolder
//  @Test
//  public void testKWs() {
//    // first argument to runtest is the string to lex; the remaining arguments
//    // are the expected tokens
//    runtest("module false\nreturn while",
//      new Token(MODULE, 0, 0, "module"),
//      new Token(FALSE, 0, 7, "false"),
//      new Token(RETURN, 1, 0, "return"),
//      new Token(WHILE, 1, 7, "while"),
//      new Token(EOF, 1, 12, ""));
//  }
//
//
//  @Test
//  public void testStringLiteralWithDoubleQuote() {
//    runtest("\"\"\"",
//      (Token)null);
//  }
//
//  @Test
//  public void testStringLiteralEscapeCharacter() {
//    runtest("\"\\n\"",
//      new Token(STRING_LITERAL, 0, 0, "\\n"),
//      new Token(EOF, 0, 4, ""));
//  }

}
