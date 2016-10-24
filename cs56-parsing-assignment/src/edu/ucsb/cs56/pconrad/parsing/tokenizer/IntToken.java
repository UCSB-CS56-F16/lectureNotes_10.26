package edu.ucsb.cs56.pconrad.parsing.tokenizer;

/**
 * Represents an integer token, which contains a specific integer
 */
public class IntToken implements Token {
    // begin instance variables
    private final int value;
    // end instance variables

    /**
     * @param value The integer value for this token to hold
     */
    public IntToken(final int value) {
        this.value = value;
    }

    public boolean equals(final Object other) {
        // intentionally don't go through intTokenValue to avoid
        // spurious checked exception error
        return (other instanceof IntToken &&
                ((IntToken)other).value == value);
    }

    public int hashCode() {
        return value;
    }

    public String toString() {
        return Integer.toString(value);
    }

    /**
     * Calls <code>visitor</code>'s <code>visitIntToken</code> method, passing
     * along the integer value held within this token (which was provided
     * in the constructor).
     * @see edu.ucsb.cs56.pconrad.parsing.tokenizer.TokenVisitor#visitIntToken
     */
    public <A, T extends Throwable> A accept(final TokenVisitor<A, T> visitor) throws T {
	return visitor.visitIntToken(value);
    }
} // IntToken
