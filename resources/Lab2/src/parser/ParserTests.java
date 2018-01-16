package parser;

import static org.junit.Assert.fail;

import java.io.StringReader;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ParserTests {
  public static void main(String... args){
    String output = "";
    Result result = JUnitCore.runClasses(ParserTests.class);
    for (Failure failure : result.getFailures()) {
      output += (failure.toString()+"\n");
    }
    output += (result.wasSuccessful() +"\n");
    System.out.println(output);
    return ;
  }
	private void runtest(String src) {
		runtest(src, true);
	}

	private void runtest(String src, boolean succeed) {
		Parser parser = new Parser();
		try {
			parser.parse(new Lexer(new StringReader(src)));
			if(!succeed) {
				fail("Test was supposed to fail, but succeeded");
			}
		} catch (beaver.Parser.Exception e) {
			if(succeed) {
				e.printStackTrace();
				fail(e.getMessage());
			}
		} catch (Throwable e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
  //TestHolder
}
