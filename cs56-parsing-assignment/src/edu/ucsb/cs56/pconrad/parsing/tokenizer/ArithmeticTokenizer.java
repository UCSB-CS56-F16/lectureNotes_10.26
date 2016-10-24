package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import java.util.*;

/**
 * Tokenizer specific to our arithmetic expression language.
 * The biggest difference from the <code>Tokenizer</code> class is that
 * <code>ArithmeticTokenizer</code> passes to <code>Tokenizer</code>'s
 * constructor the tokens in our arithmetic expression language.
 */
public class ArithmeticTokenizer extends Tokenizer {
    // begin constants
    public static final Set<Character> ARITHMETIC_TOKENS =
	setOfChar('(', ')', '+', '-', '*', '/');
    // end constants

    public ArithmeticTokenizer(final String input) {
	super(input, ARITHMETIC_TOKENS);
    }

    /**
     * Given a list of characters, it will return a <code>Set</code>
     * of <code>Character</code> objects corresponding to the provided
     * characters.  For example, if we called this like so:
     *
     * <code>setOfChar('a', 'b', 'c', 'd')</code>
     *
     * ...then this would return a set holding the characters
     * <code>a</code>, <code>b</code>, <code>c</code>, and <code>d</code>.
     *
     * @param chars The characters which should be in the returned set
     * @return A set representation of the provided characters
     */
    public static Set<Character> setOfChar(char... chars) {
	final Set<Character> retval = new HashSet<Character>();
	for (final char c : chars) {
	    retval.add(new Character(c));
	}
	return retval;
    }
}
