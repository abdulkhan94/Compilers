import java.io.*;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.*;
import java.util.*;//Scanner, Map

/**
 *Authors: Harry Koris, Abdullah Khan
 */

class Micro {

    public static boolean checkForDoubles(ArrayList<ArrayList<String>> scopeArray){
	String currentVar;
	boolean error = false;

	for(int i=0; i < scopeArray.size() && !error; i++){
	    ArrayList<String> currentArray = scopeArray.get(i);

	    for(int j=0; j < currentArray.size() && !error; j++){
		String var = currentArray.get(j);
		
		for(int k=j+1; k < currentArray.size() && !error; k++){
		    if(var.equals(currentArray.get(k))){
			System.out.println("DECLARATION ERROR " + var);
			error = true;
		    }
		}
	    }
	}

	/*	String currentVar = "";
	boolean error = false;

	for(int g=0; g < scopeArray.size() && !error; g++){
	    ArrayList<String> currentArray = scopeArray.get(g);


	    for(int h=0; h < currentArray.size() && !error; h++){
		String varName = currentArray.get(h);

		for(int i=g; i < scopeArray.size() && !error; i++){
		    ArrayList<String> innerArray = scopeArray.get(i);
		
		    for(int j=h; j < innerArray.size() && !error; j++){
			
			if(! (i == g && j == h)){
			    String var = innerArray.get(j);
			    boolean a = (var == varName);

			    if(var.equals(varName)){
			        System.out.println("DECLARATION ERROR " + varName);
				error = true;
		    
			    }
			}
		    }
		}
		}
		}*/

	return error;
    }

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

	    int tokType = 10;//This needs to be initialized to !(Lexer.EOF)
	    int blockCounter = 0;
	    String outputLine = "Symbol table GLOBAL\n";
	    
	    //Using a dictionary for the symbol table.
	    //Given a scope as the key, the value is a dictionary for the variables within that scope.
	    //This returned dictionary uses the variable identifier as the key and the value is returned as a string.
	    ArrayList<ArrayList<String>> scopeArray = new ArrayList<ArrayList<String>>();
	    int currentScope = 0;
	    scopeArray.add(currentScope, new ArrayList<String>());

	    //Iterates through each token and identifies each new scope.
	    //Note that once you enter a nested scope, there cannot be any variable declarations after exiting the nested scope.
	    //For each new scope, a dictionary with the variables identifiers and values for that scope is added to scopeDictionary.
	    for(Token tok = lexer.nextToken(); tokType != Lexer.EOF; tok = lexer.nextToken()){
		tokType = tok.getType();
		String tokName = "";
		String tokText = tok.getText();
		int nextTokType;
		ArrayList<String> currentArray;

		switch(tokText){

		case "(":
		    currentArray = scopeArray.get(currentScope);
		    String varType = "";

		    while(!tok.getText().equals(")")){

			tok = lexer.nextToken();
			varType = tok.getText();

			if(!varType.equals("INT") && !varType.equals("FLOAT") && !varType.equals("STRING")){
			    break;
			}
			
			tok = lexer.nextToken();
			String varName = tok.getText();

			outputLine += "\nname " + varName + " type " + varType;
			currentArray.add(varName);

			tok = lexer.nextToken();
		    } 
		    break;

		case "INT": 
		    currentArray = scopeArray.get(currentScope);

		    do{
			tok = lexer.nextToken();
			nextTokType = tok.getType();

			//Grab each variable identifier from the declaration list.
			String nextTokText = tok.getText();
			
			if(nextTokType == 35){
			    outputLine += "\nname " + nextTokText + " type INT";
			    currentArray.add(nextTokText);

			}

		    } while(nextTokType == 35 || nextTokType == 10);//token type is indentifier or a comma
		    break;


		case "FLOAT":
		    tok = lexer.nextToken();
		    nextTokType = tok.getType();
		    currentArray = scopeArray.get(currentScope);
		    
		    //Grab each variable identifier from the declaration list.
		    while(nextTokType == 35 || nextTokType == 10){//token type is indentifier or a comma
			String nextTokText = tok.getText();
			if(nextTokType == 35){
			    outputLine += "\nname " + nextTokText + " type FLOAT";
			    currentArray.add(nextTokText);
			}
			tok = lexer.nextToken();
			nextTokType = tok.getType();

		    }
		    break;

		case "STRING":
		    tok = lexer.nextToken();
		    String varName = tok.getText();
		    tok = lexer.nextToken();
		    tok = lexer.nextToken();
                    String nextTokText = tok.getText();

		    outputLine += "\nname " + varName + " type STRING value " + nextTokText;
		    
		    currentArray = scopeArray.get(currentScope);
		    currentArray.add(varName);
                    break;

		case "FUNCTION"://Add parameter list like testcase 18
		    tok = lexer.nextToken();
		    tok = lexer.nextToken();
		    outputLine += "\n\nSymbol table " + tok.getText();

		    currentScope++;
		    scopeArray.add(currentScope, new ArrayList<String>());
		    break;

		case "IF":
		    blockCounter++;
		    outputLine += "\n\nSymbol table BLOCK " + blockCounter;
		    
		    currentScope++;
		    scopeArray.add(currentScope, new ArrayList<String>());
		    break;

		case "ELSIF":
		    blockCounter++;
		    outputLine += "\n\nSymbol table BLOCK " + blockCounter;

		    currentScope++;
		    scopeArray.add(currentScope, new ArrayList<String>());
		    break;

		case "DO":
		    blockCounter++;
		    outputLine += "\n\nSymbol table BLOCK " + blockCounter;
		    
		    currentScope++;
		    scopeArray.add(currentScope, new ArrayList<String>());
		    break;
		    
		default:
		    break;
		}
	    }

	    boolean error = checkForDoubles(scopeArray);
	    
	    if(!error){
		System.out.println(outputLine);
	    }
	    
	} catch (IOException err) {System.out.println("I/O Exception");}

    }
}