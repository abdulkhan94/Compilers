import java.io.*;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.*;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ArrayList;
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

	    //Create the top-level starting rule context
	    parser.setBuildParseTree(true);
	    MicroParser.ProgramContext topLevelContext = parser.program();

	    //Setup the walker and listener.
	    ParseTreeWalker walker = new ParseTreeWalker();
	    AntlrMicroListener listener = new AntlrMicroListener();
	    
	    //The parse tree is walked with our listener, using ProgramContext
	    walker.walk(listener, topLevelContext);

	    //Create the tinyRep for the walker results.
	    LinkedList<Tiny_Node> varDecl = listener.getTiny_varDecl();
	    ArrayList<Func_decl> func_list = listener.getFunc_list();

	    Tiny_NodeList tiny_rep = new Tiny_NodeList();
	    Tiny_NodeList global = func_list.get(0).generateTinyCode(varDecl);
	    tiny_rep.append(global);
	    for(int i = 1; i < func_list.size(); i++){
		Tiny_NodeList tinyPortion = func_list.get(i).generateTinyCode();
		tiny_rep.append(tinyPortion);
	    }

	    //Extract the filename for the .out file
	    String[] pathSplit = args[0].split("/");
	    String[] filenameSplit = pathSplit[pathSplit.length-1].split(".micro");
	    String filename = filenameSplit[0] + ".out";

	    //Create the .out file
	    try{
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		listener.printIR_rep(writer);
		tiny_rep.printTinyList(writer);
		writer.close();
	    } catch (Exception e) {
		System.out.println(e.getMessage());
	    }
	    
	} catch (IOException err) {System.out.println("I/O Exception");}

    }

}
