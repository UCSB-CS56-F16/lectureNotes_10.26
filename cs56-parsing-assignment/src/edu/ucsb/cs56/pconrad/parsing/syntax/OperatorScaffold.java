package edu.ucsb.cs56.pconrad.parsing.syntax;

/**
 * Class for conveniently making operators which can be represented with a single character.
 * The observation here is that from the standpoint of the syntax, the only major difference
 * between all the different operations is which character is used to represent them
 * (e.g., <code>+</code> versus <code>-</code>).
 */
public abstract class OperatorScaffold implements Operator {
    // begin instance variables
    protected final char repr;
    // end instance variables

    /**
     * @param repr The character representing this operation
     */
    public OperatorScaffold(char repr) {
        this.repr = repr;
    }

    /**
     * @return the character representing this operation, corresponding
     *         to the same character provided in the constructor
     */
    public char getRepr() { return repr; }

    /**
     * @return The character representing this operation as a string
     */
    public String toString() { return Character.toString(repr); }

    public boolean equals(Object other) {
	return (other instanceof OperatorScaffold &&
		((OperatorScaffold)other).getRepr() == repr);
    }

    public int hashCode() {
	return (int)repr;
    }
}
