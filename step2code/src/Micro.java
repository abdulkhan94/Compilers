import java.io.*;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.*;
import java.util.Scanner;

/**
 *Authors: Harry Koris, Abdullah Khan
 */

class Micro {

    public static void main(String[] args) {
	

	try{
	    //Uses a scanner object and makes one token out of the whole object. Then stores it to a string.
	    File fil = new File(args[0]);
	    Scanner scan = new Scanner(fil);
	    scan = scan.useDelimiter("\\Z");// "\\Z" is used to specify the end of file.
	    String text = scan.next();

	    //The lexer is created with the text from the file.
	    //The parser is made from a buffered token stream, made using the lexer.
	    MicroLexer lexer = new MicroLexer(new ANTLRInputStream(text));
	    BufferedTokenStream bft = new BufferedTokenStream(lexer);
	    MicroParser parser = new MicroParser(bft);
	    
	    parser.setErrorHandler(new ErrorHandler());
	    parser.setBuildParseTree(true);
	    ParserRuleContext parseTree = parser.program();
	    System.out.println("Accepted");
	    
	} catch (IOException err) {System.out.println("I/O Exception");}

    }

    public static class ErrorHandler extends DefaultErrorStrategy {
	public void recover (Parser recognizer, RecognitionException e){
	    return;
	    }

	public Token recoverInline (Parser recognizer) {
	    System.out.println("Not Accepted");
	    System.exit(0);
	    return(new CommonToken(0));
	}

	public void reportError(Parser recognizer, RecognitionException e){
	    System.out.println("Not Accepted");
	    System.exit(0);
	}
    }
}
