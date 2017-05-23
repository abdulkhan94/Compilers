import java.io.*;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.*;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.PrintWriter;

/**
 * All variables are defined globally, none are defined within main.
 * There is only the main function with no calls to other functions.
 * Loops and if statement are NOT included.
 *
 * Get a list of variable declarations. These are decalared as, "var a", for the tinycode but not used for the IR code.
 *     RuleName = var_decl
 * Go line by line grabbing each assignment expression and READ/WRTITE expressions.
 * Make sure to preserve the order of in which the assignments occur as well as the READ/WRITE occurances.
 *     Rule for READ/WRITE = read_stmt / write_stmt
 *     Rule for variable expressions = assign_expr
 *
 * Note: The order should be preserved by the walker since the functions for each rule execute as the walker comes across each section designated by the rule
 *
 *
 * How am I going to build the IR representation?
 * Rules where the components are other rules will require a separate recursive function in order to go through each rule layer.
 * The purpose of the separate function will be to parse the parts of the context component that we need in order to create our instruction.
 * Variable declarations should not be part of the IR code, so we can add them to the linked list but not print them in the IR code.
 *      The declarations will always come first since none are declared within main.
 */

public class AntlrMicroListener extends MicroBaseListener {
    public static final String MUL = "*";
    public static final String DIV = "/";
    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String LPAR = "(";
    public static final String RPAR = ")";
 
    private LinkedList<IR_Node> inter_rep;
    private LinkedList<Tiny_Node> tiny_varDecl;
    private ArrayList<String> expr_comp;//Note that this is being used as a stack
    private HashMap<String, String> typeDictionary;
    private int regCounter;


    public AntlrMicroListener(){
	inter_rep = new LinkedList<IR_Node>();
	inter_rep.add(new IR_Node("IR code", "", "", ""));
	tiny_varDecl = new LinkedList<Tiny_Node>();
	expr_comp = new ArrayList<String>();
	typeDictionary = new HashMap<String, String>();
	regCounter = 1;
    }
    
    public LinkedList<IR_Node> getInterRep(){
	return inter_rep;
    }

    public LinkedList<Tiny_Node> getTiny_varDecl(){
	return tiny_varDecl;
    }

    public void printIR_rep(PrintWriter writer){
	for(int i = 0; i < inter_rep.size(); i++){
	    IR_Node node = inter_rep.get(i);
	    node.print(writer);
	}
    }

    public void printExpr_comp(){
	for(int i = 0; i < expr_comp.size(); i++){
	    System.out.println(expr_comp.get(i));
	}
    }
    
    @Override
    public void exitVar_decl(MicroParser.Var_declContext ctx){
	MicroParser.Id_listContext varNames = ctx.id_list();
	MicroParser.Var_typeContext varType = ctx.var_type();
	String[] varList = varNames.getText().split(",");
	
	for(int i = 0; i < varList.length; i++){
	    Tiny_Node node = new Tiny_Node("var", varList[i], "");
	    tiny_varDecl.add(node);

	    typeDictionary.put(varList[i], varType.getText());
	}

	return;
    }


    @Override
    public void exitRead_stmt(MicroParser.Read_stmtContext ctx){
	MicroParser.Id_listContext varList = ctx.id_list();
	//use createReadOpNode()


    }

    @Override
    public void exitWrite_stmt(MicroParser.Write_stmtContext ctx){
	String varList = ctx.id_list().getText();

	//use createWriteOpNode()
	String[] ids = varList.split(",");

	for(int i = 0; i < ids.length; i++){
	    String dest = getCurrentRegister();
	    createWriteIR_Node(ids[i]);
	}

    }

    public void enterPrimary(MicroParser.PrimaryContext ctx){
	String temp = ctx.getText();
        String firstChar = String.valueOf(temp.charAt(0));
	if(firstChar.equals(LPAR)){
	    expr_comp.add(LPAR);
	} else {
	    expr_comp.add(temp);
	}
    }

    public void exitPrimary(MicroParser.PrimaryContext ctx){
	String temp = ctx.getText();
	String lastChar = String.valueOf(temp.charAt(temp.length() - 1));
	if(lastChar.equals(RPAR)){
	    expr_comp.add(RPAR);
	}
    }

    public void exitAddop(MicroParser.AddopContext ctx){
	expr_comp.add(ctx.getText());
	
    }

    public void exitMulop(MicroParser.MulopContext ctx){
	expr_comp.add(ctx.getText());	
	
    }

