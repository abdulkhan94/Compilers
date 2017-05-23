LIB_ANTLR := lib/antlr.jar
ANTLR_SCRIPT := Micro.g4

all: group compiler

group:
	@echo "Harry Koris - hkoris, Abdullah Khan - khan85"
compiler:
	rm -rf build
	mkdir build
	java -cp $(LIB_ANTLR) org.antlr.v4.Tool -o build $(ANTLR_SCRIPT)
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java build/*.java
clean:
	rm -rf classes build

testFma:
	java -cp lib/antlr.jar:classes/ Micro testcases/step6_testcases/fma/fma.micro

testFactorial2:
	java -cp lib/antlr.jar:classes/ Micro testcases/step6_testcases/factorial2.micro

testFibonacci2:
	java -cp lib/antlr.jar:classes/ Micro testcases/step6_testcases/fibonacci2.micro

testAdv:
	java -cp lib/antlr.jar:classes/ Micro testcases/testcases_step5/input/test_adv.micro 

testDoWhile:
	java -cp lib/antlr.jar:classes/ Micro testcases/testcases_step5/input/test_dowhile.micro 

testIf:
	java -cp lib/antlr.jar:classes/ Micro testcases/testcases_step5/input/test_if.micro 

test4:
	java -cp lib/antlr.jar:classes/ Micro testcases/testcases_step5/input/step4_testcase2.micro	

test3:
	java -cp lib/antlr.jar:classes/ Micro testcases/testcases_step5/input/step4_testcase.micro	

test2:
	java -cp lib/antlr.jar:classes/ Micro testcases/test_expr.micro	

test1:
	java -cp lib/antlr.jar:classes/ Micro testcases/step4_testcase3.micro	

.PHONY: all group compiler clean
