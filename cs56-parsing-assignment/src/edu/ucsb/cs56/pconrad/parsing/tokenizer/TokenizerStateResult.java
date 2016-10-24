package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import java.util.*;

/**
 * Holds the result of a state transition between two different states in the tokenizer.
 * This holds two important values:
 * <p><ol>
 * <li>The tokens produced via the state transition
 * <li>The next state to go to
 * </ul><p>
 * Note that it is possible to avoid this class altogether by mutating a common list of
 * tokens somewhere, but this is generally bad practice as it means there is a lot of
 * code which will mutate some common state.
 */
public class TokenizerStateResult {
    // begin constants
    public static final List<Token> EMPTY_LIST =
	Collections.<Token>emptyList();
    // end constants

    // begin instance variables
    private final List<Token> tokens;
    private final TokenizerState nextState;
    // end instance variables

    /**
     * @param tokens The tokens that resulted from this state transition
     * @param nextState The next state to enter; that is, the state we transition to
     */
    public TokenizerStateResult(final List<Token> tokens,
				final TokenizerState nextState) {
	this.tokens = tokens;
	this.nextState = nextState;
    }

    public List<Token> getTokens() { return tokens; }
    public TokenizerState getNextState() { return nextState; }

    /**
     * Convenience static method for creating a <code>TokenizerStateResult</code> with
     * an empty list of tokens.  This is for transitions which changed states but
     * did not produce any tokens in the process, which is surprisingly common.
     *
     * @param nextState The state that we are transitioning to
     * @return A result holding <code>nextState</code>, along with an empty list of tokens
     */
    public static TokenizerStateResult justState(final TokenizerState nextState) {
	return new TokenizerStateResult(EMPTY_LIST, nextState);
    }
}