    @Override
    public void enterAssign_expr(MicroParser.Assign_exprContext ctx){
	String lhs = ctx.id().getText();
	MicroParser.ExprContext rhs = ctx.expr();
	
	expr_comp.add(lhs);
       	
    }

    @Override
    public void exitAssign_expr(MicroParser.Assign_exprContext ctx){    
	String lhs = ctx.id().getText();	

	//The arraylist has been filled with the expression, now create the IR_Nodes
	createExpressionIR_nodes();
    }

    private ArrayList<String> grabSubExpression(ArrayList<String> expression){
	ArrayList<String> subExp = new ArrayList<String>();
	
	for(int i = 0; !expression.isEmpty(); i++){
	    String temp = expression.get(0);
	    expression.remove(0);
	    
	    if(temp.equals(RPAR)){
		break;
	    }
	    
	    subExp.add(temp);
	}

	return subExp;
    }

    //Returns the register location that the result of computeStack is stored in
    private String executeStack(Stack<String> computeStack){
	//var2 is grabbed first b/c expression is pushed left -> right and popped right -> left
	String var2 = computeStack.pop();
	String destination = getCurrentRegister();

	//System.out.println("Execute Stack: " + var2);

	if(computeStack.empty()){
	    createStoreIR_Node(var2, destination);

	    return destination;
	}

	String op, var1;
	while(!computeStack.empty()){
	    op = computeStack.pop();
	    var1 = computeStack.pop();
	    
	    /*System.out.println("Execute Stack: " + op);
	    System.out.println("Execute Stack: " + var1);
	    */

	    //destination is not updated for more efficient register use
	    destination = getCurrentRegister();

	    destination = createOpIR_Node(var1, var2, op, destination);		
	    var2 = destination;

	}

	return destination;
	
    }

    /* Do I neeed to grab the var and op from outside of the while loop???
     *
     */
    private String traverseExpression(ArrayList<String> expression){
	Stack<String> computeStack = new Stack<String>();
	String destination = null;

	while(!expression.isEmpty()){
	    /*System.out.println("\n");
	    for(int i = 0; i < expression.size(); i++){
		System.out.println("Before var grab: " + expression.get(i));
		}*/

	    String nextVar = expression.get(0);
	    expression.remove(0);
	    
	    //Terminating Condition for direct assignment or end of an expression
	    if(expression.isEmpty()){
		computeStack.push(nextVar);
		return executeStack(computeStack);
	    }

	    /*System.out.println("\n");
	    for(int i = 0; i < expression.size(); i++){
		System.out.println("Before SubExp check: " + expression.get(i));
		}*/

	    //A recursive call executes here when a sub-expression is found
	    //Either the result register of a subExpression or the value of nextVar is pushed onto the compute stack.
	    if(nextVar.equals(LPAR)){
		ArrayList<String> subExp = grabSubExpression(expression);
		destination = traverseExpression(subExp);
		computeStack.push(destination);

		//For the case where the expression ends with a subexpression
		if(expression.isEmpty()){
		    return destination;
		}
	    } else {
		computeStack.push(nextVar);
	    }
	    
	    /*System.out.println("\n");
	    for(int i = 0; i < expression.size(); i++){
		System.out.println("Before nextOp grab: " + expression.get(i));
		}*/

	    //The stack will always have at least two more items if it is not empty after popping a variable
	    String nextOp = expression.get(0);
	    expression.remove(0);

	    if( (nextOp.equals(ADD) || nextOp.equals(SUB)) && computeStack.size() != 1){
		destination = executeStack(computeStack);
		computeStack.push(destination);
	    }

	    computeStack.push(nextOp);

	}

	return destination;
	
    }

    //Note that removing an item from an array list causes the others to shift over.
    //Adjust your index correctly when removing items.
    //If we are always grabbing and removing the first element this can behave as a stack.
    private void createExpressionIR_nodes(){
	String lhs = expr_comp.get(0);
	expr_comp.remove(0);

	String rhsResult = traverseExpression(expr_comp);

	createStoreIR_Node(rhsResult, lhs);//rhs stored to the lhs
    }

    //Note: Each time you create an IR_Node you just pass the destination reg w/ the value of currentReg.
    //The IR_Node creation automatically increments the currentReg.

