package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import java.util.*;

/**
 * Relatively generic tokenizer code, which can tokenize different
 * languages depending on the provided parameters to the constructor.
 * Assumes that whitespace can be used to separate tokens, but whitespace
 * in and of itself is not significant (therefore, whitespace has no
 * corresponding token).  Also tied closely to the <code>CharToken</code>
 * class; when the tokenizer recognizes a specified character,
 * it will create an instance of the <code>CharToken</code> class containing
 * the recognized character.  Internally uses the State pattern.
 * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.CharToken
 */
public class Tokenizer {
    // begin instance variables
    private final String input;
    private final Set<Character> recognizedTokens;
    // end instance variables

    /**
     * @param input The input string to tokenize
     * @param recognizedTokens The characters which should be treated as tokens
     */
    public Tokenizer(final String input,
		     final Set<Character> recognizedTokens) {
	this.input = input;
	this.recognizedTokens = recognizedTokens;
    }

    /**
     * Actually tokenizes the input string, which was specified in the constructor.
     * @return The tokens corresponding to the input string
     * @throws TokenizerException If the input string couldn't be tokenized.
     *         This could happen, for instance, if the input string contained
     *         a non-whitespace character which was not in <code>recognizedTokens</code>.
     */
    public ArrayList<Token> tokenize() throws TokenizerException {
	final ArrayList<Token> tokens = new ArrayList<Token>();
	TokenizerState state = new InitialTokenizerState(recognizedTokens);
	for(int pos = 0; pos < input.length(); pos++) {
	    final TokenizerStateResult cur = state.nextState(input.charAt(pos));
	    tokens.addAll(cur.getTokens());
	    state = cur.getNextState();
	}
	tokens.addAll(state.atInputEnd());
	return tokens;
    }
} // Tokenizer
