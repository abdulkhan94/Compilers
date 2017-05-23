// Define a grammar called Micro

grammar Micro;

/* Literals */
fragment LETTER: ('A'..'Z' | 'a'..'z');
fragment NUMBER: ('0'..'9');

KEYWORDS:
	'PROGRAM' | 'BEGIN' | 'END' | 'FUNCTION' | 'READ' | 'WRITE'
	| 'IF' | 'ELSIF' | 'ENDIF' | 'DO' | 'WHILE' | 'CONTINUE' | 'BREAK'
	| 'RETURN' | 'INT' | 'VOID' | 'STRING' | 'FLOAT'
	| 'TRUE' | 'FALSE';

IDENTIFIER: (LETTER)(LETTER | NUMBER)*;
INTLITERAL: (NUMBER)+;
FLOATLITERAL: (NUMBER)*'.'(NUMBER)+;
STRINGLITERAL: '"'(~('"'))*'"';
COMMENT: '--' ~('\r' | '\n')* -> skip;

OPERATORS:
	':=' | '+' | '-' | '*' | '/' | '=' | '!=' | '<' | '>' | '(' | ')' | ';' | ',' | '<=' | '>=';
	
WHITESPACE:
	( '\n' | '\r' | '\t' | ' ')+ -> skip;

baba: IDENTIFIER;

