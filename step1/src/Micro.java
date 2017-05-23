import java.io.*;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.*;
import java.util.Scanner;

/**
 *Authors: Harry Koris, Abdullah Khan
 */

class Micro {

    private static void checkIdentifierLength (String text) throws IOException{
	if(text.length() > 30){ throw new IOException();}
    }

    private static void checkStringLiteralLength (String text) throws IOException{
	if(text.length() > 80){ throw new IOException();}
    }

    public static void main(String[] args) {
	

	try{
	    //Uses a scanner object and makes one token out of the whole object. Then stores it to a string.
	    File fil = new File(args[0]);
	    Scanner scan = new Scanner(fil);
	    scan = scan.useDelimiter("\\Z");// "\\Z" is used to specify the end of file.
	    String text = scan.next();

	    //The lexer is created with the text from the file.
	    MicroLexer lexer = new MicroLexer(new ANTLRInputStream(text));
	    BufferedTokenStream bft = new BufferedTokenStream(lexer);

	    int tokType = 10;//This needs to be initialized to !(Lexer.EOF)

	    //Iterates through each token, finds the type, and prints the according output.
	    for(Token tok = lexer.nextToken(); tokType != Lexer.EOF; tok = lexer.nextToken()){
		tokType = tok.getType();
		String tokName = "";

		if(tokType == 1){
		    tokName = "KEYWORD";
		}
		if(tokType == 2){
		    tokName = "IDENTIFIER";
		    checkIdentifierLength(tok.getText());//Max 30 chars
		}
		if(tokType == 3){
		    tokName = "INTLITERAL";
		}
		if(tokType == 4){
		    tokName = "FLOATLITERAL";
		}
		if(tokType == 5){
		    tokName = "STRINGLITERAL";
		    checkStringLiteralLength(tok.getText());//Max 80 chars
		}
		if(tokType == 6){
		    tokName = "COMMENT";
		}		
		if(tokType == 7){
		    tokName = "OPERATOR";
		}
		if(tokType == 8){
		    tokName = "WHITESPACE";
		}
	
		if(tokName != "COMMENT" && tokName != "WHITESPACE" && tokType != Lexer.EOF){
		    System.out.println("Token Type: " + tokName);
		    System.out.println("Value: " + tok.getText());
		}
	    }
	    
	} catch (IOException err) {System.out.println("I/O Exception");}

    }
}
