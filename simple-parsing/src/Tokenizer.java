import java.util.ArrayList;

public class Tokenizer {

    public static FiniteStateAutomaton makeFSA() {

	FiniteStateAutomaton fsa = new FiniteStateAutomaton();
	fsa.addState(0);
	fsa.addState(1, new IntTokenMaker());
	fsa.addState(2, new PlusTokenMaker());
	fsa.addState(3, new MinusTokenMaker());
	fsa.addState(4, new TimesTokenMaker());
	fsa.addState(5, new DivideTokenMaker());

	// Making all of these little TokenMaker classes is getting tedious.
	// Another way is with anonymous classes
	
	fsa.addState(6, new TokenMaker() {
		public Token makeToken(String s) {
		    return new LParenToken();
		}		
	    } );

	// An even better way is a LambdaFunction
	
	fsa.addState(7, s -> new RParenToken());
	
	for (char c='0'; c<='9'; c++) {
	    fsa.addTransition(c,0,1);
	    fsa.addTransition(c,1,1);
	}
	fsa.addTransition('+',0,2);
	fsa.addTransition('-',0,3);
	fsa.addTransition('*',0,4);
	fsa.addTransition('/',0,5);
	fsa.addTransition('(',0,6);
	fsa.addTransition(')',0,7);

	return fsa;
    }


    public static ArrayList<Token> tokenizeToArrayList (String input) {
	FiniteStateAutomaton fsa = makeFSA();
	fsa.setInput(input);
	    
	ArrayList<Token> tokens = new ArrayList<Token>(); 

	Token t = fsa.nextToken();
	while (t != null) {
	    tokens.add(t);
	    t = fsa.nextToken();
	}

	return tokens;


	
    }
    
    public static Token [] tokenize (String input) {
	ArrayList<Token> tokens = tokenizeToArrayList(input);
	return tokens.toArray(new Token [tokens.size()]);
    }

    public static void main(String [] args) {
	System.out.println( Tokenizer.tokenize(args[0]));
    }
    
}
