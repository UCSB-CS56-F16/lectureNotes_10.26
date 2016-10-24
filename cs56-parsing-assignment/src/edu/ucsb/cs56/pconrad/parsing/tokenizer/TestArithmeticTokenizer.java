package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import static edu.ucsb.cs56.pconrad.parsing.DefaultInterpreterInterface.DEFAULT;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Tests <code>ArithmeticTokenizer</code>'s <code>tokenize</code>
 * method.  Ultimately, this is testing to see if we can go
 * from a raw input string into a list of <code>Token</code>s.
 * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.ArithmeticTokenizer#tokenize
 * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.Token
 */
public class TestArithmeticTokenizer {
    // begin instance variables
    private final TokenFactory tf;
    // end instance variables

    public TestArithmeticTokenizer() {
        tf = DefaultTokenFactory.DEFAULT;
    }

    /**
     * Convenience method to tokenize the given input.
     */
    public static Token[] tokenize(final String input)
	throws TokenizerException {
	return DEFAULT.tokenize(input).toArray(new Token[0]);
    }

    /**
     * Like <code>tokenize</code>, except it will catch
     * <code>TokenizerException</code>s so that we don't need to
     * keep annotating that tests can throw this exception.
     * Internally, if <code>TokenizerException</code> is thrown,
     * this will trigger test failure.
     * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.TestArithmeticTokenizer#tokenize
     * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.TokenizerException
     */
    public static Token[] tokenizeNoException(final String input) {
        Token[] retval = null;
        try {
	    retval = tokenize(input);
        } catch (TokenizerException e) {
            fail("Unexpected tokenizer exception: " + e.toString());
        }

        assert(retval != null);
        return retval;
    }

    @Test
    public void testOneToken() {
        assertArrayEquals(new Token[] { tf.makePlusToken() },
                          tokenizeNoException("+"));
    }

    @Test
    public void testTwoSameTokens() {
        assertArrayEquals(new Token[] { tf.makePlusToken(),
                                        tf.makePlusToken() },
                          tokenizeNoException("++"));
    }

    @Test
    public void testSingleWhitespace() {
        assertArrayEquals(new Token[] { },
                          tokenizeNoException(" "));
    }

    @Test
    public void testSingleDigit() {
        assertArrayEquals(new Token[] { tf.makeIntToken(1) },
                          tokenizeNoException("1"));
    }

    @Test
    public void testTwoDigit() {
        assertArrayEquals(new Token[] { tf.makeIntToken(12) },
                          tokenizeNoException("12"));
    }

    // BEGIN TESTS FOR INVALID INPUTS
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testInvalidSingleToken()
        throws TokenizerException {
        thrown.expect(TokenizerException.class);
        tokenize("c");
    }
} // TestArithmeticTokenizer
