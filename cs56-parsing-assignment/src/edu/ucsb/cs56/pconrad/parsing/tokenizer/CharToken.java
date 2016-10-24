package edu.ucsb.cs56.pconrad.parsing.tokenizer;

/**
 * Represents a token which can be represented with a single character,
 * which is not an integer.  For representing integers, see <code>IntToken</code>.
 * <code>CharToken</code> is appropriate for parentheses and arithmetic operators.
 * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.IntToken
 */
public class CharToken implements Token {
    // begin instance variables
    private final char c;
    // end instance variables

    /**
     * @param c The single character held within this token
     */
    public CharToken(final char c) {
        this.c = c;
    }

    public boolean equals(final Object other) {
        // intentionally don't go through charTokenValue to avoid
        // spurious checked exception error
        return (other instanceof CharToken &&
                ((CharToken)other).c == c);
    }

    public int hashCode() {
        return (int)c;
    }

    public String toString() {
        return Character.toString(c);
    }

    /**
     * Calls <code>visitor</code>'s <code>visitCharToken</code> method, passing
     * along the character held within this token (which was provided in the
     * constructor).
     * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.TokenVisitor#visitCharToken
     */
    public <A, T extends Throwable> A accept(final TokenVisitor<A, T> visitor) throws T {
	return visitor.visitCharToken(c);
    }
} // CharToken
