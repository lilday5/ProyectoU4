import org.antlr.runtime.*;
public class Test {

   public static void main(String[] args) throws Exception 
	 {
      // create a CharStream that reads from standard input
      ANTLRInputStream input = new ANTLRInputStream(System.in);
   	TLexer lexer = new TLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      
      TParser parser = new TParser(tokens);
      parser.inicio();
     }
}