package edu.ucsb.cs56.pconrad.parsing.tokenizer;

import java.util.List;

/**
 * Interface which states in the tokenizer must implement.
 * This is per the usual definition of the State pattern.
 */
public interface TokenizerState {
    /**
     * Given that the current character in the input is <code>curChar</code>,
     * enter the next state, possibly producing tokens along the way.
     *
     * @param curChar The current character we observe
     * @return A result encapsulating the next state along with any tokens
     *         produced during the state transition
     * @throws TokenizerException If tokenization failed for whatever
     *         reason, as with encountering an unexpected character
     */
    public TokenizerStateResult nextState(char curChar) throws TokenizerException;

    /**
     * Indicates that there are no more characters in the input.
     * This is necessary in the case when the input ends with an integer
     * like <code>123</code>; we can't produce the integer until we see all
     * the digits, but if the input ends on <code>3</code>, then there are no
     * further digits to feed into <code>nextState</code>.  As such, this
     * method is called to actually create the integer token holding the
     * decimal value <code>123</code>.
     *
     * @return A possibly empty list of tokens produced by indicating we are
     *         at the end of the input
     * @throws TokenizerException If tokenization failed for whatever reason
     */
    public List<Token> atInputEnd() throws TokenizerException;
}
