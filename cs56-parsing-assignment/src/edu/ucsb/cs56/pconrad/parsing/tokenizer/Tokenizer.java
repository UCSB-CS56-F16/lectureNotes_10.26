package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import java.util.ArrayList;

public class Tokenizer {

    private final String input;

    public Tokenizer(String input) {
	this.input = input;
    }
    
    public static FiniteStateAutomaton makeFSA() {

	FiniteStateAutomaton fsa = new FiniteStateAutomaton();
	fsa.addState(0);
	fsa.addState(1, s -> new IntToken(s) );
	fsa.addState(2, s -> new PlusToken() );
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

	fsa.addTransition(' ',0,0);
	fsa.addTransition('\t',0,0);
	fsa.addTransition('\n',0,0);
	fsa.addTransition('\r',0,0);
		
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


    public ArrayList<Token> tokenize () {
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
    
    public static Token [] tokenizeToArray (String input) {
	Tokenizer t = new Tokenizer(input);
	ArrayList<Token> tokens = t.tokenize();
	return tokens.toArray(new Token [tokens.size()]);
    }

    public static void main(String [] args) {
	String input="2+2";
	if (args.length>0) input = args[0];
	System.out.println( new Tokenizer(input).tokenize());
    }
    
}
