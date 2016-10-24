package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import java.util.*;

/**
 * The state we are in while we are parsing an integer, which may be spread
 * across multiple characters.  For example, <code>123</code> is a single
 * integer and should be represented with a single <code>IntToken</code>, but
 * nonetheless it takes up three characters.
 * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.IntToken
 */
public class IntTokenizerState implements TokenizerState {
    // begin instance variables
    private final ArrayList<Integer> digits;
    private InitialTokenizerState initialState;
    // end instance variables

    /**
     * @param startDigit The digit that we start on, which is assumed to
     *        be in the range 0-9
     * @param initialState The initial state for the tokenizer, as per the
     *        State pattern.  This is necessary because when we are done
     *        parsing the input integer, we must return to the initial state
     */
    public IntTokenizerState(final char startDigit,
			     final InitialTokenizerState initialState) {
	digits = new ArrayList<Integer>();
	addDigit(startDigit);
	this.initialState = initialState;
    }

    protected void addDigit(final char digit) {
	assert(Character.isDigit(digit));
	digits.add(new Integer(toDigit(digit)));
    }

    /**
     * Converts the given character <code>c</code> to a digit.
     * <code>c</code> must be in the range 0-9, or else an assertion
     * will be tripped.
     *
     * @param c The character to convert to an integer
     * @return The integer value of the character, which will be in the range
     *         0-9, inclusive
     */
    public static int toDigit(final char c) {
	assert(Character.isDigit(c));
	switch (c) {
	case '0':
	    return 0;
	case '1':
	    return 1;
	case '2':
	    return 2;
	case '3':
	    return 3;
	case '4':
	    return 4;
	case '5':
	    return 5;
	case '6':
	    return 6;
	case '7':
	    return 7;
	case '8':
	    return 8;
	case '9':
	    return 9;
	default:
	    assert(false);
	    return -1;
	}
    }

    /**
     * Will convert the given sequence of integers into a single integer.
     * Assumes that each integer in the sequence is in the range 0-9, inclusive.
     * For example, if given the list <code>[1, 2, 3]</code>, this will return
     * the single integer <code>123</code>.
     * <p>An <code>ArrayList</code> is specifically used as opposed to the
     * more generic <code>List</code>, as this requires constant-time random
     * access for efficiency.
     */
    public static int digitsToInt(final ArrayList<Integer> digits) {
	int retval = 0;
	int multiplier = 1;
	for(int x = digits.size() - 1; x >= 0; x--) {
	    retval += digits.get(x) * multiplier;
	    multiplier *= 10;
	}
	return retval;
    }

    /**
     * Returns a list containing a single <code>IntToken</code> representing
     * the digits held within.
     * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.IntToken
     */
    private List<Token> makeBaseRetvalTokens() {
	final List<Token> retvalTokens = new ArrayList<Token>();
	retvalTokens.add(new IntToken(digitsToInt(digits)));
	return retvalTokens;
    }

    /**
     * Transitions to the next state under the given character
     * <code>curChar</code>.  Intuitively, if <code>curChar</code>
     * is a digit, it will add it to the digits for the <code>IntToken</code>
     * which is being created.  If <code>curChar</code> is not a digit, then
     * it will bundle up the digits into a single <code>IntToken</code>, and
     * make a transition using the initial state (passed in the constructor)
     * and <code>curChar</code>.
     * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.IntToken
     */
    public TokenizerStateResult nextState(final char curChar)
	throws TokenizerException {
	if (Character.isDigit(curChar)) {
	    addDigit(curChar);
	    return TokenizerStateResult.justState(this);
	} else {
	    final List<Token> retvalTokens = makeBaseRetvalTokens();
	    final TokenizerStateResult forward = initialState.nextState(curChar);
	    retvalTokens.addAll(forward.getTokens());
	    return new TokenizerStateResult(retvalTokens, forward.getNextState());
	}
    }

    /**
     * Will bundle up all the digits seen so far into a single
     * <code>IntToken</code>.
     * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.IntToken
     */     
    public List<Token> atInputEnd() throws TokenizerException {
	return makeBaseRetvalTokens();
    }
} // IntTokenizerState

