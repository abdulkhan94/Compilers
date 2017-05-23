import java.io.*;
import java.io.PrintWriter;

public class Tiny_Node{

    private String opcode;
    private String op1;
    private String op2;

    public Tiny_Node(String operation, String operand1, String operand2){
	opcode = operation;
	op1 = operand1;
	op2 = operand2;
    }

    public void print(PrintWriter writer){
	writer.println(opcode + " " + op1 + " " + op2);
    }
}
