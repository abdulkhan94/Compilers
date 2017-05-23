import java.util.LinkedList;

public class Func_decl{
    String funcName;
    String funcType;
    IR_NodeList inter_rep;
    

    public Func_decl(String functionName, String functionType){
	funcName = functionName;
	funcType = functionType;
	inter_rep = new IR_NodeList();
    }

    public Func_decl(String functionName, String functionType, IR_NodeList global_rep){
	funcName = functionName;
	funcType = functionType;
	inter_rep = new IR_NodeList();
	
	inter_rep.setDictionaries(global_rep.getVarDictionary(), global_rep.getTypeDictionary());
    }

    public Tiny_NodeList generateTinyCode(){
	Tiny_NodeList tinyCode = new Tiny_NodeList(inter_rep);
	return tinyCode;
    }

    public Tiny_NodeList generateTinyCode(LinkedList<Tiny_Node> var_decl){
	Tiny_NodeList tinyCode = new Tiny_NodeList(inter_rep, var_decl);
	return tinyCode;
    }

    public String getType(){
	return funcType;
    }

    public String getName(){
	return funcName;
    }

    public IR_NodeList getInter_rep(){
	return inter_rep;
    }
}
