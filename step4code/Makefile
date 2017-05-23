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

test2:
	java -cp lib/antlr.jar:classes/ Micro testcases/test_expr.micro	

test1:
	java -cp lib/antlr.jar:classes/ Micro testcases/step4_testcase3.micro	

.PHONY: all group compiler clean
