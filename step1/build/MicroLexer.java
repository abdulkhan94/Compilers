// Generated from Micro.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		KEYWORDS=1, IDENTIFIER=2, INTLITERAL=3, FLOATLITERAL=4, STRINGLITERAL=5, 
		COMMENT=6, OPERATORS=7, WHITESPACE=8;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LETTER", "NUMBER", "KEYWORDS", "IDENTIFIER", "INTLITERAL", "FLOATLITERAL", 
		"STRINGLITERAL", "COMMENT", "OPERATORS", "WHITESPACE"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "KEYWORDS", "IDENTIFIER", "INTLITERAL", "FLOATLITERAL", "STRINGLITERAL", 
		"COMMENT", "OPERATORS", "WHITESPACE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MicroLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Micro.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\n\u00be\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4}\n\4"+
		"\3\5\3\5\3\5\7\5\u0082\n\5\f\5\16\5\u0085\13\5\3\6\6\6\u0088\n\6\r\6\16"+
		"\6\u0089\3\7\7\7\u008d\n\7\f\7\16\7\u0090\13\7\3\7\3\7\6\7\u0094\n\7\r"+
		"\7\16\7\u0095\3\b\3\b\7\b\u009a\n\b\f\b\16\b\u009d\13\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\7\t\u00a5\n\t\f\t\16\t\u00a8\13\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\5\n\u00b6\n\n\3\13\6\13\u00b9\n\13\r\13\16\13\u00ba"+
		"\3\13\3\13\2\2\f\3\2\5\2\7\3\t\4\13\5\r\6\17\7\21\b\23\t\25\n\3\2\b\4"+
		"\2C\\c|\3\2$$\4\2\f\f\17\17\6\2,-//\61\61??\6\2*+..=>@@\5\2\13\f\17\17"+
		"\"\"\u00db\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\3\27\3\2\2\2\5\31\3\2\2\2\7"+
		"|\3\2\2\2\t~\3\2\2\2\13\u0087\3\2\2\2\r\u008e\3\2\2\2\17\u0097\3\2\2\2"+
		"\21\u00a0\3\2\2\2\23\u00b5\3\2\2\2\25\u00b8\3\2\2\2\27\30\t\2\2\2\30\4"+
		"\3\2\2\2\31\32\4\62;\2\32\6\3\2\2\2\33\34\7R\2\2\34\35\7T\2\2\35\36\7"+
		"Q\2\2\36\37\7I\2\2\37 \7T\2\2 !\7C\2\2!}\7O\2\2\"#\7D\2\2#$\7G\2\2$%\7"+
		"I\2\2%&\7K\2\2&}\7P\2\2\'(\7G\2\2()\7P\2\2)}\7F\2\2*+\7H\2\2+,\7W\2\2"+
		",-\7P\2\2-.\7E\2\2./\7V\2\2/\60\7K\2\2\60\61\7Q\2\2\61}\7P\2\2\62\63\7"+
		"T\2\2\63\64\7G\2\2\64\65\7C\2\2\65}\7F\2\2\66\67\7Y\2\2\678\7T\2\289\7"+
		"K\2\29:\7V\2\2:}\7G\2\2;<\7K\2\2<}\7H\2\2=>\7G\2\2>?\7N\2\2?@\7U\2\2@"+
		"A\7K\2\2A}\7H\2\2BC\7G\2\2CD\7P\2\2DE\7F\2\2EF\7K\2\2F}\7H\2\2GH\7F\2"+
		"\2H}\7Q\2\2IJ\7Y\2\2JK\7J\2\2KL\7K\2\2LM\7N\2\2M}\7G\2\2NO\7E\2\2OP\7"+
		"Q\2\2PQ\7P\2\2QR\7V\2\2RS\7K\2\2ST\7P\2\2TU\7W\2\2U}\7G\2\2VW\7D\2\2W"+
		"X\7T\2\2XY\7G\2\2YZ\7C\2\2Z}\7M\2\2[\\\7T\2\2\\]\7G\2\2]^\7V\2\2^_\7W"+
		"\2\2_`\7T\2\2`}\7P\2\2ab\7K\2\2bc\7P\2\2c}\7V\2\2de\7X\2\2ef\7Q\2\2fg"+
		"\7K\2\2g}\7F\2\2hi\7U\2\2ij\7V\2\2jk\7T\2\2kl\7K\2\2lm\7P\2\2m}\7I\2\2"+
		"no\7H\2\2op\7N\2\2pq\7Q\2\2qr\7C\2\2r}\7V\2\2st\7V\2\2tu\7T\2\2uv\7W\2"+
		"\2v}\7G\2\2wx\7H\2\2xy\7C\2\2yz\7N\2\2z{\7U\2\2{}\7G\2\2|\33\3\2\2\2|"+
		"\"\3\2\2\2|\'\3\2\2\2|*\3\2\2\2|\62\3\2\2\2|\66\3\2\2\2|;\3\2\2\2|=\3"+
		"\2\2\2|B\3\2\2\2|G\3\2\2\2|I\3\2\2\2|N\3\2\2\2|V\3\2\2\2|[\3\2\2\2|a\3"+
		"\2\2\2|d\3\2\2\2|h\3\2\2\2|n\3\2\2\2|s\3\2\2\2|w\3\2\2\2}\b\3\2\2\2~\u0083"+
		"\5\3\2\2\177\u0082\5\3\2\2\u0080\u0082\5\5\3\2\u0081\177\3\2\2\2\u0081"+
		"\u0080\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2"+
		"\2\2\u0084\n\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0088\5\5\3\2\u0087\u0086"+
		"\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"\f\3\2\2\2\u008b\u008d\5\5\3\2\u008c\u008b\3\2\2\2\u008d\u0090\3\2\2\2"+
		"\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0091\3\2\2\2\u0090\u008e"+
		"\3\2\2\2\u0091\u0093\7\60\2\2\u0092\u0094\5\5\3\2\u0093\u0092\3\2\2\2"+
		"\u0094\u0095\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\16"+
		"\3\2\2\2\u0097\u009b\7$\2\2\u0098\u009a\n\3\2\2\u0099\u0098\3\2\2\2\u009a"+
		"\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2"+
		"\2\2\u009d\u009b\3\2\2\2\u009e\u009f\7$\2\2\u009f\20\3\2\2\2\u00a0\u00a1"+
		"\7/\2\2\u00a1\u00a2\7/\2\2\u00a2\u00a6\3\2\2\2\u00a3\u00a5\n\4\2\2\u00a4"+
		"\u00a3\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2"+
		"\2\2\u00a7\u00a9\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00aa\b\t\2\2\u00aa"+
		"\22\3\2\2\2\u00ab\u00ac\7<\2\2\u00ac\u00b6\7?\2\2\u00ad\u00b6\t\5\2\2"+
		"\u00ae\u00af\7#\2\2\u00af\u00b6\7?\2\2\u00b0\u00b6\t\6\2\2\u00b1\u00b2"+
		"\7>\2\2\u00b2\u00b6\7?\2\2\u00b3\u00b4\7@\2\2\u00b4\u00b6\7?\2\2\u00b5"+
		"\u00ab\3\2\2\2\u00b5\u00ad\3\2\2\2\u00b5\u00ae\3\2\2\2\u00b5\u00b0\3\2"+
		"\2\2\u00b5\u00b1\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\24\3\2\2\2\u00b7\u00b9"+
		"\t\7\2\2\u00b8\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba"+
		"\u00bb\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\b\13\2\2\u00bd\26\3\2\2"+
		"\2\r\2|\u0081\u0083\u0089\u008e\u0095\u009b\u00a6\u00b5\u00ba\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}