    private String createOpIR_Node(String value1, String value2, String op, String destination){
	String val1Type = checkValType(value1);
	String val2Type = checkValType(value2);
	boolean isVal1Literal = false;
	boolean isVal2Literal = false;

	//Check if either value is a literal and if so store it to a temporary register first.
	if(val1Type.equals("FLOATLITERAL") || val1Type.equals("INTLITERAL")){
	    createStoreIR_Node(value1, destination);
	    value1 = destination;//Set to the destination register for the opcode.
	    isVal1Literal = true;
	}
	
	if(val2Type.equals("FLOATLITERAL") || val2Type.equals("INTLITERAL")){
	    if(isVal1Literal){
		destination = getCurrentRegister();
	    }
	    createStoreIR_Node(value2, destination);
	    value2 = destination;
	    isVal2Literal = true;
	}

	if(isVal1Literal || isVal2Literal){//Update the destination if either value was a literal
	    destination = getCurrentRegister();
	}
	if(val1Type.equals("FLOAT") || val2Type.equals("FLOAT") || val1Type.equals("FLOATLITERAL") || val2Type.equals("FLOATLITERAL")){
	    //value1 and value2 are preset to their destination register if they are literals
	    createFloatOpIR_Node(value1, value2, op, destination);
	} else {
	    createIntOpIR_Node(value1, value2, op, destination);
	}

	return destination;
    }


    private void createIntOpIR_Node(String value1, String value2, String op, String destination){
	IR_Node node = null;

	switch(op){
	case MUL:
	    node = new IR_Node("MULTI", value1, value2, destination);
	    break;
	    
	case DIV:
	    node = new IR_Node("DIVI", value1, value2, destination);
	    break;

	case ADD:
	    node = new IR_Node("ADDI", value1, value2, destination);
	    break;

	case SUB:
	    node = new IR_Node("SUBI", value1, value2, destination);
	    break;

	default: 
	    break;
	    
	}
	
	//Add to the linked list
	inter_rep.add(node);

	//Add to the dictionary
	addRegToTypeDictionary(destination, "INT");//This is where regCounter is incremented.
    }

    private void createFloatOpIR_Node(String value1, String value2, String op, String destination){
	IR_Node node = null;

	switch(op){
	case MUL:
	    node = new IR_Node("MULTF", value1, value2, destination);
	    break;
	    
	case DIV:
	    node = new IR_Node("DIVF", value1, value2, destination);
	    break;

	case ADD:
	    node = new IR_Node("ADDF", value1, value2, destination);
	    break;

	case SUB:
	    node = new IR_Node("SUBF", value1, value2, destination);
	    break;

	default:
	    break;
	    
	}

	//Add to the linked list
	inter_rep.add(node);

	//Add to the dictionary
	addRegToTypeDictionary(destination, "FLOAT");
    }

    private void createStoreIR_Node(String value, String destination){
	String type = checkValType(value);
	
	if(type.equals("INT") || type.equals("INTLITERAL")){
	    IR_Node node = new IR_Node("STOREI", value, "", destination);
	    //Add to the linked list
	    inter_rep.add(node);
	    //Add to the dictionary
	    addRegToTypeDictionary(destination, "INT");
	} else {
	    IR_Node node = new IR_Node("STOREF", value, "", destination);
	    //Add to the linked list
	    inter_rep.add(node);
	    //Add to the dictionary
	    addRegToTypeDictionary(destination, "FLOAT");
	}

    }

    private void createWriteIR_Node(String value){
	String type = checkValType(value);
	
	if(type.equals("INT") || type.equals("INTLITERAL")){
	    IR_Node node = new IR_Node("WRITEI", value, "", "");
	    //Add to the linked list
	    inter_rep.add(node);
	} else {
	    IR_Node node = new IR_Node("WRITEF", value, "", "");
	    //Add to the linked list
	    inter_rep.add(node);
	}

    }

    private void addRegToTypeDictionary(String value, String type){
	//Check if value is a reg
	boolean isReg = false;
	if(String.valueOf(value.charAt(0)).equals("$")){//destination is a register
	    isReg = true;
	} else{
	    return;
	}

	//Add to dictionay
	//Note that it will overwrite existing keys.
	if(isReg){
	    typeDictionary.put(value, type);
	}


	//Register Count is incremented
	regCounter++;
    }

    private String checkValType(String value){
	String type = typeDictionary.get(value);

	if(type == null){
	    if(value.matches("[0-9]*\\.[0-9]+")){
		return "FLOATLITERAL";
	    } else if(value.matches("[0-9]+")){
		return "INTLITERAL";
	    } else if(String.valueOf(value.charAt(0)).equals("$")){
		return typeDictionary.get(value);//Lookup the type for the register
	    } else {
		System.out.println("ERROR: Value: " + value + "\nNot found to have a type!!!");
		return type;
	    }
	} else{
	    return type;
	}
	
    }

    private String getCurrentRegister(){
	return "$T" + regCounter;
    }
    
}
